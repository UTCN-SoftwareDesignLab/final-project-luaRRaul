package com.stockhelt.backend.user;

import com.stockhelt.backend.report.ReportService;
import com.stockhelt.backend.report.ReportServiceFactory;
import com.stockhelt.backend.report.ReportType;
import com.stockhelt.backend.user.dto.*;
import com.stockhelt.backend.user.dto.patient.PatientDTO;
import com.stockhelt.backend.user.dto.patient.PatientMinimalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.stockhelt.backend.UrlMapping.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> allUsers() {
        return userService.allUsersForList();
    }

    @GetMapping(DOCTORS)
    public List<DoctorDTO> allDoctors(){
       return userService.allDoctors();
    }
    @PostMapping
    public UserDTO create(@RequestBody UserCreationDTO user){
        return userService.create(user);
    }

    @PutMapping(ENTITY)//full edit
    public UserDTO edit(@PathVariable Long id, @RequestBody UserDTO user){
        return userService.edit(id, user);
    }

    @PatchMapping(ENTITY)//partial edit
    public UserDTO changePassword(@PathVariable Long id, @RequestBody String newPassword){
        return userService.changePassword(id, newPassword);
    }

    @GetMapping(ENTITY)
    public UserDTO getUser(@PathVariable Long id){
        return userService.get(id);
    }

    @DeleteMapping(ENTITY)
    public void deleteUser(@PathVariable Long id)
    {
        userService.delete(id);
    }


    //PATIENTS
    @GetMapping(PATIENTS)
    public List<PatientMinimalDTO> allPatients(){
        return userService.allPatientsForList();
    }

    @GetMapping(PATIENTS+ENTITY)
    public PatientDTO getPatient(@PathVariable Long id) {
        return userService.getPatientById(id);
    }

    @PutMapping(PATIENTS+ENTITY)
    public PatientDTO editPatient(@PathVariable Long id, @RequestBody PatientDTO patient){
        return userService.editPatient(id, patient);
    }

    //REPORT
    private final ReportServiceFactory reportServiceFactory;
    @GetMapping(EXPORT_REPORT)
    public ResponseEntity<?> generateReport(@PathVariable ReportType type){
        ByteArrayOutputStream toExport = reportServiceFactory.getReportService(type).export();
        ByteArrayResource byteArrayResource = new ByteArrayResource(toExport.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        if(type.equals(ReportType.PDF)) {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report.pdf");
        }else{
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report.csv");
        }

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayResource);
    }
}
