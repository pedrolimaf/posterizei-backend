package posterizeims.posterizei.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import posterizeims.posterizei.dtos.UserDto;
import posterizeims.posterizei.models.UserModel;
import posterizeims.posterizei.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers(){
		List<UserModel> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserModel> getUser(@PathVariable UUID userId){
		UserModel user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserDto userdto){	
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userdto, userModel);
		UserModel createdUser = userService.createUser(userModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserModel> updateUser(@PathVariable UUID userId, @RequestBody @Valid UserDto userdto){
		UserModel userModel = new UserModel();
	    BeanUtils.copyProperties(userdto, userModel);
	    UserModel updatedUser = userService.updateUser(userId, userModel);
	    return ResponseEntity.ok(updatedUser);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    return ex.getBindingResult()
	              .getFieldErrors()
	              .stream()
	              .map(error -> error.getDefaultMessage())
	              .collect(Collectors.toList());
	}
}
