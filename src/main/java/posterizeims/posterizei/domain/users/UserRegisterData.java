package posterizeims.posterizei.domain.users;

import jakarta.validation.constraints.*;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDate;

public record UserRegisterData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String phone,
        @Past
        @NotNull
        LocalDate birthday,
        @NotBlank
        String password,
        @Pattern(regexp = "\\d{8}")
        @NotBlank
        String addressCode
) {
}
