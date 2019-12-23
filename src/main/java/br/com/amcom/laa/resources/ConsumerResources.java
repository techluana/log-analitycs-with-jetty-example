package br.com.amcom.laa.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.amcom.laa.dto.LogDTO;
import br.com.amcom.laa.services.ConsumerService;
import br.com.amcom.laa.services.ConsumerServiceImpl;

@Path("/laar/ingest")
public class ConsumerResources {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response comsumer(LogDTO log) {
		ConsumerService service = new ConsumerServiceImpl();
		service.saveLog(log);
		return Response.status(Status.CREATED).build();
	}
}
