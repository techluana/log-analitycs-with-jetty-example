package br.com.amcom.laa.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

import br.com.amcom.laa.configuration.ElasticsearchConfiguration;
import br.com.amcom.laa.constants.ElasticsearchConstants;
import br.com.amcom.laa.dto.LogDTO;
import br.com.amcom.laa.exceptions.ElasticsearchDefaultException;
import br.com.amcom.laa.exceptions.ElasticsearchNotResultException;
import br.com.amcom.laa.mapper.LogObjectMapper;

public class ConsumerServiceImpl implements ConsumerService {
	private static final Logger LOGGER = LogManager.getLogger(ConsumerServiceImpl.class);

	@Override
	public Integer saveLog(LogDTO entity) {
		IndexRequest request = new IndexRequest(ElasticsearchConstants.INDEX);
		String objectPost = new LogObjectMapper().getJsonValue(entity);
		request.source(objectPost, XContentType.JSON);

		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		IndexResponse response = null;
		try {
			response = config.getClient().index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchDefaultException(e);
		} finally {
			config.close();
		}
		
		if(null == response || !Result.CREATED.equals(response.getResult())) {
			throw new ElasticsearchNotResultException();
		}
		return response.status().getStatus();
	}
}
