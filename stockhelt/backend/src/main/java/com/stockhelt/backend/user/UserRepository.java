package com.stockhelt.backend.user;

import com.stockhelt.backend.user.model.Doctor;
import com.stockhelt.backend.user.model.Patient;
import com.stockhelt.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Doctor findDoctorByUsername(String doctorName);

    Doctor findDoctorById(Long id);

    @Query(value = "SELECT * FROM user where user_type = 'Doctor' ", nativeQuery = true)
    List<Doctor> findAllDoctors();

    @Query(value = "SELECT * FROM user where user_type = 'Patient' ", nativeQuery = true)
    List<Patient> findAllPatients();

    Optional<Patient> findPatientById(Long id);
}
