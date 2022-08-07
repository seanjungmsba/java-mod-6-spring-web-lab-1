package com.example.springweblab1.service;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.dto.CamperDTO;
import com.example.springweblab1.dto.SignupDTO;
import com.example.springweblab1.model.Activity;
import com.example.springweblab1.model.Camper;
import com.example.springweblab1.model.Signup;
import com.example.springweblab1.repository.ActivityRepository;
import com.example.springweblab1.repository.CamperRepository;
import com.example.springweblab1.repository.SignupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignupService {

    // The SignupRepository object is automatically created and injected by Spring into the SignupService class because of the @Autowired annotation.
    @Autowired
    SignupRepository signupRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    CamperRepository camperRepository;
    @Autowired
    private ModelMapper modelMapper;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // This method uses the default save method on the repository object to persist the member in the database.
    public Signup createSignup(Signup signup) {
        return signupRepository.save(signup);
    }

    public SignupDTO createSignupDTO(SignupDTO signupDTO, CamperDTO camperDTO, ActivityDTO activityDTO) {
        try {
            // Convert the camperDTO to a Camper entity
            Camper camper = modelMapper.map(camperDTO, Camper.class);

            // Convert the activityDTO to an Activity entity
            Activity activity = modelMapper.map(activityDTO, Activity.class);

            // Convert the SignupDTO to a Signup entity
            Signup signup = modelMapper.map(signupDTO, Signup.class);

            // signupDTO has an id and other property
            // signup has an id property
            // the mapper will create a signup with the name from the signupDTO
            signup = signupRepository.save(signup);
            // signup will now have an id and a time
            // then we map that signup entity to a SignupDTO and then return that
            return modelMapper.map(signup, SignupDTO.class);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "validation errors");
        }
    }

    // It uses the findById method on the repository to find a record with the ID of id and returns the object.
    public List<Signup> getSignups() {
        return signupRepository.findAll();
    }

    public Signup getSignup(Integer id) {
        return signupRepository.findById(id).get();
    }

    public List<SignupDTO> getSignupDTOs() {
        return signupRepository.findAll().stream().map(signup -> modelMapper.map(signup, SignupDTO.class)).collect(Collectors.toList());
    }

    public SignupDTO getSignupDTO(Integer id) {
        Signup signup =
                signupRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(signup, SignupDTO.class);
    }

    // We can’t simply use a default method for this method and have to add some custom method instead.
    // The controller will call this method with the ID of the member (id) the client wants to update and the data (memberData) to update it with.
    // We update the member instance and call the save method to persist the updated record.
    public Signup updateSignup(Integer id, Signup signupData) {
        if (!signupRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        Signup signup = signupRepository.findById(id).get();
        signup.setTime(signupData.getTime());
        signup.setActivity(signupData.getActivity());
        signup.setCamper(signupData.getCamper());
        return signupRepository.save(signup);
    }

    // It removes the member with the ID of id from the database.
    public void deleteSignup(Integer id) {
        if (signupRepository.existsById(id)) {
            signupRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
    }
    public void deleteAllSignups() {
        signupRepository.deleteAll();
    }
}
