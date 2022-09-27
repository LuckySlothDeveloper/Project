package com.board.domain;

import java.sql.Date;
import java.util.Objects;

public class ReplyDTO {

	//댓글 번호
	private int rno;
	
	//글 번호
	private int bno;
	
	//댓글 작성자
	private String replyer;
	
	//댓글 내용
	private String replyText;
	
	//작성일
	private Date regDay;
	
	//수정일
	private Date updateDay;
	
	
	/* 디폴트 생성자 */
	public ReplyDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/* 사용자 생성자 */
	public ReplyDTO(int rno, int bno, String replyer, String replyText, Date regDay, Date updateDay) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyer = replyer;
		this.replyText = replyText;
		this.regDay = regDay;
		this.updateDay = updateDay;
	}
	

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public Date getRegDay() {
		return regDay;
	}

	public void setRegDay(Date regDay) {
		this.regDay = regDay;
	}

	public Date getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(rno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyDTO other = (ReplyDTO) obj;
		return rno == other.rno;
	}

	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", bno=" + bno + ", replyer=" + replyer + ", replyText=" + replyText
				+ ", regDay=" + regDay + ", updateDay=" + updateDay + "]";
	}
	
	
}
