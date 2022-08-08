package com.example.springweblab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Temporal(TemporalType.DATE)
    private Date created_at;
    @Temporal(TemporalType.DATE)
    private Date updated_at;
    @OneToMany(mappedBy = "camper")
    private Set<Signup> signup = new HashSet<>();

}
