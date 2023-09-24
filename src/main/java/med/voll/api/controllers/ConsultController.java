package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import med.voll.api.domain.consult.ConsultRegistrationData;

import med.voll.api.domain.consult.DataDetailConsult;
import med.voll.api.service.SaveConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consult")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    @Autowired
    private SaveConsultRepository saveConsultRepository;


    @PostMapping
    @Transactional
    public ResponseEntity registerConsult(@RequestBody @Valid ConsultRegistrationData consultData){

        var response = saveConsultRepository.saveConsult(consultData);
        return ResponseEntity.ok(response);

    }

}
