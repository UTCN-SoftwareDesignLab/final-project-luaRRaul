package com.stockhelt.backend.user.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String CNP;
    private Date dateOfBirth;
    private String address;
}
