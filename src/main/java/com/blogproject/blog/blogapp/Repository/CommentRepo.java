package com.blogproject.blog.blogapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.blog.blogapp.Entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
    
}
