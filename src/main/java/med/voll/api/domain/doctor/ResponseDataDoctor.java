package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;

public record ResponseDataDoctor(Long id, String name, String email,
                                 String phone, String dni, AddressData address) {
}
