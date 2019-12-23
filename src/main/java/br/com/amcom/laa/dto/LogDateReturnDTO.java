package br.com.amcom.laa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogDateReturnDTO {
	
	@JsonProperty("date_as_string")
	private String dateAsString;

	@JsonProperty("total_count")
	private Long totalCount;

	public LogDateReturnDTO() {
		super();
	}

	public LogDateReturnDTO(String dateAsString, Long totalCount) {
		super();
		this.dateAsString = dateAsString;
		this.totalCount = totalCount;
	}

	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}
