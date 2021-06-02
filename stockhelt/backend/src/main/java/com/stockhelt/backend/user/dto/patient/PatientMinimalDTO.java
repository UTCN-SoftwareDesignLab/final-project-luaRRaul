package com.stockhelt.backend.user.dto.patient;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientMinimalDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
