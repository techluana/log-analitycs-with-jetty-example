package br.com.amcom.laa.configuration;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;

import br.com.amcom.laa.constants.ElasticsearchConstants;
import br.com.amcom.laa.exceptions.ElasticsearchCloseException;

public class ElasticsearchConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(ElasticsearchConfiguration.class);

	private RestHighLevelClient client;

	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchCloseException(e);
		}
		client = null;
	}

	public RestHighLevelClient getClient() {
		if (null == client) {
			open();
		}
		return client;
	}

	private void open() {
		client = new RestHighLevelClient(getRestClientBuilder());
	}

	private RestClientBuilder getRestClientBuilder() {

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(ElasticsearchConstants.USER, ElasticsearchConstants.PASS));

		return RestClient.builder(
				ElasticsearchConstants.CLOUD_ID)
				.setHttpClientConfigCallback(new HttpClientConfigCallback() {
					@Override
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				});
	}
}
