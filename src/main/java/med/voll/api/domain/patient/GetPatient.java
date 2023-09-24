package med.voll.api.domain.patient;

//DTO
public record GetPatient(Long id, String name, String last_name,String dni, String email, String phone) {


    public GetPatient(Patient patient){
            this(patient.getId(),
                    patient.getName(),
                    patient.getLast_name(),
                    patient.getDni(),
                    patient.getEmail(),
                    patient.getPhone()
            );
    }
}
