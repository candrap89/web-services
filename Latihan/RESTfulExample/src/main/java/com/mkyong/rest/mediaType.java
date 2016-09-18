package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/media")
public class mediaType {

	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?><Hello> Hello jersey</Hello>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLhello() {
		return "<html>"+"<title>" + "Hello Jersey" + "</titel>" 
				+ "<Body>"+"<h1>"+ "Hallo jersey" +"</h1>" +"</Body>";
	}
	
}
