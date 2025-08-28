package com.springsecurity.jwtauth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.jwtauth.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	//jpa query method
	Optional<User> findByUsername(String username);

}
