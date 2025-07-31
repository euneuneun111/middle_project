package com.application.command;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.application.dto.FundingVO;
import com.application.dto.FundingOptionVO;

public class FundingRegistCommand {

    private String title;
    private String writer;
    private String content;
    private Date startDate;
    private Date endDate;
    private int point;
    private String dist;
    private MultipartFile picture; // 이미지 파일 업로드용

    private List<FundingOptionVO> optionList; // 후원 옵션 목록

    // --- Getter/Setter ---

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }

    public String getDist() {
        return dist;
    }
    public void setDist(String dist) {
        this.dist = dist;
    }

    public MultipartFile getPicture() {
        return picture;
    }
    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public List<FundingOptionVO> getOptionList() {
        return optionList;
    }
    public void setOptionList(List<FundingOptionVO> optionList) {
        this.optionList = optionList;
    }

    // --- FundingVO 변환 메서드 ---
    public FundingVO toFundingVO(String pictureFileName) {
        FundingVO vo = new FundingVO();
        vo.setTitle(this.title);
        vo.setWriter(this.writer);
        vo.setContent(this.content);
        vo.setStartdate(startDate);
        vo.setEnddate(endDate);
        vo.setPoint(this.point);
        vo.setDist(this.dist);
        vo.setFundingFicture(pictureFileName != null ? pictureFileName : "noimage.jpg");
        return vo;
    }
}
