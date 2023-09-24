package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

//DTO
public record PatientRegistrationData(
        @NotBlank
        String name,
        @NotBlank
        String last_name,
        @NotBlank
        @Pattern(regexp = "\\d{6,8}")
        String dni,
        @NotBlank
        @Email
        String email,
        @NotNull
        String phone,
        @NotNull
        @Valid
        AddressData address
) {
}
