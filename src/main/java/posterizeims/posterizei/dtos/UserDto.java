package posterizeims.posterizei.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "O campo 'nome' não pode estar em branco")
    private String nome;
    
    @NotBlank(message = "O campo 'cpf' não pode estar em branco")
    private String cpf;

    @NotNull(message = "O campo 'dtNascimento' é obrigatório")
    @PastOrPresent(message = "O campo 'dtNascimento' deve ser uma data no passado ou presente")
    private LocalDate dtNascimento;

    @Email(message = "O campo 'email' deve ser um endereço de email válido")
    private String email;

    @NotBlank(message = "O campo 'senha' não pode estar em branco")
    private String senha;

    @NotBlank(message = "O campo 'telefone' não pode estar em branco")
    private String telefone;
}