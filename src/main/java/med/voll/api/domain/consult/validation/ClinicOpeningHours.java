package med.voll.api.domain.consult.validation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consult.ConsultRegistrationData;
import med.voll.api.domain.consult.validation.inteface.ConsultationsValidator;
import med.voll.api.infra.errors.IntegrityValidation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOpeningHours implements ConsultationsValidator {


    public void validation(ConsultRegistrationData data){

        var sunday = DayOfWeek.SUNDAY.equals(data.data().getDayOfWeek());
        Boolean beforeOpening = data.data().getHour() < 7;
        Boolean afterClosing =data.data().getHour() >= 19;

        if (sunday ||  beforeOpening || afterClosing){
            throw new IntegrityValidation("The clinic is open from Monday to Saturday, from 07:00 to 19:00 hours.");
        }
    }


    /*

       var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());

       var antesdDeApertura=datos.fecha().getHour()<7;
       var despuesDeCierre=datos.fecha().getHour()>19;
       if(domingo || antesdDeApertura || despuesDeCierre){
           throw  new ValidationException("El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
       }
   }
     */

}
