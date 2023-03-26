package posterizeims.posterizei.users;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateUserData(
        @NotNull
        UUID id,
        String name,
        String email,
        String phone,
        LocalDate birthday
) {
}
