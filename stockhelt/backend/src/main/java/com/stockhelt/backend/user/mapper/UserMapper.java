package com.stockhelt.backend.user.mapper;

import com.stockhelt.backend.user.dto.*;
import com.stockhelt.backend.user.dto.patient.PatientDTO;
import com.stockhelt.backend.user.dto.patient.PatientMinimalDTO;
import com.stockhelt.backend.user.model.Doctor;
import com.stockhelt.backend.user.model.Patient;
import com.stockhelt.backend.user.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMinimalDTO toMinimalDto(User user);

    @Mappings(
            @Mapping(target = "role", ignore = true)
    )
    UserDTO toDto(User user);

    User fromDto(UserDTO user);

    User fromCreationDto(UserCreationDTO user);

    @AfterMapping
    default void populateRole(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setRole(user.getClass().getSimpleName());
    }

    PatientDTO patientToDto(Patient patient);

    PatientMinimalDTO patientToMinimalDto(Patient patient);

    Patient patientFromDto(PatientDTO patient);

    Patient patientFromMinimalDto(PatientMinimalDTO patient);

    Doctor docFromUserCreationDto(UserCreationDTO user);

    DoctorDTO doctorToDto(Doctor doctor);
}
