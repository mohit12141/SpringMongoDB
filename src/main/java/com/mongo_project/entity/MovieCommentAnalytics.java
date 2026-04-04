package com.mongo_project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movie_comment_analytics")
public class MovieCommentAnalytics {
    @Id
    private String movieId;
    private Long commentCount;

    public MovieCommentAnalytics() {
    }

    public MovieCommentAnalytics(String movieId, Long commentCount) {
        this.movieId = movieId;
        this.commentCount = commentCount;
    }

    public String getMovieId() {
        return movieId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public Long getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    
}
