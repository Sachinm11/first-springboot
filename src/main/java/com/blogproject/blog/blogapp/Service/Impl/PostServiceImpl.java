package com.blogproject.blog.blogapp.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogproject.blog.blogapp.Entities.Category;
import com.blogproject.blog.blogapp.Entities.Post;
import com.blogproject.blog.blogapp.Entities.User;
import com.blogproject.blog.blogapp.Exceptions.ResourceNotFoundException;
import com.blogproject.blog.blogapp.Payloads.PostDto;
import com.blogproject.blog.blogapp.Payloads.PostResponse;
import com.blogproject.blog.blogapp.Repository.CategoryRepo;
import com.blogproject.blog.blogapp.Repository.PostRepo;
import com.blogproject.blog.blogapp.Repository.UserRepo;
import com.blogproject.blog.blogapp.Service.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        Post post = this.dtoToPost(postDto);
        User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user","id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));

        post.setImageName("Default.png");
        post.setCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());

        Post updatePost = this.postRepo.save(post);

        return this.PostToDto(updatePost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = this.postRepo.findAll(page);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPostNumber(postPage.getNumber());
        postResponse.setPostSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        return this.PostToDto(post);

    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        Page<Post> postPage = this.postRepo.findByCategory(category,page);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos=posts.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());
         
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPostNumber(postPage.getNumber());
        postResponse.setPostSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;

    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        User user = this.userRepo.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user","id",userId));
        Page<Post> postPage = this.postRepo.findByUser(user,page);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPostNumber(postPage.getNumber());
        postResponse.setPostSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;

    }
    
    @Override
    public PostResponse searchPosts(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> postPage = this.postRepo.findByTitleContaining(keyword,page);
        List<Post> posts = postPage.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());


        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPostNumber(postPage.getNumber());
        postResponse.setPostSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    private Post dtoToPost(PostDto postDto){
        return this.modelMapper.map(postDto, Post.class);
    }

    public PostDto PostToDto(Post post){
        return this.modelMapper.map(post, PostDto.class);
    }


}
