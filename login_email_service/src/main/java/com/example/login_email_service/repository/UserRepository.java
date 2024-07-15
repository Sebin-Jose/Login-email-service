package com.example.login_email_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login_email_service.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
public User findByUsernameAndPassword(String username, String password);

}
