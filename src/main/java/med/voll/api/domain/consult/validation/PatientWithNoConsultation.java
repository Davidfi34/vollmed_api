package med.voll.api.domain.consult.validation;


import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.infra.errors.IntegrityValidation;
import med.voll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithNoConsultation implements ConsultationsValidator {

    @Autowired
    private ConsultRepository consultRepository;

    public void validation(ConsultRegistrationData data){

        var firstTime = data.data().withHour(7);
        var lastTime = data.data().withHour(18);

        var patientWithConsultation = consultRepository.existsByPatientIdAndDataBetween(data.id_patient(),firstTime,lastTime);

        if (patientWithConsultation){
            throw new IntegrityValidation("Do not allow more than one consultation to be scheduled on the same day for the same patient.");
        }
    }
}
