package com.blogproject.blog.blogapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.blog.blogapp.Entities.User;


public interface UserRepo extends JpaRepository<User , Integer>{

    Optional<User> findByEmail(String email);
    
}
