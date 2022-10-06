package com.board.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class GalleryDTO {
	
	private int gbno;
	
	private String title;
	private String content;
	
	private Date regDay;
	private Date updateDay;
	
	private List<GalleryAttachDTO> attachList;
}
