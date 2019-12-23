package br.com.amcom.laa.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;

import br.com.amcom.laa.configuration.ElasticsearchConfiguration;
import br.com.amcom.laa.dto.ClusterHealthStatusDTO;
import br.com.amcom.laa.exceptions.ElasticsearchDefaultException;

public class HealthServiceImpl implements HealthService {
	private static final Logger LOGGER = LogManager.getLogger(HealthServiceImpl.class);

	@Override
	public ClusterHealthStatusDTO checkHealth() {
		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		ClusterHealthResponse response;
		try {
			response = config.getClient().cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchDefaultException(e);
		} finally {
			config.close();
		}
		
		if (null == response) {
			throw new ElasticsearchDefaultException("Could not check Elasticsearch health status");
		}
		
		return new ClusterHealthStatusDTO(response.getClusterName(), response.getStatus());
	}
}
