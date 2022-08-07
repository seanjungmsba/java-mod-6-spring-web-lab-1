package com.example.springweblab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signup {
    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 0)
    @Max(value = 23)
    private int time;
    private LocalDate created_at;
    private LocalDate updated_at;
    @ManyToOne
    private Activity activity;
    @ManyToOne
    private Camper camper;

}
