package com.blogproject.blog.blogapp.Service;

import com.blogproject.blog.blogapp.Payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
