package com.mkyong.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/list")
@com.sun.jersey.spi.resource.Singleton
public class cobaList {

	   public	List<String> items = new ArrayList<String>();
	   
	   public String Addmember (String mmber) {
		   
		   items.add(mmber);
		   
		   return "OK";
	   }
	   
	   public String GetItem(Integer mmber){
		   
		   String hasil = "member : "+items.get(mmber);
		   
		   return hasil;
	   }
/////////////////////////////////////web service method//////////////////////////////////////////	   
	   
	    @GET
		@Path("/add/{param}")
	    public String setMember(@PathParam("param") String msg){
	    	
	    	items.add(msg);
	    	String output = "Result OK";
	    	System.out.println(items.get(0));
	    	return output;
	    }
	    
	    
	    @GET
		@Path("/all")
	    public String GetAll(){
	    	String output = "<?xml version=\"1.0\"?>";
	    	int i = 0;
	    	
	    	for (String item : items){
	    		i++;
	    		output = output+"<index>"+i+"</index><item>"+item+"</item>"+"\n"; 
	    	}
	    	
	    	
	    	
	    	return output;
	    }
	    
	    @GET
	    @Path("/get/{param}")
	    public String GetMember(@PathParam("param") Integer member){
	    	
	    	String output = "<item>"+items.get(member)+"</item>";  

	    	return output; 	
	    	
	    }
	    
	    @GET
	    @Path("/removeAll")
	    public String ClearArray(){
	    	
	    	items.clear();
	    	
	    	return "List empety";
	    }
	    
	   
	
}
