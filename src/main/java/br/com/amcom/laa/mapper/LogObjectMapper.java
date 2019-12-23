package br.com.amcom.laa.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.amcom.laa.dto.LogDTO;
import br.com.amcom.laa.resources.ConsumerResources;

public class LogObjectMapper {
	private static final Logger LOGGER = LogManager.getLogger(ConsumerResources.class);

	private ObjectMapper mapper;
	
	public LogObjectMapper() {
		super();
		this.mapper = new ObjectMapper();
	}

	public String getJsonValue(LogDTO entity) {
		try {
			return mapper.writeValueAsString(entity);
		} catch (JsonMappingException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public LogDTO getEntityFromJsonValue(String jsonValue) {
		try {
			return mapper.readValue(jsonValue, LogDTO.class);
		} catch (JsonMappingException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			LOGGER.warn(e);
			e.printStackTrace();
		}
		return null;
	}
}
