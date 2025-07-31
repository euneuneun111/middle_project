package com.application.dto;

package com.example.domain;

public class FundingOptionVO {

    private String optionId;      // 옵션 ID
    private int fno;              // 펀딩 글 번호 (외래키)
    private String optionName;    // 옵션 이름
    private int optionPrice;      // 옵션 가격
    private Integer optionStock;  // 옵션 재고 수량 (nullable)

    // 기본 생성자
    public FundingOptionVO() {}

    // 전체 필드 생성자
    public FundingOptionVO(String optionId, int fno, String optionName, int optionPrice) {
        this.optionId = optionId;
        this.fno = fno;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }

    // Getter / Setter
    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(int optionPrice) {
        this.optionPrice = optionPrice;
    }

    public Integer getOptionStock() {
        return optionStock;
    }

    public void setOptionStock(Integer optionStock) {
        this.optionStock = optionStock;
    }

    @Override
    public String toString() {
        return "FundingOptionVO [optionId=" + optionId + ", fno=" + fno + ", optionName=" + optionName +
               ", optionPrice=" + optionPrice + "]";
    }
}

