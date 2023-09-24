package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.DataUpdateDoctor;
import med.voll.api.domain.doctor.GetDoctor;
import med.voll.api.domain.doctor.MedicalRegistrationData;
import med.voll.api.domain.doctor.ResponseDataDoctor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.repository.DoctorRepository;



@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired//no es recomendable... para realizar en la practica
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<ResponseDataDoctor> registerDoctor(@RequestBody @Valid MedicalRegistrationData medicalData,
                                         UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor = doctorRepository.save(new Doctor(medicalData));
        ResponseDataDoctor responseDataDoctor = new ResponseDataDoctor(
                doctor.getId(),
                doctor.getName(),doctor.getEmail(),doctor.getPhone(),
                doctor.getDni(),
                new AddressData(
                        doctor.getAddress().getStreet(),
                        doctor.getAddress().getDistrict(),
                        doctor.getAddress().getCity(),
                        doctor.getAddress().getNumber(),
                        doctor.getAddress().getComplement()));
        URI url = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(responseDataDoctor);
    }

    @GetMapping
    //pagination: ej:/doctor?size=1&page=1&sort=name
    //@PageableDefault(size:) : number of records
    public ResponseEntity<Page<GetDoctor>>  getAll(@PageableDefault(size = 4) Pageable pages){
       //return doctorRepository.findAll(pages).map(GetDoctor::new); //ALL
        return ResponseEntity.ok(doctorRepository.findByActiveTrue(pages).map(GetDoctor::new));//ONLY ACTIVE
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataUpdateDoctor request) {
            Doctor doctor = doctorRepository.getReferenceById(request.id());
            doctor.updateData(request);
            return ResponseEntity.ok(new ResponseDataDoctor(doctor.getId(),
                    doctor.getName(),doctor.getEmail(),doctor.getPhone(),
                    doctor.getDni(),
                    new AddressData(
                            doctor.getAddress().getStreet(),
                            doctor.getAddress().getDistrict(),
                            doctor.getAddress().getCity(),
                            doctor.getAddress().getNumber(),
                            doctor.getAddress().getComplement()
                    )
            ));
    }


    //GET BY ID
    @GetMapping(path ="/{id}")
    public ResponseEntity<ResponseDataDoctor> getById (@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        var doctorData = new ResponseDataDoctor(
                doctor.getId(),
                doctor.getName(),doctor.getEmail(),doctor.getPhone(),
                doctor.getDni(),
                new AddressData(
                        doctor.getAddress().getStreet(),
                        doctor.getAddress().getDistrict(),
                        doctor.getAddress().getCity(),
                        doctor.getAddress().getNumber(),
                        doctor.getAddress().getComplement()));
        return ResponseEntity.ok(doctorData);
    }

    @DeleteMapping(path ="/{id}")
    @Transactional
    //LOGICAL DELETE
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deactivateDoctor();
        return ResponseEntity.noContent().build();
    }

    //DELETE IN DATABASE
   /* public void deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctorRepository.delete(doctor);
    }*/

}
