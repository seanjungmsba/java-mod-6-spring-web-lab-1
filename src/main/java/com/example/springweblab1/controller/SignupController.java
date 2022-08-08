package com.example.springweblab1.controller;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.dto.CreateSignupDTO;
import com.example.springweblab1.model.Signup;
import com.example.springweblab1.service.ActivityService;
import com.example.springweblab1.service.CamperService;
import com.example.springweblab1.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SignupController {

    @Autowired
    private SignupService signupService;
    @Autowired
    private ActivityService activityService;

    @Autowired
    private CamperService camperService;

    @PostMapping("/signups")
    public ActivityDTO createSignup(@Valid @RequestBody CreateSignupDTO signup) {
        return signupService.createSignupDTO(signup);
    }

    @GetMapping("/signups")
    // If the Signup is created successfully, send back a response with the data related to the Activity
    public List<ActivityDTO> readSignups() {
        return activityService.getActivityDTOs();
    }

    @GetMapping("/signups/{signupId}")
    // If the Signup is created successfully, send back a response with the data related to the Activity
    public ActivityDTO readSignup(@PathVariable(value = "signupId") Integer id) {
        return activityService.getActivityDTO(id);
    }

    @PutMapping("/signups/{signupId}")
    public Signup updateSignup(@PathVariable(value = "signupId") Integer id, @RequestBody Signup signupData) {
        return signupService.updateSignup(id, signupData);
    }

    @DeleteMapping("/signups/{signupId}")
    public void deleteSignup(@PathVariable(value = "signupId") Integer id) {
        signupService.deleteSignup(id);
    }

    @DeleteMapping("/signups")
    public void deleteAllSignups() {
        signupService.deleteAllSignups();
    }
}
