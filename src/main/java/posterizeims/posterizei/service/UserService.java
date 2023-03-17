package posterizeims.posterizei.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import posterizeims.posterizei.models.UserModel;
import posterizeims.posterizei.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserModel> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<UserModel> getUser(UUID userId){
		return userRepository.findById(userId);
	}
	
	public UserModel createUser(UserModel user){
		user.setDataCriacao(LocalDateTime.now());
		user.setIndicadorValidacaoEmail(true);
		user.setStatus("ACTIVE");
		UserModel savedUser = userRepository.save(user);
		return savedUser;
	}
	
}