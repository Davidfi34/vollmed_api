package med.voll.api.domain.consult.validation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.infra.errors.IntegrityValidation;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatient implements ConsultationsValidator {
    @Autowired
    private PatientRepository patientRepository;

    public void validation(ConsultRegistrationData data){

        if(data.id_patient() == null){
            return;
        }
        var activePatient = patientRepository.findActiveById(data.id_patient());

        if (!activePatient){
            throw new IntegrityValidation("Do not allow to schedule appointments with inactive patients in the system.");
        }

    }
}
