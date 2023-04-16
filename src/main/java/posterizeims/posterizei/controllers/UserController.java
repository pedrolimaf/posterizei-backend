package posterizeims.posterizei.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import posterizeims.posterizei.domain.users.*;

import java.util.UUID;

import static posterizeims.posterizei.domain.users.UserStatus.ACTIVE;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        var user = new User(data);
        repository.save(user);

        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserListData(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserListData>> getAllUsers(@PageableDefault(size = 10, sort = "name") Pageable pageable){
        return ResponseEntity.ok(repository.findAllByStatus(ACTIVE, pageable).map(UserListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid UpdateUserData data){
        var user = repository.getReferenceById(data.id());
        user.updateData(data);
        return ResponseEntity.ok(new UserListData(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id){
        var user = repository.getReferenceById(id);
        user.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable UUID id){
        var user = repository.getReferenceById(id);
        return ResponseEntity.ok(new UserDetailsData(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){

        try{
            var user = repository.findByEmail(email);
            if(user != null){
                return ResponseEntity.ok("Já existe um cadastro com esse e-mail.");
            }
            return ResponseEntity.ok("Nenhum cadastro encontrado para o e-mail informado.");
        }catch(RuntimeException ex){
            throw new RuntimeException("Erro ao buscar usuário por e-mail", ex);
        }

    }

}
