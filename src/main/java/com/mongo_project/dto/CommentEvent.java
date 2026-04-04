package com.mongo_project.dto;

import java.time.Instant;

public class CommentEvent {
    String commentId;
    String movieId;
    String email;
    Instant createdAt;

    public CommentEvent() {
    }

    public CommentEvent(String commentId, String movieId, String email, Instant createdAt) {
        this.commentId = commentId;
        this.movieId = movieId;
        this.email = email;
        this.createdAt = createdAt;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    
}
