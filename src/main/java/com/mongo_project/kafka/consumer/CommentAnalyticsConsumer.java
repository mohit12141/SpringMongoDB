package com.mongo_project.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo_project.dto.CommentEvent;
import com.mongo_project.entity.MovieCommentAnalytics;
import com.mongo_project.repository.MovieCommentAnalyticsRepository;

@Component
public class CommentAnalyticsConsumer {
    private MovieCommentAnalyticsRepository analyticsRepository;
    private final ObjectMapper objectMapper;
    
    public CommentAnalyticsConsumer(MovieCommentAnalyticsRepository analyticsRepository, ObjectMapper objectMapper) {
        this.analyticsRepository = analyticsRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "comment-events", groupId = "analytics-group")
    public void consume(String message) {
        try {
            CommentEvent commentEvent = objectMapper.readValue(message, CommentEvent.class);
            
            System.out.println("Received event for movieId: " + commentEvent.getMovieId());

            String movieId = commentEvent.getMovieId();

            MovieCommentAnalytics analytics = analyticsRepository.findById(movieId).orElse(new MovieCommentAnalytics(movieId, 0L));

            analytics.setCommentCount(analytics.getCommentCount() + 1);

            analyticsRepository.save(analytics);

            System.out.println("Movie: " + movieId + "-> Total Comments: " + analytics.getCommentCount());
        } catch (Exception e) {
            System.err.println("Failed to parse message" + e.getMessage());
        }
        
    }
}
