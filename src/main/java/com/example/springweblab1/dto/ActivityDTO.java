package com.example.springweblab1.dto;

import com.example.springweblab1.model.Signup;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ActivityDTO {
    private int id;
    private String activityName;
    private int difficulty;
    private List<Signup> signups;
}
