package com.eva.hcabs.service;

import com.eva.hcabs.entity.Appointment;
import com.eva.hcabs.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getUpcomingAppointments(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDateTimeBetweenAndReminderSentFalse(start, end);
    }
}

