package med.voll.api.repository;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByActiveTrue(Pageable pageable);//ONLY ACTIVE : TRUE

    @Query("""
            select c.active 
            from Doctor c
            where c.id=:id_doctor
            """)
    Boolean findActiveById(Long id_doctor);



   /* @Query("""
            SELECT d FROM Doctor AS d
            WHERE d.active = 1 
            AND d.speciality=:speciality
            AND d.id NOT IN (
                SELECT c.id_doctor FROM Consult AS c WHERE c.data=:data) 
            ORDER BY RAND() LIMIT 1
            """)
    Doctor findRandomDoctorWithSpecialityNotInConsult(Speciality speciality,LocalDateTime data);*/

    /*
    @Query("""
            select d from Doctor d
            where d.active= 1
            and
            d.speciality=:speciality
            and
            d.id not in(
                select c.id_doctor from Consult c
                where
                c.data=:data
            )
            order by rand()
            limit 1
            """)
     */

    @Query(value = "SELECT * FROM doctor d " +
            "WHERE d.active = 1 " +
            "AND d.speciality = :speciality " +
            "AND d.id NOT IN (" +
            "SELECT c.id_doctor FROM consult c WHERE c.data = :data" +
            ") " +
            "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Doctor findRandomDoctorWithSpecialityNotInConsult(@Param("speciality") Speciality speciality, @Param("data") LocalDateTime data);




}
