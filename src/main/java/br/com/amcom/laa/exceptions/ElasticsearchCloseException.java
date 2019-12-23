package br.com.amcom.laa.exceptions;

public class ElasticsearchCloseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;
	
	public ElasticsearchCloseException(Exception e) {
		message = e.getLocalizedMessage();
	}

	public String getMessage() {
		return message;
	}
	
}
