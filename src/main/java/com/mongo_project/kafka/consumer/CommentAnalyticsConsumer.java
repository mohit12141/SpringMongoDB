package com.mongo_project.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo_project.dto.CommentEvent;
import com.mongo_project.entity.MovieCommentAnalytics;
import com.mongo_project.entity.ProcessedEvent;
import com.mongo_project.repository.MovieCommentAnalyticsRepository;
import com.mongo_project.repository.ProcessedEventRepository;

@Component
public class CommentAnalyticsConsumer {
    private MovieCommentAnalyticsRepository analyticsRepository;
    private final ObjectMapper objectMapper;
    private ProcessedEventRepository processedEventRepository;
    
    public CommentAnalyticsConsumer(MovieCommentAnalyticsRepository analyticsRepository, ObjectMapper objectMapper, ProcessedEventRepository processedEventRepository) {
        this.analyticsRepository = analyticsRepository;
        this.objectMapper = objectMapper;
        this.processedEventRepository = processedEventRepository;
    }

    @KafkaListener(topics = "comment-events", groupId = "analytics-group")
    public void consume(String message) {
        try {
            CommentEvent commentEvent = objectMapper.readValue(message, CommentEvent.class);

            String eventId = commentEvent.getCommentId();

            if(processedEventRepository.existsById(eventId)){
                System.out.println("Event with ID " + eventId + " has already been processed. Skipping...");
                return;
            }

            System.out.println("Received event for movieId: " + commentEvent.getMovieId());

            String movieId = commentEvent.getMovieId();

            MovieCommentAnalytics analytics = analyticsRepository.findById(movieId).orElse(new MovieCommentAnalytics(movieId, 0L));

            analytics.setCommentCount(analytics.getCommentCount() + 1);

            analyticsRepository.save(analytics);

            processedEventRepository.save(new ProcessedEvent(eventId));

            System.out.println("Movie: " + movieId + "-> Total Comments: " + analytics.getCommentCount());
        } catch (Exception e) {
            System.err.println("Failed to parse message" + e.getMessage());
        }
        
    }
}
