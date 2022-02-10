package com.poscoict.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.exception.FileUploadException;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/upload-images";
	private static String URL_BASE = "/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originFilename = multipartFile.getOriginalFilename();  //파일 명을 가지고 온다.
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);  //
			String extName2 = originFilename.substring(originFilename.lastIndexOf('.'));

			String saveFilename = genearteSaveFilename(extName);  // generateSaveFilename에 의해
			long fileSize = multipartFile.getSize();  // org.springframework.web.multipart.commons.CommonsMultipartFile@506906d5 라고 하는 파일의 크기를 구한다.
			
			
//			System.out.println("originFilename : " + originFilename); //예상 dog.jpg -> dog.jpg
//			
//			System.out.println("originFilename.substring(originFilename.lastIndexOf('.')+1)" + originFilename.substring(originFilename.lastIndexOf('.')+1)); // 4부터이므로 dog.jpg 면 j 부터 끝까지 자른다.
//			System.out.println("originFilename.lastIndexOf('.')" + originFilename.lastIndexOf('.'));  //예상 .jpg 가 나온다. -> 3 이 나온다. 이유 dog. 인데 .이 위치상 3이기때문에
//			
//			
//			System.out.println("extName" + extName); // jpg가 나오네
//			System.out.println("extName2" + extName2); // 여기서는 .jpg가 나오네

			//getOriginalFilename 이 메서드는 파일명과 확장자까지 붙여서 문자열을 반환합니다.
			System.out.println("##############" + originFilename);
			System.out.println("##############" + saveFilename);
			System.out.println("##############" + fileSize);
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			url = URL_BASE + "/" + saveFilename;  //
			
		} catch(IOException ex) {
			throw new FileUploadException("file upload error:" + ex);
		}
		
		return url;
	}

	private String genearteSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);  // 앞에는 년 월 일 시간 분 초 밀리세컨드 가 파일명으로 생성된다.
		
		return filename;
	}

}