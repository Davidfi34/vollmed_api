package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String number;
    private String district;
    private String complement;
    private String city;



    public Address(AddressData address){
        this.street = address.street();
        this.number = address.number();
        this.district = address.district();
        this.complement = address.complement();
        this.city = address.city();
    }

    public Address updateData(AddressData request) {
        this.street = request.street();
        this.number = request.number();
        this.district = request.district();
        this.complement = request.complement();
        this.city = request.city();
        return this;
    }
}
