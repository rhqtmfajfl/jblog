package com.poscoict.jblog.exception;

public class FileUploadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public FileUploadException(String message){
		
	}
	
	public FileUploadException(){
		super("FileUpload Exception OOPS");
	}
	
	
}
