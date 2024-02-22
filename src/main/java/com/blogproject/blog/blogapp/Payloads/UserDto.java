package com.blogproject.blog.blogapp.Payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import com.blogproject.blog.blogapp.Entities.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotEmpty
    @Size(min = 3, message = "Username must be atleast 3 characters!!")
    private String name;

    @NotEmpty
    @Email(message = "Email address not valid!!")
    private String email;

    @NotEmpty
    @Size(min = 3, message = "Password must be minimum 3 characters!!")
    private String password;

    @NotEmpty(message = "About cannot be empty!!")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
    
}
