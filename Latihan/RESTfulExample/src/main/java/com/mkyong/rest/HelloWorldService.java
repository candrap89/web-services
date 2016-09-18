package com.mkyong.rest;
 
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {
	

	

 
	@GET
	@Path("/{param}/{param2}")
	public Response getMsg(@PathParam("param") String msg, @PathParam("param2") String msg2) {
 
		String output = "Jersey say : " +  msg + " "+ msg2;
		
 
		return Response.status(200).entity(output).build();
 
	}
	
	
	
 
}