package com.eva.hcabs.controller;

import com.eva.hcabs.entity.Appointment;
import com.eva.hcabs.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/upcoming")
    public List<Appointment> getUpcomingAppointments(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return appointmentService.getUpcomingAppointments(start, end);
    }
}

