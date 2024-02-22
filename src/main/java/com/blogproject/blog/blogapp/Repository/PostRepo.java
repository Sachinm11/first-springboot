package com.blogproject.blog.blogapp.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.blog.blogapp.Entities.Category;
import com.blogproject.blog.blogapp.Entities.Post;
import com.blogproject.blog.blogapp.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
    
    Page<Post> findByUser(User user, Pageable page);
    Page<Post> findByCategory(Category category, Pageable page);
    Page<Post> findByTitleContaining(String keyword, Pageable page);

}
