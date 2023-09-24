package med.voll.api.service;

import med.voll.api.domain.consult.Consult;
import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.DataDetailConsult;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Speciality;
import med.voll.api.infra.errors.IntegrityValidation;
import med.voll.api.repository.ConsultRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveConsultRepository {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    List<ConsultationsValidator>validators;

    public DataDetailConsult saveConsult(ConsultRegistrationData consultData){

        if (!patientRepository.findById(consultData.id_patient()).isPresent() ){
                throw new IntegrityValidation("patient not found");
        }
        if (consultData.id_doctor() != null && !doctorRepository.existsById(consultData.id_doctor()) ){
            throw new IntegrityValidation("doctor not found");
        }

        //VALID
        validators.forEach(v->v.validation(consultData));

        var doctor = selectDoctor(consultData);

        if (doctor == null){
            throw new IntegrityValidation("there are no doctors available for this schedule");
        }

        var patient = patientRepository.findById(consultData.id_patient()).get();
        var consult = new Consult(null,doctor,patient,consultData.data());
        consultRepository.save(consult);
        return new DataDetailConsult(consult);

    }

    private Doctor selectDoctor(ConsultRegistrationData consultData) {
        if (consultData.id_doctor() != null ){
            return doctorRepository.getReferenceById(consultData.id_doctor());
        }
        if (consultData.speciality() == null){
            System.out.println("especialidad: "+consultData.speciality());
            throw new IntegrityValidation("you must select a specialty for the doctor");
        }


       // return  doctorRepository.getReferenceById(1l);
       return doctorRepository.findRandomDoctorWithSpecialityNotInConsult(consultData.speciality(),consultData.data());
        //return doctorRepository.findRandomDoctorWithSpecialityNotInConsult(consultData.id_doctor());


    }

}
