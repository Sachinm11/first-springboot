package com.blogproject.blog.blogapp.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogproject.blog.blogapp.Config.Constants;
import com.blogproject.blog.blogapp.Entities.Roles;
import com.blogproject.blog.blogapp.Entities.User;
import com.blogproject.blog.blogapp.Exceptions.ResourceNotFoundException;
import com.blogproject.blog.blogapp.Payloads.UserDto;
import com.blogproject.blog.blogapp.Repository.RoleRepo;
import com.blogproject.blog.blogapp.Repository.UserRepo;
import com.blogproject.blog.blogapp.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user","id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user","id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        
        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtos =  users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user","id",userId));
        this.userRepo.delete(user);
        
    }

    private User dtoToUser(UserDto userDto){
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }

    public UserDto userToDto(User user){

        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Roles role = this.roleRepo.findById(Constants.NORMAL_USER).get();
        user.getRoles().add(role);
        User registeredUser = this.userRepo.save(user);
        return this.userToDto(registeredUser);

    }

    
}
