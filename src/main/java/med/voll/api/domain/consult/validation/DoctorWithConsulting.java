package med.voll.api.domain.consult.validation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.infra.errors.IntegrityValidation;
import med.voll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithConsulting implements ConsultationsValidator {

    @Autowired
    private ConsultRepository consultRepository;

    public void validation(ConsultRegistrationData data){


        if (data.id_doctor() == null) return;

        var doctorWithConsultation = consultRepository.existsByDoctorIdAndData(data.id_doctor(),data.data());

        if (doctorWithConsultation){
            throw new IntegrityValidation("this doctor already has a practice at that time");
        }
    }
}
