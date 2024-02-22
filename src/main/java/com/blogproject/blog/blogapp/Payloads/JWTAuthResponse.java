package com.blogproject.blog.blogapp.Payloads;

import java.util.Date;

import lombok.Data;

@Data
public class JWTAuthResponse {
    
    private String token;
    private Date expiration;
}
