package posterizeims.posterizei.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posterizeims.posterizei.users.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static posterizeims.posterizei.users.UserStatus.ACTIVE;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterData data){
        var user = new User(data);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserListData>> getAllUsers(@PageableDefault(size = 10, sort = "name") Pageable pageable){
        return ResponseEntity.ok(repository.findAllByStatus(ACTIVE, pageable).map(UserListData::new));
    }

    @PutMapping
    @Transactional
    public void updateUser(@RequestBody @Valid UpdateUserData data){
        var user = repository.getReferenceById(data.id());
        user.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable UUID id){
        var user = repository.getReferenceById(id);
        user.delete();
    }

}
