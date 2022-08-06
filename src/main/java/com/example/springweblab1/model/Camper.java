package com.example.springweblab1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table
@Data
public class Camper {
    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String camperName;
    @Min(value = 8)
    @Max(value = 18)
    private int age;
    private LocalDate created_at;
    private LocalDate updated_at;
    @OneToMany(mappedBy = "camper")
    private List<Signup> signup = new ArrayList<>();

}
