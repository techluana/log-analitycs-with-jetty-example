package br.com.amcom.laa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.amcom.laa.dto.ClusterHealthStatusDTO;
import br.com.amcom.laa.services.HealthService;
import br.com.amcom.laa.services.HealthServiceImpl;

@Path("/laaa/health")
public class HealthResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkHealth() {
		HealthService service = new HealthServiceImpl();
		ClusterHealthStatusDTO result = service.checkHealth();
		return Response.status(Status.OK).entity(result).build();
	}
}
