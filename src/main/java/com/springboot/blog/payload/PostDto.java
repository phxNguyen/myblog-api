package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PostDto {

    private long id;
    @NotNull @Size(min = 6, message = "Post title should have at least 6 characters")
    private String title;
    @NotNull @Size(min = 6, message = "Post Description  should have at least 10 characters")
    private String description;
    @NotNull
    private String content;
    private List<CommentDto> commentDtos;
}
