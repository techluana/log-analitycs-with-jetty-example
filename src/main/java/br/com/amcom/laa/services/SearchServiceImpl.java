package br.com.amcom.laa.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import br.com.amcom.laa.configuration.ElasticsearchConfiguration;
import br.com.amcom.laa.constants.ElasticsearchConstants;
import br.com.amcom.laa.dto.LogCountReturnDTO;
import br.com.amcom.laa.dto.LogDateReturnDTO;
import br.com.amcom.laa.exceptions.ElasticsearchDefaultException;
import br.com.amcom.laa.exceptions.ElasticsearchNotResultException;

public class SearchServiceImpl implements SearchService {
	private static final Logger LOGGER = LogManager.getLogger(SearchServiceImpl.class);

	@Override
	public List<LogCountReturnDTO> searchTop3Url() {
		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		SearchRequest searchRequest = new SearchRequest(ElasticsearchConstants.INDEX);
        TermsAggregationBuilder aggregationBuilder = getCountAggregationBuilder(ElasticsearchConstants.SEARCH_NAME, 
				ElasticsearchConstants.SEARCH_FIELD_URL, ElasticsearchConstants.SEARCH_SIZE_3);
        SearchSourceBuilder searchBuilder = getSearchBuilder(aggregationBuilder);
        
        searchRequest.source(searchBuilder);
        SearchResponse searchResponse = null;
		try {
			searchResponse = config.getClient().search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchNotResultException(e);
		}finally {
			config.close();
		}
		return getObjectsFromBuckets(searchResponse);
	}
	
	@Override
	public List<LogCountReturnDTO> searchTop3urlByRegion(Integer region) {
		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		SearchRequest searchRequest = new SearchRequest(ElasticsearchConstants.INDEX);
        TermsAggregationBuilder aggregationBuilder = getCountAggregationBuilder(ElasticsearchConstants.SEARCH_NAME, 
				ElasticsearchConstants.SEARCH_FIELD_URL, ElasticsearchConstants.SEARCH_SIZE_3);
        SearchSourceBuilder searchBuilder = getSearchBuilder(aggregationBuilder);
        searchBuilder.query(QueryBuilders.termQuery(ElasticsearchConstants.SEARCH_FIELD_REGION, region));
        
        searchRequest.source(searchBuilder);
        SearchResponse searchResponse = null;
		try {
			searchResponse = config.getClient().search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchNotResultException(e);
		}finally {
			config.close();
		}
		return getObjectsFromBuckets(searchResponse);
	}

	@Override
	public LogCountReturnDTO searchUrlLessAccess() {
		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		SearchRequest searchRequest = new SearchRequest(ElasticsearchConstants.INDEX);
        TermsAggregationBuilder aggregationBuilder = getCountAggregationBuilderMinuteLessAccess(ElasticsearchConstants.SEARCH_NAME, 
				ElasticsearchConstants.SEARCH_FIELD_URL);
        SearchSourceBuilder searchBuilder = getSearchBuilder(aggregationBuilder);
        
        searchRequest.source(searchBuilder);
        SearchResponse searchResponse = null;
		try {
			searchResponse = config.getClient().search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchNotResultException(e);
		}finally {
			config.close();
		}
		List<LogCountReturnDTO> logs = getObjectsFromBuckets(searchResponse);
		return logs.stream().findFirst().orElse(null);
	}

	@Override
	public List<LogCountReturnDTO> searchTop3ByParams(Integer day, Integer week, Integer year) {
		if(null == day && null == week && null == year) {
			throw new ElasticsearchDefaultException("Enter day, week, or year for results");
		}
		
		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		SearchRequest searchRequest = new SearchRequest(ElasticsearchConstants.INDEX);
        TermsAggregationBuilder aggregationBuilder = getCountAggregationBuilderMinuteLessAccess(ElasticsearchConstants.SEARCH_NAME, 
				ElasticsearchConstants.SEARCH_FIELD_URL);
        SearchSourceBuilder searchBuilder = getSearchBuilder(aggregationBuilder);
        addQueryBuilders(searchBuilder, ElasticsearchConstants.QUERY_BY_DAY, day);
        addQueryBuilders(searchBuilder, ElasticsearchConstants.QUERY_BY_WEEK, week);
        addQueryBuilders(searchBuilder, ElasticsearchConstants.QUERY_BY_YEAR, year);
        
        searchRequest.source(searchBuilder);
        SearchResponse searchResponse = null;
		try {
			searchResponse = config.getClient().search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchNotResultException(e);
		}finally {
			config.close();
		}
		return getObjectsFromBuckets(searchResponse);
	}
	
