package com.example.springweblab1.service;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.dto.SignupDTO;
import com.example.springweblab1.model.Activity;
import com.example.springweblab1.model.Signup;
import com.example.springweblab1.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ActivityService {

    // The ActivityRepository object is automatically created and injected by Spring into the ActivityService class because of the @Autowired annotation.
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    private ModelMapper mapper;

    // This method uses the default save method on the repository object to persist the member in the database.
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public ActivityDTO createActivityDTO(ActivityDTO createDTO) {
        // Convert the ActivityDTO to an Activity entity
        Activity activity = mapper.map(createDTO, Activity.class);
        // createDTO has an id and other property
        // activity has an id property
        // the mapper will create a signup with the name from the createDTO
        activity = activityRepository.save(activity);
        // signup will now have an id and a time
        // then we map that signup entity to a ActivityDTO and then return that
        return mapper.map(activity, ActivityDTO.class);
    }

    // It uses the findById method on the repository to find a record with the ID of id and returns the object.
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }
    public Activity getActivity(Integer id) {
        return activityRepository.findById(id).get();
    }

    public List<ActivityDTO> getActivityDTOs() {
        return (List<ActivityDTO>) activityRepository.findAll().stream().map(activity -> mapper.map(activity, ActivityDTO.class));
    }

    public ActivityDTO getActivityDTO(Integer id) {
        Activity activity =
                activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.map(activity, ActivityDTO.class);
    }

    // We can’t simply use a default method for this method and have to add some custom method instead.
    // The controller will call this method with the ID of the member (id) the client wants to update and the data (memberData) to update it with.
    // We update the member instance and call the save method to persist the updated record.
    public Activity updateActivity(Integer id, Activity activityData) {
        if (!activityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Activity activity = activityRepository.findById(id).get();
        activity.setActivityName(activityData.getActivityName());
        activity.setDifficulty(activityData.getDifficulty());
        activity.setSignup(activityData.getSignup());
        return activityRepository.save(activity);
    }

    // It removes the member with the ID of id from the database.
    public void deleteActivity(Integer id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
