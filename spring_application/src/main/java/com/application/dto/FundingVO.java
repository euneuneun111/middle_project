package com.application.dto;

import java.util.Date;

public class FundingVO {

	 private int fno;                     // 번호
	    private String title;               // 제목
	    private String writer;              // 작성자
	    private String content;             // 내용
	    private int viewcnt;                // 조회수
	    private Date regdate;               // 작성일
	    private Date updatedate;            // 수정일
	    private Date startdate;            // 펀딩 시작일
	    private Date enddate;              // 펀딩 종료일
	    private int point;                 // 포인트
	    private String dist;               // 구분자
	    private int fundingLike;           // 좋아요 수
	    private String fundingFicture;     // 펀딩 이미지 경로

	    // 기본 생성자
	    public FundingVO() {}

		public int getFno() {
			return fno;
		}

		public void setFno(int fno) {
			this.fno = fno;
		}

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

		public int getViewcnt() {
			return viewcnt;
		}

		public void setViewcnt(int viewcnt) {
			this.viewcnt = viewcnt;
		}

		public Date getRegdate() {
			return regdate;
		}

		public void setRegdate(Date regdate) {
			this.regdate = regdate;
		}

		public Date getUpdatedate() {
			return updatedate;
		}

		public void setUpdatedate(Date updatedate) {
			this.updatedate = updatedate;
		}

		public Date getStartdate() {
			return startdate;
		}

		public void setStartdate(Date startdate) {
			this.startdate = startdate;
		}

		public Date getEnddate() {
			return enddate;
		}

		public void setEnddate(Date enddate) {
			this.enddate = enddate;
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

		public int getFundingLike() {
			return fundingLike;
		}

		public void setFundingLike(int fundingLike) {
			this.fundingLike = fundingLike;
		}

		public String getFundingFicture() {
			return fundingFicture;
		}

		public void setFundingFicture(String fundingFicture) {
			this.fundingFicture = fundingFicture;
		}
}
