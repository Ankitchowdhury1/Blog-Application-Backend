package com.ankit.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import com.ankit.blog.config.AppConstants;
import com.ankit.blog.entities.Role;
import com.ankit.blog.repositories.RoleRepo;



@SpringBootApplication
public class BlogAppApisApplication {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public void run(String... args) throws Exception{
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role1);
			List<Role> results = this.roleRepo.saveAll(roles);
			results.forEach(r->{
				System.out.println(r.getName());
			});
			
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
//	public void run1(String... args) throws Exception{
//		System.out.println(this.passwordEncoder.encode("12345"));
//	}
	
	
}
