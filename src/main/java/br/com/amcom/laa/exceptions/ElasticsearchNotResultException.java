package br.com.amcom.laa.exceptions;

public class ElasticsearchNotResultException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ElasticsearchNotResultException(Exception e) {
		this.message = e.getLocalizedMessage();
	}
	
	public ElasticsearchNotResultException() {
		this.message = "Could not perform data inclusion/search in Elasticsearch";
	}

	public String getMessage() {
		return message;
	}
	
}
