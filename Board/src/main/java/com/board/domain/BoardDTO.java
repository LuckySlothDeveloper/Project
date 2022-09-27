package com.board.domain;

import java.sql.Date;
import java.util.Objects;

public class BoardDTO {
	
	//글번호
	private int bno;
	
	//작성자
	private String writer;
	
	//내용
	private String content;
	
	//제목
	private String title;
	
	//조회수
	private String readCnt;
	
	//작성일
	private Date regDay;
	
	//수정일
	private Date updateDay;
	
	/* 디폴트 생성자 */
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/* 사용자 생성자 */
	public BoardDTO(int bno, String writer, String content, String title, String readCnt, Date regDay, Date updateDay) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.title = title;
		this.readCnt = readCnt;
		this.regDay = regDay;
		this.updateDay = updateDay;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(String readCnt) {
		this.readCnt = readCnt;
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
		return Objects.hash(bno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardDTO other = (BoardDTO) obj;
		return bno == other.bno;
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", writer=" + writer + ", content=" + content + ", title=" + title
				+ ", readCnt=" + readCnt + ", regDay=" + regDay + ", updateDay=" + updateDay + "]";
	}
	
	
	
}
