package com.ankit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankit.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
