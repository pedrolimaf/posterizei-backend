package posterizeims.posterizei.users;

import ch.qos.logback.core.CoreConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static posterizeims.posterizei.users.UserStatus.ACTIVE;
import static posterizeims.posterizei.users.UserStatus.INACTIVE;

@Table(name = "users")
@Entity(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String password;

    public User(UserRegisterData userRegisterData) {
        this.name = userRegisterData.name();
        this.email = userRegisterData.email();
        this.phone = userRegisterData.phone();
        this.birthday = userRegisterData.birthday();
        this.status = ACTIVE;
        this.password = userRegisterData.password();
    }

    public void updateData(UpdateUserData data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.phone() != null){
            this.phone = data.phone();
        }
        if(data.birthday() != null){
            this.birthday = data.birthday();
        }
    }

    public void delete() {
        this.status = INACTIVE;
    }
}
