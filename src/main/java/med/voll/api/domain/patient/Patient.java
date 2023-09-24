package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;


@Entity
@Table(name = "patients")
@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String last_name;
    private String dni;
    private String email;
    private String phone;
    @Embedded
    private Address address;
    private Boolean active;


    public Patient(PatientRegistrationData patientData){
        this.name = patientData.name();
        this.last_name = patientData.last_name();
        this.email = patientData.email();
        this.dni = patientData.dni();
        this.phone = patientData.phone();
        this.address = new Address(patientData.address());
        this.active = true;
    }


    public void updatePatientData(DataUpdatePatient request) {
        if (request.name() != null) this.name = request.name();
        if (request.last_name() != null) this.last_name = request.last_name();
        if (request.address() != null) this.address = address.updateData(request.address());
    }


    public void deactivatePatient() {
        this.active = false;
    }

}
