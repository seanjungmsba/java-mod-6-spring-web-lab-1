package com.example.springweblab1;

import com.example.springweblab1.model.Activity;
import com.example.springweblab1.model.Camper;
import com.example.springweblab1.model.Signup;
import com.example.springweblab1.repository.ActivityRepository;
import com.example.springweblab1.repository.CamperRepository;
import com.example.springweblab1.repository.SignupRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class SpringWebCamperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebCamperApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

//    @Bean
//    @Transactional
//    CommandLineRunner runner(ActivityRepository activityRepository, CamperRepository camperRepository,
//                             SignupRepository signupRepository) {
//        return (args) -> {
//            Activity archery = activityRepository
//                    .save(new Activity(1, "Archery", 2, new Date(), new Date(), new HashSet<>()));
//            Activity swimming = activityRepository
//                    .save(new Activity(2, "Swimming", 3, new Date(), new Date(), new HashSet<>()));
//            Camper caitlin = camperRepository
//                    .save(new Camper(1, "Caitlin", 8, new Date(), new Date(), new HashSet<>()));
//            Camper lizzie = camperRepository
//                    .save(new Camper(2, "Lizzie", 9, new Date(), new Date(), new HashSet<>()));
//            Signup archerySignup = signupRepository
//                    .save(new Signup(1, 9, new Date(), new Date(), archery, caitlin));
//            Signup swimmingSignup = signupRepository
//                    .save(new Signup(2, 10, new Date(), new Date(), swimming, caitlin));
//            Set<Signup> signupList = new HashSet<>();
//            signupList.add(archerySignup);
//            signupList.add(swimmingSignup);
//            archery.setSignup(signupList);
//            caitlin.setSignup(signupList);
//        };
//    }

}
