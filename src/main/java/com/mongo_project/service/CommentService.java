package com.mongo_project.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mongo_project.entity.Comment;
import com.mongo_project.entity.CommentResponse;
import com.mongo_project.repository.CommentRepository;

@Service
public class CommentService {

    
    private final CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public CommentResponse getComment(int page, int size) {
        Page<Comment> comments = commentRepository.findAll(PageRequest.of(page, size));
        return  new CommentResponse("200", "Retrieved successfully", comments.getContent());

    }

    public CommentResponse getCommentStartingWithEmail(String email){
        List<Comment> comments = commentRepository.findByEmail(email);
        if(comments.isEmpty()){
            return new CommentResponse("404", "No comments found with email starting with " + email, null);
        } 
        return new CommentResponse("200", comments.size() + " comments found with email starting with " + email, comments);
    }

    public CommentResponse getCountByEmail(String email){
        long count = commentRepository.getCountByEmail(email);
        return new CommentResponse("200", count + " comments found with email starting with " + email, null);
    }
}
