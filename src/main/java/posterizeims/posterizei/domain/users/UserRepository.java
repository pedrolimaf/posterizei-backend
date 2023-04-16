package posterizeims.posterizei.domain.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findAllByStatus(UserStatus active, Pageable pageable);
    UserDetails findByEmail(String email);

}
