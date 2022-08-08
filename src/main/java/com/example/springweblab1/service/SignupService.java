package com.example.springweblab1.service;

import com.example.springweblab1.dto.ActivityDTO;
import com.example.springweblab1.dto.CreateSignupDTO;
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

import java.util.Date;
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

    public Signup createSignup(Signup signup) {
        return signupRepository.save(signup);
    }

    public ActivityDTO createSignupDTO(CreateSignupDTO createSignupDTO) {
        Activity activity = activityRepository.findById(createSignupDTO.getActivity_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"validation errors"));
        Camper camper = camperRepository.findById(createSignupDTO.getCamper_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"validation errors"));
        Signup signup = modelMapper.map(createSignupDTO, Signup.class);
        camper.getSignup().add(signup);
        activity.getSignup().add(signup);
        signup.setActivity(activity);
        signup.setCamper(camper);
        camper.setUpdated_at(new Date());
        activity.setUpdated_at(new Date());
        signup.setUpdated_at(new Date());

        signupRepository.save(signup);
        camperRepository.save(camper);
        activityRepository.save(activity);

        return modelMapper.map(activity,ActivityDTO.class);
    }

    public List<Signup> getSignups() {
        return signupRepository.findAll();
    }

    public Signup getSignup(Integer id) {
        return signupRepository.findById(id).get();
    }

    public List<CreateSignupDTO> getSignupDTOs() {
        return signupRepository.findAll().stream().map(signup -> modelMapper.map(signup, CreateSignupDTO.class)).collect(Collectors.toList());
    }

    public CreateSignupDTO getSignupDTO(Integer id) {
        Signup signup =
                signupRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(signup, CreateSignupDTO.class);
    }

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
