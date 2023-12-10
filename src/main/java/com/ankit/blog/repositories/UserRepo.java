package com.ankit.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entities.User;

public interface UserRepo extends JpaRepository <User, Integer>{

	
	Optional<User> findByEmail(String email);
}
