package com.mongo_project.service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mongo_project.dto.CommentEvent;
import com.mongo_project.entity.Comment;
import com.mongo_project.entity.CommentResponse;
import com.mongo_project.kafka.producer.CommentEventProducer;
import com.mongo_project.repository.CommentRepository;

@Service
public class CommentService {

    
    private final CommentRepository commentRepository;

    private final CommentEventProducer commentEventProducer;

    CommentService(CommentRepository commentRepository, CommentEventProducer commentEventProducer){
        this.commentRepository = commentRepository;
        this.commentEventProducer = commentEventProducer;
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

    public CommentResponse getCommentByDateRange(LocalDateTime startDate, LocalDateTime endDate){
        List<Comment> list = commentRepository.findByDateRange(startDate, endDate);
        if(list.size() == 0 || list.isEmpty()) {
            return new CommentResponse("404", "No comments found", null);
        }

        return new CommentResponse("200", "Comment's found", list);
    }

    public void addComment(Comment comment) {
        // 1. if date is not provided, set it to current time
        if(comment.getDate() == null) {
            comment.setDate(Instant.now());
        }

        // 2. Save the comment to MongoDB
        Comment savedComment = commentRepository.save(comment);

        // 3. Create a CommentEvent
        CommentEvent event = new CommentEvent(
                savedComment.getId(),
                savedComment.getMovieId(),
                savedComment.getEmail(),
                savedComment.getDate()
        );

        // 4. Send the CommentEvent to Kafka
        try{
            commentEventProducer.sendCommentEvent(event);
        } catch (Exception e) {
            // Handle the exception, e.g., log it
            System.err.println("Failed to send comment event: " + e.getMessage());
        }
    }
}
