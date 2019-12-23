package br.com.amcom.laa.services;

import java.util.List;

import br.com.amcom.laa.dto.LogCountReturnDTO;
import br.com.amcom.laa.dto.LogDateReturnDTO;

public interface SearchService {

	List<LogCountReturnDTO> searchTop3Url();
	
	List<LogCountReturnDTO> searchTop3urlByRegion(Integer region);
	
	LogCountReturnDTO searchUrlLessAccess();
	
	List<LogCountReturnDTO> searchTop3ByParams(Integer day, Integer week, Integer year);
	
	LogDateReturnDTO searchMinuteMoreAccess();
}
