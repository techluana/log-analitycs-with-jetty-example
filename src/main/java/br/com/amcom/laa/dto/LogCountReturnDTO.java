package br.com.amcom.laa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogCountReturnDTO {
	
	private String url;

	@JsonProperty("total_count")
	private Long totalCount;

	public LogCountReturnDTO() {
		super();
	}

	public LogCountReturnDTO(String url, Long totalCount) {
		super();
		this.url = url;
		this.totalCount = totalCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}
