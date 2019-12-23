package br.com.amcom.laa.exceptions;

public class ElasticsearchDefaultException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ElasticsearchDefaultException(Exception e) {
		this.message = e.getLocalizedMessage();
	}
	
	public ElasticsearchDefaultException(String messageException) {
		this.message = messageException;
	}

	public String getMessage() {
		return message;
	}
	
}
