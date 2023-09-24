package med.voll.api.domain.consult.validation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.infra.errors.IntegrityValidation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceSchedule implements ConsultationsValidator {
    public void validation(ConsultRegistrationData data){

        var now = LocalDateTime.now();
        var consultingTime = data.data();
        Boolean differenceOf30Minutes = Duration.between(now,consultingTime).toMinutes() < 30;


        if (differenceOf30Minutes){
            throw  new IntegrityValidation("Consultations have a fixed duration of 1 hour");
        }
    }

}
