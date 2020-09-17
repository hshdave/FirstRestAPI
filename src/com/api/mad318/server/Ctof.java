package com.api.mad318.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

@Path("/ctof")
public class Ctof {
	
	
	@Path("/ctofnoparam")
	@GET
	@Produces("application/json")
	public Response convertCtoF()
	{
		
		JSONObject jsonobject = new JSONObject();
		
		Double fahrenheit;
		Double celsius = 36.8;  
		
		try
		{

			fahrenheit = ((celsius * 9)/5) + 32;
			
			jsonobject.put("Celsius", celsius);
			jsonobject.put("Fahrenheit", fahrenheit);
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Eroor :"+e.getMessage());
			
			
			jsonobject.put("Error", "Something Wrong!");
			jsonobject.put("Message", e.getMessage());
			
		}
		
		
		return Response.status(200).entity(jsonobject.toString()).build();
		
	}
	
	
	@Path("/ctofwithparam/{c}")
	@GET
	@Produces("appplication/json")
	public Response convertCtoFParam(@PathParam("c") Double c)
	{
		JSONObject jsonobject = new JSONObject();
		Double fahrenheit;
		Double celsius = c;
		
		try
		{
			fahrenheit = ((celsius * 9)/5) + 32;
			
			jsonobject.put("Celsius", celsius);
			jsonobject.put("Fahrenheit", fahrenheit);
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Eroor :"+e.getMessage());
			
			
			jsonobject.put("Error", "Something Wrong!");
			jsonobject.put("Message", e.getMessage());
			
		}
		
		
		return Response.status(200).entity(jsonobject.toString()).build();
		
	}
	
	@Path("/ftoc/{f}")
	@GET
	@Produces("application/json")
	public Response convertFtoC(@PathParam("f") Double f)
	{
		JSONObject jsonobject = new JSONObject();
		Double fahrenheit = f;
		Double celsius=0.0;
		
		celsius = (fahrenheit-32) * 5 / 9;
			
		try
		{
				
			jsonobject.put("Fahrenheit", fahrenheit);
			jsonobject.put("Celsius", celsius);
					
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Eroor :"+e.getMessage());
			
			
			jsonobject.put("Error", "Something Wrong!");
			jsonobject.put("Message", e.getMessage());
			
		}
	
	
		return Response.status(200).entity(jsonobject.toString()).build();
		
		
	}
	
	@Path("/jsondata")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response jsonData()
	{
		JSONObject mainObj = new JSONObject();
		JSONArray jarray = new JSONArray();
		JSONObject childObj = new JSONObject();
		
		//First Student;
		childObj.put("id", 101);
		childObj.put("fname", "Harsh");
		childObj.put("lname", "Dave");
		childObj.put("city", "Montreal");
		
		jarray.put(childObj);
	
		
		childObj = new JSONObject();
		
		
		//Second Student;
		childObj.put("id", 102);
		childObj.put("fname", "Test");
		childObj.put("lname", "Test");
		childObj.put("city", "Toronto");
		
		jarray.put(childObj);
		
	
		mainObj.put("Students", jarray);
		
		return Response.status(200).entity(mainObj.toString()).build();
		
	}
	
	

	

}
