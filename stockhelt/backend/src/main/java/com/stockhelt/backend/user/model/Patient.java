package com.stockhelt.backend.user.model;

import com.stockhelt.backend.appointment.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Patient extends User {
    @Column(length = 32)
    private String firstName;

    @Column(length = 16)
    private String lastName;

    @Column(length = 32)
    private String CNP;

    @Column()
    private Date dateOfBirth;

    @Column(length = 32)
    private String address;
}
