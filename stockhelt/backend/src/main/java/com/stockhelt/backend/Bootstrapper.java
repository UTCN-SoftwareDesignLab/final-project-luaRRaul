package com.stockhelt.backend;

import com.stockhelt.backend.appointment.AppointmentService;
import com.stockhelt.backend.appointment.dto.AppointmentDTO;
import com.stockhelt.backend.security.AuthService;
import com.stockhelt.backend.security.dto.SignupRequest;
import com.stockhelt.backend.user.UserService;
import com.stockhelt.backend.user.dto.DoctorDTO;
import com.stockhelt.backend.user.dto.patient.PatientMinimalDTO;
import com.stockhelt.backend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final AuthService authService;

    private final AppointmentService appointmentService;


    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            authService.register(SignupRequest.builder()
                    .email("raul.romitan@gmail.com")
                    .username("root")
                    .password("root123.")
                    .build());
            authService.registerDoctor(SignupRequest.builder()
                    .email("doc@doc.com")
                    .username("doc")
                    .password("doctor123.")
                    .build());
            authService.registerSecretary(SignupRequest.builder()
                    .email("sec@sec.com")
                    .username("sec")
                    .password("secretary123.")
                    .build());
            authService.registerPatient(SignupRequest.builder()
                    .email("pat@pat.com")
                    .username("pat")
                    .password("patient123.")
                    .build());


            appointmentService.create(
                    AppointmentDTO.builder()
                    .date(new Date())
                    .duration(60)
                    .description("hello dr")
                    .patient(PatientMinimalDTO.builder().id(4L).build())
                    .doctor(DoctorDTO.builder().id(2L).build())
                    .build()
            );
        }
    }
}
