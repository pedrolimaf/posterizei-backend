package posterizeims.posterizei.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
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
    @Embedded
    private Address address;

    public User(UserRegisterData userRegisterData) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(bCryptPasswordEncoder);
        System.out.println(userService.encoderPassword(userRegisterData.password()));

        this.name = userRegisterData.name();
        this.email = userRegisterData.email();
        this.phone = userRegisterData.phone();
        this.birthday = userRegisterData.birthday();
        this.status = UserStatus.ACTIVE;
        this.password = userService.encoderPassword(userRegisterData.password());
        this.address = new Address(userRegisterData.addressCode());
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
        this.status = UserStatus.INACTIVE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
