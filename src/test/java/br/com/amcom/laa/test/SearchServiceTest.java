package br.com.amcom.laa.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.amcom.laa.dto.LogCountReturnDTO;
import br.com.amcom.laa.services.SearchService;
import br.com.amcom.laa.services.SearchServiceImpl;

public class SearchServiceTest {

	@Mock
    private SearchService serviceMock; 
	
	@Before
	public void construct() {
		this.serviceMock = new SearchServiceImpl();
	}
/*
    @Test
    public void testServiceSearchTop3()  {
    	List<LogCountReturnDTO> expectedResult = new ArrayList<LogCountReturnDTO>();
    	expectedResult.add(new LogCountReturnDTO("/pet/exotics/cats/10", 150L));
    	expectedResult.add(new LogCountReturnDTO("/pet/guaipeca/dogs/1", 130L));
    	expectedResult.add(new LogCountReturnDTO("/tiggers/bid/now", 126L));

    	when(serviceMock.searchTop3Url()).thenReturn(expectedResult);
    	
    	List<LogCountReturnDTO> result = serviceMock.searchTop3Url();
    	assertEquals(expectedResult.size(), result.size());
    }
    
    @Test
    public void testServiceSearchTop3Byregion()  {
    	List<LogCountReturnDTO> expectedResult = new ArrayList<LogCountReturnDTO>();
    	expectedResult.add(new LogCountReturnDTO("/pet/exotics/cats/10", 150L));
    	expectedResult.add(new LogCountReturnDTO("/pet/guaipeca/dogs/1", 130L));
    	expectedResult.add(new LogCountReturnDTO("/tiggers/bid/now", 126L));

    	when(serviceMock.searchTop3urlByRegion(1)).thenReturn(expectedResult);
    	
    	List<LogCountReturnDTO> result = serviceMock.searchTop3Url();
    	assertEquals(expectedResult.size(), result.size());
    }
    
    @Test
    public void testServiceSearchLessUrl()  {
    	LogCountReturnDTO expectedResult = new LogCountReturnDTO("/tiggers/bid/now", 126L);

    	when(serviceMock.searchUrlLessAccess()).thenReturn(expectedResult);
    	
    	LogCountReturnDTO result = serviceMock.searchUrlLessAccess();
    	assertEquals(expectedResult.getUrl(), result.getUrl());
    }*/
	
}
