package com.example.demo;

public class StudentData {
	
	private String name;
	private int  age ;
	private String city ;
	private String email;
	private long  phone;
	
	 public StudentData() {
}

	 public StudentData(String name, int age, String city, String email, long phone) {
	        this.name = name;
	        this.age = age;
	        this.city = city;
	        this.email = email;
	        this.phone = phone;
	    }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public int getAge() {
		 return age;
	 }

	 public void setAge(int age) {
		 this.age = age;
	 }

	 public String getCity() {
		 return city;
	 }

	 public void setCity(String city) {
		 this.city = city;
	 }

	 public String getEmail() {
		 return email;
	 }

	 public void setEmail(String email) {
		 this.email = email;
	 }

	 public long getPhone() {
		 return phone;
	 }

	 public void setPhone(long phone) {
		 this.phone = phone;
	 }
}