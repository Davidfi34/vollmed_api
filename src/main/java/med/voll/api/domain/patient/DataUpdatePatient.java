package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;


//DTO
public record DataUpdatePatient(
        @NotNull
        Long id,
        String name,
        String last_name,
        String dni,
        String phone,
        AddressData address
) {
}
