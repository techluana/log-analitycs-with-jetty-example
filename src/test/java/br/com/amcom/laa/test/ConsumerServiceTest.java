package br.com.amcom.laa.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.amcom.laa.dto.LogDTO;
import br.com.amcom.laa.services.ConsumerService;
import br.com.amcom.laa.services.ConsumerServiceImpl;

public class ConsumerServiceTest {

	@Mock
    private ConsumerService serviceMock; 

	@Before
	public void construct() {
		this.serviceMock = new ConsumerServiceImpl();
	}
	
    /*@Test
    public void testServiceEmpty()  {
    	LogDTO log = new LogDTO();
    	Integer expectedResult = 500;
    	when(serviceMock.saveLog(log)).thenReturn(expectedResult);
    	
    	Integer result = serviceMock.saveLog(log);
    	assertEquals(expectedResult, result);
    }
    
    @Test
    public void testServiceWithValue()  {
    	ZonedDateTime zdt = LocalDateTime.now().atZone(ZoneId.systemDefault());
    	LogDTO log = new LogDTO("/pet/exotics/cats/10", zdt.toInstant().getEpochSecond(), "5b019db5-b3d0-46d2-9963-437860af707f", 1L);
    	Integer expectedResult = 201;
    	when(serviceMock.saveLog(log)).thenReturn(expectedResult);
    	
    	Integer result = serviceMock.saveLog(log);
    	assertEquals(expectedResult, result);
    }*/
	
}
