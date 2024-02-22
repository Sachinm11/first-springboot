package com.blogproject.blog.blogapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogproject.blog.blogapp.Entities.User;
import com.blogproject.blog.blogapp.Exceptions.ResourceNotFoundException;
import com.blogproject.blog.blogapp.Repository.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = this.userRepo.findByEmail(username).orElseThrow( () -> new ResourceNotFoundException("user","id",username));
        return user;
    }
    
}
