package com.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyCriteria {

	private int pageNum;
	private int amount;
	
	private int bno;
	
	public ReplyCriteria() {
		this(1, 10, 0);
	}

	public ReplyCriteria(int pageNum, int amount, int bno) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.bno = bno;
	}
}
