package posterizeims.posterizei.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import posterizeims.posterizei.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
	
}
