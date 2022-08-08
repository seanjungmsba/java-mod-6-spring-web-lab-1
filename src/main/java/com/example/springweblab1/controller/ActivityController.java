package com.example.springweblab1.controller;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.model.Activity;
import com.example.springweblab1.repository.ActivityRepository;
import com.example.springweblab1.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/activities")
    public ActivityDTO createActivity(@Valid @RequestBody ActivityDTO activity) {
        return activityService.createActivityDTO(activity);
    }

    @GetMapping("/activities")
    public List<ActivityDTO> readActivities() {
        return activityService.getActivityDTOs();
    }

    @GetMapping("/activities/{activityId}")
    public ActivityDTO readActivity(@PathVariable(value = "activityId") Integer id) {
        return activityService.getActivityDTO(id);
    }

    @PutMapping("/activities/{activityId}")
    public Activity updateActivity(@PathVariable(value = "activityId") Integer id, @RequestBody Activity activityData) {
        return activityService.updateActivity(id, activityData);
    }

    @DeleteMapping("/activities/{activityId}")
    public void deleteActivity(@PathVariable(value = "activityId") Integer id) {
        activityService.deleteActivity(id);
    }

    @DeleteMapping("/activities")
    public void deleteAllActivities() {
        activityService.deleteAllActivities();
    }

}
