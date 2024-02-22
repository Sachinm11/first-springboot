package com.blogproject.blog.blogapp.Payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    
    private List<PostDto> content;
    private Integer postNumber;
    private Integer postSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean isLastPage;
}
