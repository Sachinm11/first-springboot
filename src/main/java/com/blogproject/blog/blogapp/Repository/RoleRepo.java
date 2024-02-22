package com.blogproject.blog.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.blog.blogapp.Entities.Roles;

public interface RoleRepo extends JpaRepository<Roles,Integer>{
    
}
