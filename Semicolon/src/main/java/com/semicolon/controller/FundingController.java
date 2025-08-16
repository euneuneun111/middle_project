package com.semicolon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.josephoconnell.html.HTMLInputFilter;
import com.semicolon.command.FundingModifyCommand;
import com.semicolon.command.FundingRegistCommand;
import com.semicolon.command.PageMaker;
import com.semicolon.dao.AttachDAO;
import com.semicolon.dto.AttachVO;
import com.semicolon.dto.FundingVO;
import com.semicolon.dto.HeartVO;
import com.semicolon.dto.MemberVO;
import com.semicolon.service.FundingService;

@Controller
@RequestMapping("/funding")
public class FundingController {

	@Autowired
	private FundingService fundingService;

	@Autowired
	private AttachDAO attachDAO;

	@GetMapping("/list")
	public ModelAndView list(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception {
		String url = "/funding/list";
		List<FundingVO> fundingList = fundingService.searchList(pageMaker);
		mnv.addObject("fundingList", fundingList);
		mnv.setViewName(url);
		return mnv;
	}

	@GetMapping("/list/rest")
	@ResponseBody
	public List<FundingVO> listRest(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception {
		List<FundingVO> fundingList = fundingService.searchList(pageMaker);
		return fundingList;
	}

	@GetMapping("/list/latest")
	public ModelAndView listLatest(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception {
		String url = "/funding/list";
		List<FundingVO> fundingList = fundingService.listByLatest(pageMaker);
		mnv.addObject("fundingList", fundingList);
		mnv.addObject("pageType", "latest");
		mnv.setViewName(url);
		return mnv;
	}

	@GetMapping("/list/popular")
	public ModelAndView listPopular(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception {
		String url = "/funding/list";
		List<FundingVO> fundingList = fundingService.listByPopular(pageMaker);
		mnv.addObject("fundingList", fundingList);
		mnv.addObject("pageType", "popular");
		mnv.setViewName(url);
		return mnv;
	}

	@GetMapping("/regist")
	public ModelAndView registForm(ModelAndView mnv) throws Exception {
		String url = "/funding/regist";
		mnv.setViewName(url);
		return mnv;
	}

	@javax.annotation.Resource(name = "fundingSavedFilePath")
	private String fileUploadPath;

	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(FundingRegistCommand regCommand, ModelAndView mnv) throws Exception {

		String url = "/funding/regist_success";

		// 파일저장
		List<MultipartFile> uploadFiles = regCommand.getUploadFile();
		String uploadPath = fileUploadPath;

		List<AttachVO> attaches = saveFileToAttaches(uploadFiles, uploadPath);

		// DB
		FundingVO funding = regCommand.toFundingVO();
		funding.setTitle(HTMLInputFilter.htmlSpecialChars(funding.getTitle()));
		funding.setAttachList(attaches);

		int fno = fundingService.regist(funding);

		mnv.setViewName(url);
		mnv.addObject("fno", fno);

		return mnv;
	}

	private List<AttachVO> saveFileToAttaches(List<MultipartFile> multiFiles, String savePath) throws Exception {
		if (multiFiles == null)
			return null;

		// 저장 -> attachVO -> attachList.add
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		for (MultipartFile multi : multiFiles) {
			// 파일명
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileName = uuid + "$$" + multi.getOriginalFilename();

			// 파일저장
			File target = new File(savePath, fileName);
			target.mkdirs();
			multi.transferTo(target);

			AttachVO attach = new AttachVO();
			attach.setUploadPath(savePath);
			attach.setFileName(fileName);
			attach.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());

			// attchList 추가
			attachList.add(attach);

		}
		return attachList;
	}

	@GetMapping("/detail")
	public ModelAndView detail(int fno, HttpSession session, ModelAndView mnv) throws Exception {
		String url = "/funding/detail";

		ServletContext ctx = session.getServletContext();

		String key = "funding:" + fno;

		FundingVO funding = null;
		if (ctx.getAttribute(key) != null) {
			funding = fundingService.getFunding(fno);
		} else {
			funding = fundingService.detail(fno);
			ctx.setAttribute(key, key);
		}
		
		 // 로그인 사용자
        String loginId = ((MemberVO)session.getAttribute("loginUser")).getId();

        // 사용자가 좋아요 했는지 확인
        boolean hearted = fundingService.isHeartedByUser(fno, loginId);
        
        mnv.addObject("hearted", hearted);

		mnv.addObject("funding", funding);
		mnv.setViewName(url);

		return mnv;
	}
	
	@PostMapping("/heart")
    public String toggleHeart(HeartVO heartVO) {
        fundingService.toggleHeart(heartVO);
        return "redirect:/funding/detail?fno=" + heartVO.getFno();
    }

	@GetMapping("/modify")
	public void modifyForm(int fno, Model model) throws Exception {
		FundingVO funding = fundingService.getFunding(fno);
		model.addAttribute("funding", funding);

	}

	@PostMapping("/modify")
	public ModelAndView modify(FundingModifyCommand modCommand, ModelAndView mnv) throws Exception {
		String url = "/funding/modify_success";

		// 파일삭제
		if (modCommand.getDeleteFile() != null && modCommand.getDeleteFile().length > 0) {
			for (int ano : modCommand.getDeleteFile()) {
				AttachVO attach = attachDAO.selectAttachByAno(ano);

				File deleteFile = new File(attach.getUploadPath(), attach.getFileName());

				if (deleteFile.exists()) {
					deleteFile.delete(); // 파일삭제
				}
				attachDAO.deletAttach(ano); // DB삭제
			}
		}

		// 파일저장
		List<AttachVO> attachList = saveFileToAttaches(modCommand.getUploadFile(), fileUploadPath);

		// FundingVO setting
		FundingVO funding = modCommand.toFundingVO();
		funding.setAttachList(attachList);

		// DB 저장
		funding.setTitle(HTMLInputFilter.htmlSpecialChars(funding.getTitle()));
		int fno = fundingService.modify(funding);

		mnv.setViewName(url);
		mnv.addObject("fno", fno);
		return mnv;
	}

	@GetMapping("/remove")
	public ModelAndView remove(int fno, ModelAndView mnv) throws Exception {
		String url = "/funding/remove_success";

		// 첨부파일 삭제
		List<AttachVO> attachList = fundingService.getFunding(fno).getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}

		// DB 삭제
		fundingService.remove(fno);

		mnv.setViewName(url);
		return mnv;
	}

	@GetMapping("/getPicture")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(int fno) {
		ResponseEntity entity = null;

		FundingVO funding = null;

		try {
			funding = fundingService.getFunding(fno);
		} catch (SQLException e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (funding == null)
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

		String picture = funding.getAttachList().get(0).getFileName();

		InputStream in = null;

		try {
			in = new FileInputStream(new File(fileUploadPath, picture));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);

			return entity;
		} catch (IOException e) {
			System.out.println(
					"Not Founded ::: " + funding.getFno() + ":" + funding.getAttachList().get(0).getFileName());
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@GetMapping("/getFile")
	@ResponseBody
	public ResponseEntity<Resource> getFile(int ano) throws Exception {

		AttachVO attach = attachDAO.selectAttachByAno(ano);
		String filePath = attach.getUploadPath() + File.separator + attach.getFileName();

		Resource resource = new UrlResource(Paths.get(filePath).toUri());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + UriUtils.encode(attach.getFileName().split("\\$\\$")[1], "UTF-8") + "\"")
				.body(resource);
	}

	@GetMapping("/paymentPopup")
	public ModelAndView paymentPopup(ModelAndView mnv) throws Exception {
		String url = "/funding/paymentPopup";
		mnv.setViewName(url);
		return mnv;
	}
}
