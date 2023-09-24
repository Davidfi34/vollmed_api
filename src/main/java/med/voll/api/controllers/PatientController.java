package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.patient.*;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    //CREATE
    @PostMapping
    public ResponseEntity<ResponseDataPatient> registerPatient(@RequestBody @Valid PatientRegistrationData patientRegistrationData,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Patient patient = patientRepository.save(new Patient(patientRegistrationData));
        ResponseDataPatient responseDataPatient = new ResponseDataPatient(
                patient.getId(),
                patient.getName(),
                patient.getLast_name(),
                patient.getDni(),
                patient.getEmail(),
                patient.getPhone(),
                new AddressData(
                        patient.getAddress().getStreet(),
                        patient.getAddress().getDistrict(),
                        patient.getAddress().getCity(),
                        patient.getAddress().getNumber(),
                        patient.getAddress().getComplement()));
        URI url = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(url).body(responseDataPatient);
    }

    //GET ALL
    @GetMapping
    //pagination: ej:/doctor?size=1&page=1&sort=name
    //@PageableDefault(size:) : number of records
    public ResponseEntity<Page<GetPatient>> getAll (@PageableDefault(size = 2) Pageable pages){
      // return ResponseEntity.ok(patientRepository.findAll(pages).map(GetPatient::new)); //ALL
        return ResponseEntity.ok(patientRepository.findByActiveTrue(pages).map(GetPatient::new));//ONLY ACTIVE

    }

    //UPDATE
    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid DataUpdatePatient request) {
            Patient patient = patientRepository.getReferenceById(request.id());
            patient.updatePatientData(request);
            return ResponseEntity.ok(new ResponseDataPatient(patient.getId(),
                    patient.getName(),patient.getLast_name(),patient.getEmail(),
                    patient.getPhone(), patient.getDni(),
                    new AddressData(
                            patient.getAddress().getStreet(),
                            patient.getAddress().getDistrict(),
                            patient.getAddress().getCity(),
                            patient.getAddress().getNumber(),
                            patient.getAddress().getComplement()
                    )
            ));
    }


    //GET BY ID
    @GetMapping(path ="/{id}")
    public ResponseEntity<ResponseDataPatient> getById (@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        var patientData = new ResponseDataPatient(
                patient.getId(),
                patient.getName(),patient.getLast_name(),
                patient.getDni(),
                patient.getEmail(),
                patient.getPhone(),
                new AddressData(
                        patient.getAddress().getStreet(),
                        patient.getAddress().getDistrict(),
                        patient.getAddress().getCity(),
                        patient.getAddress().getNumber(),
                        patient.getAddress().getComplement()));
        return ResponseEntity.ok(patientData);
    }


    @DeleteMapping(path ="/{id}")
    @Transactional
    //LOGICAL DELETE
    public ResponseEntity deletePatient(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();
        return ResponseEntity.noContent().build();
    }

    //DELETE
   /* @DeleteMapping(path ="/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patientRepository.delete(patient);
    }*/


}
