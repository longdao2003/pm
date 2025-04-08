package vn.humg.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.humg.pm.entity.Login;

public interface LoginRepository extends JpaRepository<Login,Integer> {
    
}
