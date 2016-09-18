package com.mkyong.rest;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

 
@Path("/calc")
public class Calculator {
 
	@GET
	@Path("/{param}/{param2}")
	public Response getMsg(@PathParam("param") Integer msg, @PathParam("param2") Integer msg2) {
 
		String output = "hasil penjumlahan : " + (msg + msg2);
		
 
		return Response.status(200).entity(output).build();
 
	}
	

	
 
}