package com.example.springweblab1.dto;

import com.example.springweblab1.model.Signup;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CamperDTO {
    private int id;
    private String camperName;
    private int age;
    private List<Signup> signups = new ArrayList<>();
//    private List<Activity> activities;
}
