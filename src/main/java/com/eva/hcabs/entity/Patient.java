package com.eva.hcabs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Patient patient;
    private List<Patient> patients = new ArrayList<>();



}
