package br.com.amcom.laa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogDTO {
	
	@JsonIgnore
	private String id;
	
	private String url;
	
	@JsonProperty("request_date")
	private Long requestDateInMilis;
	
	private String key;
	
	private Long server;

	public LogDTO() {
		super();
	}

	public LogDTO(String url, Long requestDate, String key, Long server) {
		super();
		this.url = url;
		this.requestDateInMilis = requestDate;
		this.key = key;
		this.server = server;
	}
	
	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getRequestDateInMilis() {
		return requestDateInMilis;
	}

	public void setRequestDateInMilis(Long requestDateInMilis) {
		this.requestDateInMilis = requestDateInMilis;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getServer() {
		return server;
	}

	public void setServer(Long server) {
		this.server = server;
	}
	
}
