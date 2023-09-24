package med.voll.api.repository;

import med.voll.api.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByActiveTrue(Pageable pageable); // Solo activos: TRUE

   @Query("""
            select c.active 
            from Patient c
            where c.id=:id_patient
            """)
    Boolean findActiveById(Long id_patient);
}
