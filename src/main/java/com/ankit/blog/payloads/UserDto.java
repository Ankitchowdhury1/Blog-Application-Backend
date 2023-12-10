package com.ankit.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.ankit.blog.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min=4, message="user name must be minimum of 4 charactor")
	private String name;
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty
	@Size(min=4, max = 10 ,message="password must be minimum of 3 and maximum of 10 charactor")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
}
