package com.example.springweblab1.service;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.model.Activity;
import com.example.springweblab1.model.Signup;
import com.example.springweblab1.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    // The ActivityRepository object is automatically created and injected by Spring into the ActivityService class because of the @Autowired annotation.
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ActivityDTO createActivityDTO(ActivityDTO createDTO) {

        try {
            // Convert the ActivityDTO to an Activity entity
            Activity activity = modelMapper.map(createDTO, Activity.class);
            // createDTO has an id and other property
            // activity has an id property
            // the mapper will create a signup with the name from the createDTO
            activity = activityRepository.save(activity);
            // signup will now have an id and a time
            // then we map that signup entity to a ActivityDTO and then return that
            return modelMapper.map(activity, ActivityDTO.class);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "validation errors");
        }

    }

    public List<ActivityDTO> getActivityDTOs() {
        return activityRepository.findAll().stream().map(activity -> modelMapper.map(activity, ActivityDTO.class)).collect(Collectors.toList());
    }

    public ActivityDTO getActivityDTO(Integer id) {
        Activity activity =
                activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(activity, ActivityDTO.class);
    }

    public Activity updateActivity(Integer id, Activity activityData) {
        if (!activityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        Activity activity = activityRepository.findById(id).get();
        activity.setSignup(activityData.getSignup());
        activity.setDifficulty(activityData.getDifficulty());
        activity.setUpdated_at(activityData.getCreated_at());
        return activityRepository.save(activity);
    }

    public void deleteActivity(Integer id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
    }
    public void deleteAllActivities() {
        activityRepository.deleteAll();
    }

}
