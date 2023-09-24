package med.voll.api.domain.consult;

import java.time.LocalDateTime;

public record ResponseDataConsult(Long id, Long id_doctor, Long id_patient, LocalDateTime data) {
}
