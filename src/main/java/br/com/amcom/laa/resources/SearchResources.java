package br.com.amcom.laa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.amcom.laa.dto.LogCountReturnDTO;
import br.com.amcom.laa.dto.LogDateReturnDTO;
import br.com.amcom.laa.services.SearchService;
import br.com.amcom.laa.services.SearchServiceImpl;

@Path("/laa/metrics")
public class SearchResources {

	@GET
	@Path("/top-3")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchTop3() {
		SearchService service = new SearchServiceImpl();
		List<LogCountReturnDTO> result = service.searchTop3Url();
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/top-3/{region}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchTop3Region(@PathParam("region") Long region) {
		SearchService service = new SearchServiceImpl();
		List<LogCountReturnDTO> result = service.searchTop3urlByRegion(region.intValue());
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/less-url-access")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchLessURLAccess() {
		SearchService service = new SearchServiceImpl();
		LogCountReturnDTO result = service.searchUrlLessAccess();
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/top-3/params")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchTop3Region(@QueryParam(value = "day") Integer day, 
			@QueryParam(value = "week") Integer week, @QueryParam(value = "year") Integer year) {
		SearchService service = new SearchServiceImpl();
		List<LogCountReturnDTO> result = service.searchTop3ByParams(day, week, year);
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/more-minute-access")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchMoreMinuteAccess() {
		SearchService service = new SearchServiceImpl();
		LogDateReturnDTO result = service.searchMinuteMoreAccess();
		return Response.status(Status.OK).entity(result).build();
	}
}
