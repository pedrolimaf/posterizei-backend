package posterizeims.posterizei.users;

import jakarta.validation.constraints.*;

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
        @Pattern(regexp = "\\d{6,8}")
        String password
) {
}
