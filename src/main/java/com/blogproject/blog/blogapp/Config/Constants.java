package com.blogproject.blog.blogapp.Config;

public class Constants {

    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String PAGE_SORT_BY = "postId";
    public static final String PAGE_SORT_DIR = "asc";
    public static final Integer NORMAL_USER = 2;
    public static final Integer ADMIN_USER = 1;
    public static final String[] PERMITTED_URLS = {
                                                    "/api/v1/auth/**",
                                                    "/v3/api-docs",
                                                    "/v2/api-docs",
                                                    "/swagger-resources/**",
                                                    "/swagger-ui/**",
                                                    "/webjars/**"
                                                };

    
}
