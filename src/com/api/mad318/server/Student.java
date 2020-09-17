package com.api.mad318.server;

public class Student {
	
	private int id;
	private String studentfName;
	private String studentlname;
	private String city;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentfName() {
		return studentfName;
	}
	public void setStudentfName(String studentfName) {
		this.studentfName = studentfName;
	}
	public String getStudentlname() {
		return studentlname;
	}
	public void setStudentlname(String studentlname) {
		this.studentlname = studentlname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return studentfName+" "+studentlname+" id is "+id+" and from "+city;
	}
	
	
	
	

	
	

}
