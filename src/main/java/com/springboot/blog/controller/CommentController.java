package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService ;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostID(@PathVariable(value = "postId") long postId){

        List<CommentDto> list = commentService.getCommentsByPostId(postId);
        return commentService.getCommentsByPostId(postId);

        //long counter = list.stream().count();
        //int count = commentService.getCommentsByPostId(postId).stream().count()
       //return counter;
    }
}
