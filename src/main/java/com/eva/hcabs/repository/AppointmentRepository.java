package com.eva.hcabs.repository;


import com.eva.hcabs.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDateTimeBetweenAndReminderSentFalse(LocalDateTime start, LocalDateTime end);
}

