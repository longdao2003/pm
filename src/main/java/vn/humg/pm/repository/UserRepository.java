package vn.humg.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.humg.pm.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);   

    Optional<User> findByUsername(String username);
}
