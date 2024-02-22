package com.blogproject.blog.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.blog.blogapp.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
    
}
