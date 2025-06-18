package com.eva.hcabs.controller;

import com.eva.hcabs.entity.Doctor;
import com.eva.hcabs.repository.DoctorRepository;
import com.eva.hcabs.entity.Appointment;
import com.eva.hcabs.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
