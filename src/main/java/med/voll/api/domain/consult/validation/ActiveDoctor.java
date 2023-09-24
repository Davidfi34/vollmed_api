package med.voll.api.domain.consult.validation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.infra.errors.IntegrityValidation;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveDoctor implements ConsultationsValidator {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validation(ConsultRegistrationData data){

        if(data.id_doctor() == null){
            return;
        }
        var activeDoctor = doctorRepository.findActiveById(data.id_doctor());

        if (!activeDoctor){
            throw new IntegrityValidation("Do not allow to schedule appointments with inactive doctor in the system.");
        }

    }
}

