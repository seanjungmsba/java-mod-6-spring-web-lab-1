package com.example.springweblab1.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
@Data
public class Activity {
    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String activityName;
    private int difficulty;
    private LocalDate created_at;
    private LocalDate updated_at;
    @OneToMany(mappedBy = "activity")
    private List<Signup> signup = new ArrayList<>();

    // List of signups and inside each of these there is a variable named activity,
    // which maps to this OneToMany relationship is mapped by activity variable

}
