package com.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.GalleryAttachDTO;
import com.board.domain.GalleryDTO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	/* 갤러리 등록 화면 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		System.out.println("갤러리 게시판 등록 화면");
	}

	/* 갤러리 등록 처리 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<GalleryAttachDTO>> insert(MultipartFile[] uploadFile, GalleryDTO galleryDTO, RedirectAttributes rttr) {
		System.out.println("갤러리 게시판 등록 처리 - galleryDTO : " + galleryDTO);
		
		List<GalleryAttachDTO> list = new ArrayList<>();
		
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();
		
		/* 업로드 폴더 생성 */
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {	//uploadPath 폴더가 없으면 실행
			uploadPath.mkdirs();
		}
		
		System.out.println("업로드 폴더 생성 - uploadPath : " + uploadPath);
		
		for(MultipartFile multipartFile : uploadFile) {
			System.out.println("갤러리 게시판 등록 처리 - 업로드 파일 이름 : " + multipartFile.getOriginalFilename());
			System.out.println("갤러리 게시판 등록 처리 - 업로드 파일 사이즈 : " + multipartFile.getSize());
			
			GalleryAttachDTO galleryAttachDTO = new GalleryAttachDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//인터넷 익스플로러 처리
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			/* 중복 방지용 uuid */
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				rttr.addFlashAttribute("insert-success", true);
				
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
				
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
				
				thumbnail.close();
				
				list.add(galleryAttachDTO);
				
			} catch (Exception e) {
				e.printStackTrace();
				rttr.addFlashAttribute("insert-success", false);
			}
		
		}
		
		return new ResponseEntity<List<GalleryAttachDTO>>(list, HttpStatus.OK);
	}
	
	/* 썸네일 처리 */
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		File file = new File("c:\\upload\\" + fileName);
		
		System.out.println("업로드 파일 썸네일 처리 - file : " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 첨부파일 다운로드 */
	@RequestMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent, String fileName) {
		System.out.println("다운로드 처리 - fileName : " + fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		System.out.println("리소스 - resource : " + resource);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String resourceName = resource.getFilename();
		
		//UUID 제거
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			
			if(userAgent.contains("Trident")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			}else if(userAgent.contains("Edge")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			}else if(userAgent.contains("Chrome")) {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			System.out.println("다운로드 파일명 - downloadName : " + downloadName);
			
			headers.add("Content-Disposition", "attachment; fileName=" + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	/* 첨부파일 삭제 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> delete(String fileName) {
		System.out.println("첨부파일 삭제 처리 - fileName : " + fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			String largeFileName = file.getAbsolutePath().replace("s_", "");
			
			file = new File(largeFileName);
			
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
	/* 년/월/일 폴더 생성 */
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
}
