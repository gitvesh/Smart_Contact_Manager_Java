package scm.SmartContactManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scm.SmartContactManager.Entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface user_repository extends JpaRepository<User,String> {
Optional<User> findByEmail(String email);

  User findByToken(String emailToken);

 }


