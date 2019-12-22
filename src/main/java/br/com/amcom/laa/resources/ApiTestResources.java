package br.com.amcom.laa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class ApiTestResources {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
		return "shazam";
    }

}
