package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;

@Entity
@Table(name = "doctors")
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String dni;
    private String phone;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    public Doctor(MedicalRegistrationData medicalData){
        this.name = medicalData.name();
        this.email = medicalData.email();
        this.dni = medicalData.dni();
        this.phone = medicalData.phone();
        this.active = true;
        this.speciality = medicalData.speciality();
        this.address = new Address(medicalData.address());
    }

    public void updateData(DataUpdateDoctor request) {
        if (request.name() != null) this.name = request.name();
        if (request.dni() != null) this.dni = request.dni();
        if (request.address() != null) this.address = address.updateData(request.address());
    }

    public void deactivateDoctor() {
        this.active = false;
    }
}
