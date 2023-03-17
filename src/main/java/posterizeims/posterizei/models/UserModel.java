package posterizeims.posterizei.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USERS")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;
	private String cpf;
	private String nome;
	private LocalDate dtNascimento;
	private String email;
	private String senha;
	private boolean indicadorValidacaoEmail;
	private LocalDateTime dataCriacao;
	private String telefone;
	private String status;
}
