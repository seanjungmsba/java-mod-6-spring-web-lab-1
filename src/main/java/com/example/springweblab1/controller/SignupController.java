package com.example.springweblab1.controller;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.dto.SignupDTO;
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

    // SignupService Injection
    // The SignupService object is injected automatically by Spring into the controller, so we can use it in the class.
    @Autowired
    private SignupService signupService;
    @Autowired
    private ActivityService activityService;

    @Autowired
    private CamperService camperService;

    //////////////////////////////////////////////////////////////

    // This method is creating the /api/signups endpoint for the POST HTTP method and persisting the data sent from the client.
    // The @PostMapping annotation is a shorthand for the @RequestMapping(value="/signups", method=RequestMethod.POST) annotation.
    // Both of these annotations define the endpoint path as /api/signups
//    @PostMapping("/signups")
//    public ResponseEntity<Signup> createSignup(@Valid @RequestBody Signup signup) {
//        Signup newSignup = signupService.createSignup(signup);
//        // Notice that we are returning a ResponseEntity instead of a Member object like earlier.
//        // The ResponseEntity allows us to modify response information (status code, headers) before sending them back to the client.
//        return ResponseEntity.ok(newSignup);
//    }
    @PostMapping("/signups")
    public SignupDTO createSignup(@Valid @RequestBody SignupDTO signup, @Valid @RequestBody CamperDTO camper, @Valid @RequestBody ActivityDTO activity) {
        return signupService.createSignupDTO(signup, camper, activity);
    }

    // The @GetMapping is a shorthand for the @RequestMapping(value="/signups", method=RequestMethod.GET) annotation.
    // Both of these annotations define the endpoint path as /api/signups.
    // The readSignups method calls the signupService to retrieve all the signup records from the database and returns a JSON
//    @GetMapping("/signups")
//    public List<Signup> readSignups() {
//        return signupService.getSignups();
//    }

    @GetMapping("/signups")
    // If the Signup is created successfully, send back a response with the data related to the Activity
    public List<ActivityDTO> readSignups() {
        return activityService.getActivityDTOs();
    }

    // The {signupId} in the @GetMapping value is a dynamic value which takes on the value of the client’s request path parameter.
    // For example, the following URL would assign the value of 1 to signupId and id:
    // http://localhost:8080/api/signups/1
    // The @PathVariable annotation maps the dynamic path value to the method parameter id.
//    @GetMapping("/signups/{signupId}")
//    public Signup readSignup(@PathVariable(value = "signupId") Integer id) {
//        return signupService.getSignup(id);
//    }
    @GetMapping("/signups/{signupId}")
    // If the Signup is created successfully, send back a response with the data related to the Activity
    public ActivityDTO readSignup(@PathVariable(value = "signupId") Integer id) {
        return activityService.getActivityDTO(id);
    }

    // The @PutMapping is a shorthand for the @RequestMapping(value="/signups/{signupId}", method=RequestMethod.PUT).
    // This method is using @PathVariable to get the ID of the record that needs to be updated and @RequestBody to get the data sent by the client.
    @PutMapping("/signups/{signupId}")
    public Signup updateSignup(@PathVariable(value = "signupId") Integer id, @RequestBody Signup signupData) {
        return signupService.updateSignup(id, signupData);
    }

    // The @DeleteMapping is a shorthand for the @RequestMapping(value="/signups/{signupId}", method=RequestMethod.DELETE) annotation.
    // This method calls the deleteSignup method on the SignupService class which removes the record with the given ID from the database.
    @DeleteMapping("/signups/{signupId}")
    public void deleteSignup(@PathVariable(value = "signupId") Integer id) {
        signupService.deleteSignup(id);
    }

    @DeleteMapping("/signups")
    public void deleteAllSignups() {
        signupService.deleteAllSignups();
    }
}
