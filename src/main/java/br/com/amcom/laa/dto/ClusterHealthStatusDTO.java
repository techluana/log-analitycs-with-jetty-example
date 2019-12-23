package br.com.amcom.laa.dto;

import org.elasticsearch.cluster.health.ClusterHealthStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterHealthStatusDTO {
	
	@JsonProperty("cluster_name")
	private String clusterName;
	private ClusterHealthStatus status;

	public ClusterHealthStatusDTO() {
		super();
	}

	public ClusterHealthStatusDTO(String clusterName, ClusterHealthStatus status) {
		super();
		this.clusterName = clusterName;
		this.status = status;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public ClusterHealthStatus getStatus() {
		return status;
	}

	public void setStatus(ClusterHealthStatus status) {
		this.status = status;
	}
		
}
