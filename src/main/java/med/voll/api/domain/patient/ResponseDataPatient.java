package med.voll.api.domain.patient;

import med.voll.api.domain.address.AddressData;

public record ResponseDataPatient(Long id, String name, String last_name,String dni,String email,
                                  String phone, AddressData address) {
}
