package by.peachack.wim.repositories;

import by.peachack.wim.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String login, String password);

    boolean existsByUsername(String login);
}
