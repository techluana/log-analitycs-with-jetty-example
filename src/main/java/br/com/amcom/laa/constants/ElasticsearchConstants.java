package br.com.amcom.laa.constants;

public class ElasticsearchConstants {
	
	public static final String CLUSTER_NAME = "a094c6bb91604113879fa9968bb911e9";
	public static final String REGION = "us-central1";
	public static final int PORT = 9243;
	public static final String CLOUD_ADDRES = "gcp.cloud.es.io";
	public static final String CLOUD_ID = "tech-laa:dXMtY2VudHJhbDEuZ2NwLmNsb3VkLmVzLmlvJGEwOTRjNmJiOTE2MDQxMTM4NzlmYTk5NjhiYjkxMWU5JDg1NGU4OTU0Mjk0NDQxZDNhMjFlNzQwMmQxZTc3NTkx";
	public static final String INDEX = "laa-app";
	public static final String USER = "elastic";
	public static final String PASS = "TyMgHArrkkkRa0rEUcv5QtD5";
	
	public static final String SEARCH_AGGREGATION = "_count";
	
	public static final String SEARCH_NAME = "search_query";
	public static final String SEARCH_FIELD_URL = "url";
	public static final String SEARCH_FIELD_REGION = "server";
	public static final String SEARCH_FIELD_DATE = "request_date";
	public static final int SEARCH_SIZE_3 = 3;
	
	public static final String QUERY_BY_DAY = "doc.request_date.value.dayOfMonth";
	public static final String QUERY_BY_WEEK = "doc.request_date.value.getWeekOfWeekyear()";
	public static final String QUERY_BY_YEAR = "doc.request_date.value.year";
	public static final String QUERY_EQUALS = "==";
	
	
	public static String getHost() {
		return CLUSTER_NAME.concat(".").concat(REGION).concat(".").concat(CLOUD_ADDRES);
	}
	
}
