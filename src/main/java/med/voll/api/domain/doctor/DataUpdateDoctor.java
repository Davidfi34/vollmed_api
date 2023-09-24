package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;


//DTO
public record DataUpdateDoctor(
        @NotNull
        Long id,
        String name,
        String dni,
        AddressData address
) {
}
