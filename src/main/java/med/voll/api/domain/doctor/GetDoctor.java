package med.voll.api.domain.doctor;

//DTO
public record GetDoctor(Long id, String name, String dni, String email , String speciality) {


    public GetDoctor(Doctor doctor){
            this(doctor.getId(),
                    doctor.getName(),
                    doctor.getDni(),
                    doctor.getEmail(),
                    doctor.getSpeciality().toString());
    }
}
