package vn.humg.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.humg.pm.entity.Password;

public interface PasswordRepository extends JpaRepository<Password,Integer> {
    
}
