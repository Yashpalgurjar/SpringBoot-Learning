package com.example.demo.dto;

public class StudentSummaryDTO {

    private String name;
    private String city;

    public StudentSummaryDTO(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}