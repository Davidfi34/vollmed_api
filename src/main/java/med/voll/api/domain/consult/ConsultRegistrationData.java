package med.voll.api.domain.consult;

import jakarta.validation.constraints.*;
import med.voll.api.domain.doctor.Speciality;

import java.time.LocalDateTime;

//DTO
public record ConsultRegistrationData(

        @NotNull
        Long id_patient,
        Long id_doctor,
        @NotNull
        @Future
        LocalDateTime data,
        Speciality speciality

) {
}
