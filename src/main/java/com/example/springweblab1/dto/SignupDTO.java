package com.example.springweblab1.dto;

import com.example.springweblab1.model.Activity;
import com.example.springweblab1.model.Camper;
import lombok.Data;

@Data
public class SignupDTO {
    private int id;
    private int time;
    private Activity activity;
    private Camper camper;
}
