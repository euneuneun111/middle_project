package com.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.command.FundingRegistCommand;
import com.application.dto.FundingOptionVO;
import com.application.dto.FundingVO;
import com.application.service.FundingOptionService;
import com.application.service.FundingService;

@Controller
@RequestMapping("/funding")
public class FundingController {

    private final FundingService fundingService;
    
    @Autowired
    private FundingOptionService fundingOptionService;

    @Autowired
    public FundingController(FundingService fundingService) {
        this.fundingService = fundingService;
    }

    // 🔹 전체 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<FundingVO> fundings = fundingService.getAllFundings();
        model.addAttribute("fundings", fundings);
        return "funding/list"; // 뷰 이름 (예: /WEB-INF/views/funding/list.jsp)
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
    @GetMapping("/write")
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
    @GetMapping("/edit/{fno}")
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
    @GetMapping("/delete/{fno}")
    public String delete(@PathVariable("fno") int fno) {
        fundingService.deleteFunding(fno);
        return "redirect:/funding/list";
    }

    // 🔹 좋아요 증가 처리 (비동기나 추가 기능 용)
    @PostMapping("/like/{fno}")
    @ResponseBody
    public String like(@PathVariable("fno") int fno) {
        fundingService.increaseLike(fno);
        return "success";
    }
}

