package com.board.domain;

import lombok.Data;

@Data
public class GalleryAttachDTO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	
	private int gbno;
}
