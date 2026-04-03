package com.mongo_project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mongo_project.entity.Comment;
import com.mongo_project.entity.CommentResponse;
import com.mongo_project.service.CommentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MongoDBDemoController {

    private CommentService commentService;

    MongoDBDemoController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, MongoDB!");
    }

    @GetMapping("/comment")
    public ResponseEntity<CommentResponse> getAllComment(@RequestParam int page, @RequestParam int size){
        CommentResponse response = commentService.getComment(page, size);
        if(response.getComments() == null || response.getComments().isEmpty()){
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/email")
    public ResponseEntity<CommentResponse> getCommentStartingWithEmail(@RequestParam String email){
        CommentResponse commentResponse = commentService.getCommentStartingWithEmail(email);
        return ResponseEntity.ok(commentResponse);
    }

    @GetMapping("/email/count")
    public ResponseEntity<CommentResponse> getCountByEmail(@RequestParam String email){ 
        CommentResponse response = commentService.getCountByEmail(email);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/byDateRange")
    public ResponseEntity<CommentResponse> getByDateRange(@RequestParam String start, @RequestParam String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter); 
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);
        CommentResponse response = commentService.getCommentByDateRange(startDate, endDate);
        if(response.getStatusCode().equals("404")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/comment")
    public String addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return "Comment added successfully!";
    }
}
