package com.api.mad318.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/pexample")
public class PostMethodExp {

	Connection connect = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	PreparedStatement pstmt = null;

	
	JSONObject mainObj = new JSONObject();
	JSONArray jArray = new JSONArray();
	JSONObject childObj = new JSONObject();
	
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response studentRecord(Student student) {
	

		String result = "Result :" + student;
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/getCutomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ck() {

		MySQLConnection mconnection = new MySQLConnection();

		connect = mconnection.getConnection();

		try {

			stmt = connect.createStatement();
			rs = stmt.executeQuery("Select * from customers");

			while (rs.next()) {
				
				childObj = new JSONObject();
				
				childObj.accumulate("customerNumber", rs.getInt(1));
				childObj.accumulate("customerName", rs.getString("customerName"));
				childObj.accumulate("contactLastName", rs.getString("contactLastName"));
				childObj.accumulate("contactFirstName", rs.getString("contactFirstName"));
				childObj.accumulate("phone", rs.getString("phone"));
				childObj.accumulate("addressLine1", rs.getString("addressLine1"));
				childObj.accumulate("addressLine2", rs.getString("addressLine2"));
				childObj.accumulate("city", rs.getString("city"));
				childObj.accumulate("state", rs.getString("state"));
				childObj.accumulate("postalCode", rs.getString("postalCode"));
				childObj.accumulate("country", rs.getString("country"));
				childObj.accumulate("salesRepEmployeeNumber", rs.getString("salesRepEmployeeNumber"));
				childObj.accumulate("creditLimit", rs.getString("creditLimit"));
				
				jArray.put(childObj);
		
				
			}
		
			mainObj.put("Customers", jArray);
			

		} catch (SQLException e) {
			System.out.println("SQLException :" + e.getMessage());
		} finally {
			try {
				connect.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Finally Block SQLExceltion :" + e.getMessage());
			}
		}

		return Response.status(200).entity(mainObj.toString()).build();

	}
	
	
	@GET
	@Path("/getCustomerByID/{cID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerbyID(@PathParam("cID") int id)
	{
		MySQLConnection mconnection = new MySQLConnection();
		connect = mconnection.getConnection();	
		
		try
		{
			String query = "select * from customers where customerNumber="+id;
			stmt = connect.createStatement();
			rs = stmt.executeQuery(query);
			
		
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					childObj.accumulate("customerNumber", rs.getInt(1));
					childObj.accumulate("customerName", rs.getString("customerName"));
					childObj.accumulate("contactLastName", rs.getString("contactLastName"));
					childObj.accumulate("contactFirstName", rs.getString("contactFirstName"));
					childObj.accumulate("phone", rs.getString("phone"));
					childObj.accumulate("addressLine1", rs.getString("addressLine1"));
					childObj.accumulate("addressLine2", rs.getString("addressLine2"));
					childObj.accumulate("city", rs.getString("city"));
					childObj.accumulate("state", rs.getString("state"));
					childObj.accumulate("postalCode", rs.getString("postalCode"));
					childObj.accumulate("country", rs.getString("country"));
					childObj.accumulate("salesRepEmployeeNumber", rs.getString("salesRepEmployeeNumber"));
					childObj.accumulate("creditLimit", rs.getString("creditLimit"));
				}
			
			}else
				{
					childObj.accumulate("Error", "No Data Found!");
				}
					
				
			}catch (SQLException e) {
			System.out.println("SQLException :" + e.getMessage());
		} finally {
			try {
				connect.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Finally Block SQLException :" + e.getMessage());
			}
		}
			
		return Response.status(200).entity(childObj.toString()).build();
	
	}
	
	
	@POST
	@Path("/createCutomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customers customers)
	{
		MySQLConnection mconnection = new MySQLConnection();
		connect = mconnection.getConnection();	
		
		try
		{
			String query = "insert  into `customers`(`customerNumber`,`customerName`,`contactLastName`,`contactFirstName`,`phone`,`addressLine1`,`addressLine2`,`city`,`state`,`postalCode`,`country`,`salesRepEmployeeNumber`,`creditLimit`) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = connect.prepareStatement(query);
			
			pstmt.setInt(1, customers.getCustomerNumber());
			pstmt.setString(2, customers.getCustomerName());
			pstmt.setString(3, customers.getContactLastName());
			pstmt.setString(4, customers.getContactFirstName());
			pstmt.setString(5, customers.getPhone());
			pstmt.setString(6, customers.getAddressLine1());
			pstmt.setString(7, customers.getAddressLine2());
			pstmt.setString(8, customers.getCity());
			pstmt.setString(9, customers.getState());
			pstmt.setString(10, customers.getPostalCode());
			pstmt.setString(11, customers.getCountry());
			pstmt.setString(12, customers.getSalesRepEmployeeNumber());
			pstmt.setDouble(13, customers.getCreditLimit());
			
			
			int count = pstmt.executeUpdate();
			
			if(count>0)
			{
				System.out.println("Record inserted Successfully ! : "+count);
				
				mainObj.accumulate("Status Code", 201);
				mainObj.accumulate("Message", "Record Successfully added!");
			}else
			{
				mainObj.accumulate("Status Code", 500);
				mainObj.accumulate("Message", "Something went wrong!");
			}
			
			
			
			
		}catch (SQLException e) {
			System.out.println("SQLException :" + e.getErrorCode());
			
			mainObj.accumulate("Status Code", e.getSQLState());
			mainObj.accumulate("Message", e.getMessage());
		
			
		} finally {
			try {
				
				connect.close();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("Finally Block SQLException :" + e.getMessage());
			}
		}
	
		
		
		return Response.status(201).entity(mainObj.toString()).build();
	}

}
