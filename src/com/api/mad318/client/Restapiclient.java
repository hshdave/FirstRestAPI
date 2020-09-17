package com.api.mad318.client;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;	

public class Restapiclient {
	
	Client client = Client.create();
	String url = "http://localhost:8080/FirstRestAPI/api/pexample/post";
	String jsonurl = "http://localhost:8080/FirstRestAPI/api/ctof/jsondata";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Restapiclient restapiclient = new Restapiclient();
			
		//	restapiclient.postRequest();
			
			restapiclient.getRequest();

	}

	
	public void postRequest()
	{
		WebResource webResource = client.resource(url);
		String input = "{\r\n" + 
				"   \"id\":1,\r\n" + 
				"   \"studentfName\":\"Harsh\",\r\n" + 
				"   \"studentlname\":\"Dave\",\r\n" + 
				"   \"city\":\"Montreal\"\r\n" + 
				"}";
		
	/*	
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("id", 1);
		jsonObject.put("studentfName", "Test");
		jsonObject.put("studentlname", "check");
		jsonObject.put("city", "M");
*/
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,input);
		
		if(response.getStatus() != 201)
		{
			throw new RuntimeException("Http Error :"+response.getStatus());
		}
		
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);
	
		
	}
	
	public void getRequest()
	{
		WebResource webResource = client.resource(jsonurl);	
		
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if(response.getStatus() != 200)
		{
			throw new RuntimeException("Http Error :"+response.getStatus());
		}
		
		String result = response.getEntity(String.class);
		/*System.out.println("Response from the Server: ");
		System.out.println(result);*/
		
		JSONObject mainobj = new JSONObject(result);
		
		//Print Main JsonObject
		System.out.println(mainobj);
		
		JSONArray studentArray = mainobj.getJSONArray("Students");
		
		//Print Student JSON Array 
		System.out.println(studentArray);
		
		for(int i=0;i<studentArray.length();i++)
		{
			JSONObject chilobj = studentArray.getJSONObject(i);
			
			System.out.println(i+") First Name : "+chilobj.getString("fname"));
		}
		
	}
	
}
