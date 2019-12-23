package br.com.amcom.laa.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.amcom.laa.dto.ClusterHealthStatusDTO;
import br.com.amcom.laa.services.HealthService;
import br.com.amcom.laa.services.HealthServiceImpl;

public class HealthServiceTest {

	/*@Mock
    private HealthService serviceMock; 

	@Before
	public void construct() {
		this.serviceMock = new HealthServiceImpl();
	}
	
    @Test
    public void testServiceEmpty()  {
    	ClusterHealthStatusDTO expectedResult = new ClusterHealthStatusDTO("teste", ClusterHealthStatus.GREEN);
    	when(serviceMock.checkHealth()).thenReturn(expectedResult);
    	
    	ClusterHealthStatusDTO result = serviceMock.checkHealth();
    	assertEquals(expectedResult.getClusterName(), result.getClusterName());
    	assertEquals(expectedResult.getStatus(), result.getStatus());
    }*/
	
}
