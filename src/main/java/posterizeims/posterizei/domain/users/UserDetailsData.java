package posterizeims.posterizei.domain.users;

import java.time.LocalDate;
import java.util.UUID;

public record UserDetailsData(
        UUID id,
        String name,
        String email,
        String phone,
        LocalDate birthday,
        UserStatus status,

        Address address
) {
    public UserDetailsData(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getBirthday(), user.getStatus(), user.getAddress());
    }
}
