package com.stockhelt.backend.appointment.dto;


import com.stockhelt.backend.user.dto.DoctorDTO;
import com.stockhelt.backend.user.dto.patient.PatientMinimalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentMinimalDTO {
    private Long id;
    private DoctorDTO doctor;
    private PatientMinimalDTO patient;
    private Date date;
    private int duration;
}
