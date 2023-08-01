package posterizeims.posterizei.security;

import java.util.UUID;

public record TokenData(
        UUID id,
        String token

) {
}
