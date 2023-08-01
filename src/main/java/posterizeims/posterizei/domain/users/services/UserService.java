package posterizeims.posterizei.domain.users.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import posterizeims.posterizei.domain.users.*;

@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder = null;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encoderPassword(String password) {
        return encoder.encode(password);
    }


    public User createUser(UserRegisterData data){
        if(getUserByEmail(data.email()) == null){
            try{
                var user = new User(data);
                repository.save(user);
                return user;
            }catch(RuntimeException ex){
                throw new RuntimeException("Erro ao salvar informações do usuário.",ex);
            }
        }
        throw new RuntimeException("Usuário já existe.");
    }

    public UserDetailsData getUserByEmail(String email){
        try{
            var user = repository.findAllByEmail(email);
            if(user == null){
                return null;
            }
            return new UserDetailsData(user);
        }catch(RuntimeException ex){
            throw new RuntimeException("Erro ao buscar usuário por e-mail", ex);
        }
    }
}
