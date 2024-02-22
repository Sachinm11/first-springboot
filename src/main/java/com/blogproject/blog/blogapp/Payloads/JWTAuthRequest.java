package com.blogproject.blog.blogapp.Payloads;

import lombok.Data;

@Data
public class JWTAuthRequest {

    private String username;

    private String password;
    
}