	private void addQueryBuilders(SearchSourceBuilder searchBuilder, String script, Integer value) {
		if(null != value) {
			searchBuilder.query(QueryBuilders.scriptQuery(new Script(script.concat(ElasticsearchConstants.QUERY_EQUALS).concat(value.toString()))));
		}
	}

	@Override
	public LogDateReturnDTO searchMinuteMoreAccess() {
		ElasticsearchConfiguration config = new ElasticsearchConfiguration();
		SearchRequest searchRequest = new SearchRequest(ElasticsearchConstants.INDEX);
        DateHistogramAggregationBuilder aggregationBuilder = getCountAggregationBuilderByMinutes(ElasticsearchConstants.SEARCH_NAME, 
				ElasticsearchConstants.SEARCH_FIELD_DATE);
        SearchSourceBuilder searchBuilder = getSearchBuilderFromDateHistogram(aggregationBuilder);
        
        searchRequest.source(searchBuilder);
        SearchResponse searchResponse = null;
		try {
			searchResponse = config.getClient().search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new ElasticsearchNotResultException(e);
		}finally {
			config.close();
		}
		List<LogDateReturnDTO> logs = getObjectsDateFromBuckets(searchResponse);
		return logs.stream().sorted((o1, o2) -> o2.getTotalCount().compareTo(o1.getTotalCount()))
				.findFirst().orElse(null);
	}

	private SearchSourceBuilder getSearchBuilder(TermsAggregationBuilder aggregationBuilder) {
		SearchSourceBuilder searchBuilder = new SearchSourceBuilder(); 
		searchBuilder.aggregation(aggregationBuilder);
		searchBuilder.from(0); 
		searchBuilder.size(0);
		searchBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
		return searchBuilder;
	}
	
	private SearchSourceBuilder getSearchBuilderFromDateHistogram(DateHistogramAggregationBuilder aggregationBuilder) {
		SearchSourceBuilder searchBuilder = new SearchSourceBuilder(); 
		searchBuilder.aggregation(aggregationBuilder);
		searchBuilder.from(0); 
		searchBuilder.size();
		searchBuilder.timeout(new TimeValue(300, TimeUnit.SECONDS));
		return searchBuilder;
	}
	
	private TermsAggregationBuilder getCountAggregationBuilder(String nameAgreggation, String field, int size) {
		return AggregationBuilders.terms(nameAgreggation)
                .field(field)
                .size(size)
                .order(BucketOrder.aggregation(ElasticsearchConstants.SEARCH_AGGREGATION, false));
	}
	
	private TermsAggregationBuilder getCountAggregationBuilderMinuteLessAccess(String nameAgreggation, String field) {
		return AggregationBuilders.terms(nameAgreggation)
                .field(field)
                .size(3)
                .order(BucketOrder.aggregation(ElasticsearchConstants.SEARCH_AGGREGATION, true));
	}
	
	private DateHistogramAggregationBuilder getCountAggregationBuilderByMinutes(String nameAgreggation, String field) {
		return AggregationBuilders
				.dateHistogram(nameAgreggation)
				.field(field)
				.fixedInterval(DateHistogramInterval.MINUTE)
				.format("yyyy-MM-dd:HH.mm")
				.minDocCount(1)
				.order(BucketOrder.aggregation(ElasticsearchConstants.SEARCH_AGGREGATION, false));
	}
	
	private LogCountReturnDTO bucketToObject(Terms.Bucket bucket) {
		return new LogCountReturnDTO(bucket.getKeyAsString(), bucket.getDocCount());
    }
	
	private List<LogCountReturnDTO> getObjectsFromBuckets(SearchResponse searchResponse){
		if(null == searchResponse || null == searchResponse.getAggregations()) {
			throw new ElasticsearchNotResultException();
		}
		ParsedStringTerms aggs = searchResponse.getAggregations().get(ElasticsearchConstants.SEARCH_NAME);
		if(null == aggs || null == aggs.getBuckets()) {
			throw new ElasticsearchNotResultException();
		}
		return aggs.getBuckets().stream().map(this::bucketToObject).collect(Collectors.toList());
	}
	
	private LogDateReturnDTO bucketToObjectDate(Histogram.Bucket bucket) {
		return new LogDateReturnDTO(bucket.getKeyAsString(), bucket.getDocCount());
    }
	
	private List<LogDateReturnDTO> getObjectsDateFromBuckets(SearchResponse searchResponse){
		if(null == searchResponse || null == searchResponse.getAggregations()) {
			throw new ElasticsearchNotResultException();
		}
		ParsedDateHistogram aggs = searchResponse.getAggregations().get(ElasticsearchConstants.SEARCH_NAME);
		if(null == aggs || null == aggs.getBuckets()) {
			throw new ElasticsearchNotResultException();
		}
		return aggs.getBuckets().stream().map(this::bucketToObjectDate).collect(Collectors.toList());
	}
	
}
