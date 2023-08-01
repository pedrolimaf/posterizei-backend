package posterizeims.posterizei.domain.users;

public record AddressData(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi,

        boolean erro
) {
}
