package posterizeims.posterizei.domain.users;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String addressCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String country;

    public Address(String addressCode) {
        var addressData = new RestTemplate().getForEntity("https://viacep.com.br/ws/" + addressCode + "/json/", AddressData.class).getBody();
        if(addressData.erro() == true){
            throw new RuntimeException("Cep inválido.");
        }
        this.addressCode = addressData.cep();
        this.street = addressData.logradouro();
        this.neighborhood = addressData.bairro();
        this.city = addressData.localidade();
        this.state = addressData.uf();
        this.country = "BRASIL";
    }
}
