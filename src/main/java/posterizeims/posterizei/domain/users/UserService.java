package posterizeims.posterizei.domain.users;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder = null;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encoderPassword(String password) {
        return encoder.encode(password);
    }
}
