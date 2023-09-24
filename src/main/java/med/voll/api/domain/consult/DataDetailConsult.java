package med.voll.api.domain.consult;

import java.time.LocalDateTime;

public record DataDetailConsult(Long id, Long id_patient, Long id_doctor, LocalDateTime data) {
    public DataDetailConsult(Consult consult) {
        this(consult.getId(),consult.getPatient().getId(),consult.getDoctor().getId(),consult.getData());
    }
}