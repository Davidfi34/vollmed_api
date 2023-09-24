package med.voll.api.repository;

import med.voll.api.domain.consult.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    Boolean existsByPatientIdAndDataBetween(Long id_patient, LocalDateTime firstTime, LocalDateTime lastTime);
    Boolean existsByDoctorIdAndData(Long id_doctor, LocalDateTime data);
}
