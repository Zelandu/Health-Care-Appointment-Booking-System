Since a Healthcare Appointment Booking System involves multiple components, I'll provide a structured breakdown of the essential code files:
1. Entity Classes (JPA)
These define the database structure.
Patient Entity
java
package backend.example.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
Doctor Entity
java
package backend.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
}
Appointment Entity
java
package backend.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime dateTime;
    private boolean reminderSent;
}
2. Repository Interfaces
These handle database operations.
Patient Repository
java
package backend.example.repository;

import backend.example.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
Doctor Repository
java
package backend.example.repository;

import backend.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
Appointment Repository
java
package backend.example.repository;

import backend.example.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDateTimeBetweenAndReminderSentFalse(LocalDateTime start, LocalDateTime end);
}
3. Service Layer
Handles business logic.
Appointment Service
java
package backend.example.service;

import backend.example.entity.Appointment;
import backend.example.repository.AppointmentRepository;
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
4. Controller Layer
Handles API requests.
Appointment Controller
java
package backend.example.controller;

import backend.example.entity.Appointment;
import backend.example.service.AppointmentService;
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
5. Application Configuration
application.properties
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/healthcare_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8080
6. Security Configuration (Optional)
If authentication is needed:
java
package backend.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }
}
7. Frontend (React Example)
Appointment Booking Form
jsx
import React, { useState } from "react";

const AppointmentForm = () => {
  const [dateTime, setDateTime] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    await fetch("/appointments", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ dateTime }),
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Select Date & Time:</label>
      <input type="datetime-local" value={dateTime} onChange={(e) => setDateTime(e.target.value)} />
      <button type="submit">Book Appointment</button>
    </form>
  );
};

export default AppointmentForm;
Next Steps
•	Testing: Use Postman or Swagger for API testing.
•	Deployment: Dockerize the application for production.
•	Enhancements: Add email notifications, payment integration, and admin dashboards.
Would you like help with a specific feature?

