package com.mongo_project.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommentResponse {
    private String statusCode;
    private String message;
    private List<Comment> comments;
    private String TimeStamp;

    public CommentResponse() {
    }

    public CommentResponse(String statusCode, String message, List<Comment> comments) {
        this.statusCode = statusCode;
        this.message = message;
        this.comments = comments;
        this.TimeStamp = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
}
