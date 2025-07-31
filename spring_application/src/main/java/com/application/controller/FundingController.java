package com.application.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.application.command.FundingRegistCommand;
import com.application.command.PageMaker;
import com.application.dto.FundingOptionVO;
import com.application.dto.FundingVO;
import com.application.dto.MemberVO;
import com.application.service.FundingOptionService;
import com.application.service.FundingService;

@Controller
@RequestMapping("/funding")
public class FundingController {

    private final FundingService fundingService;
    
    @Autowired
    private FundingOptionService fundingOptionService;
    
	@Resource(name = "picturePath")
	private String picturePath;

    @Autowired
    public FundingController(FundingService fundingService) {
        this.fundingService = fundingService;
    }

    // 🔹 전체 목록 조회   
    @GetMapping("/list")
    public String list(@ModelAttribute("pageMaker") PageMaker pageMaker, Model model) {
        List<FundingVO> fundings = fundingService.getFundingsPaging(pageMaker);
        int totalCount = fundingService.getTotalCount(pageMaker);

        pageMaker.setTotalCount(totalCount); // 계산 및 prev/next 설정

        model.addAttribute("fundings", fundings);
        model.addAttribute("pageMaker", pageMaker);

        return "funding/list";
    }

 // 🔹 단건 상세 조회 (옵션 포함)
    @GetMapping("/view/{fno}")
    public String view(@PathVariable("fno") int fno, Model model) {
        fundingService.increaseViewCnt(fno);
        FundingVO funding = fundingService.getFundingById(fno);
        model.addAttribute("funding", funding);

        List<FundingOptionVO> options = fundingOptionService.getOptionsByFundingId(fno);
        model.addAttribute("options", options);

        return "funding/view";
    }
    // 🔹 글쓰기 폼 이동
    @GetMapping("/regist")
    public String writeForm() {
        return "funding/write";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute FundingRegistCommand command) {
        // 1. 파일 이름 저장 (파일 저장 로직은 별도 구현 필요)
        String pictureFileName = (command.getPicture() != null && !command.getPicture().isEmpty())
                                 ? command.getPicture().getOriginalFilename()
                                 : "noimage.jpg";

        // 2. FundingVO 생성 및 등록
        FundingVO funding = command.toFundingVO(pictureFileName);
        int fno = fundingService.insertFunding(funding);

        // 3. 옵션 등록 (옵션 리스트가 존재하는 경우)
        if (command.getOptionList() != null && !command.getOptionList().isEmpty()) {
            for (FundingOptionVO option : command.getOptionList()) {
                option.setFno(fno); // 외래 키 설정
                fundingOptionService.insertFundingOption(option);
            }
        }

        return "redirect:/funding/list";
    }

    // 🔹 수정 폼 이동
    @GetMapping("/modify/{fno}")
    public String editForm(@PathVariable("fno") int fno, Model model) {
        FundingVO funding = fundingService.getFundingById(fno);
        model.addAttribute("funding", funding);
        return "funding/edit";
    }

    // 🔹 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute FundingVO vo) {
        fundingService.updateFunding(vo);
        return "redirect:/funding/view/" + vo.getFno();
    }

    // 🔹 삭제
    @GetMapping(value = "/remove")
	public ModelAndView remove(int fno,ModelAndView mnv) throws Exception {
		String url = "/funding/remove_success";

		// 이미지 파일을 삭제
		FundingVO funding = fundingService.getFundingById(fno);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, funding.getFundingFicture());
		if (imageFile.exists()) {
			imageFile.delete();
		}
		// db삭제

		fundingService.deleteFunding(fno);
		
		mnv.setViewName(url);
		return mnv;
	}

    @PostMapping("/like/{fno}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void likeToggle(@PathVariable("fno") int fno, @RequestParam("like") boolean like) {
        fundingService.toggleLike(fno, like);
    }
    
    
}

