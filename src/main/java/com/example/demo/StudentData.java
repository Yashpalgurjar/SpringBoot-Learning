package com.example.demo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class StudentData {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	

		    @NotBlank(message = "Name should not be blank")
		    private String name;

		    @Min(value = 18, message = "Age must be at least 18")
		    private int age;

		    @NotBlank(message = "City should not be blank")
		    private String city;

		    @Email(message = "Please enter a valid email")
		    @NotBlank(message = "Email should not be blank")
		    private String email;

		    private Long phone;
		

	
	
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
	 public Long getId() {
		    return id;
		}

		public void setId(Long id) {
		    this.id = id;
		}
	  
	 }
