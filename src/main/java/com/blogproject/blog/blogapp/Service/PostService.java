package com.blogproject.blog.blogapp.Service;

import com.blogproject.blog.blogapp.Payloads.PostDto;
import com.blogproject.blog.blogapp.Payloads.PostResponse;

public interface PostService {
    
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer PageSize, String sortBy, String sortDir);
    PostDto getPostById(Integer postId);
    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PostResponse searchPosts(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

}
