package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {
    CommentDto createComment (long postId,CommentDto commentDto);

}
