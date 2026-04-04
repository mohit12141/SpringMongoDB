package com.mongo_project.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.mongo_project.dto.CommentEvent;
import com.mongo_project.entity.MovieCommentAnalytics;
import com.mongo_project.repository.MovieCommentAnalyticsRepository;

@Component
public class CommentAnalyticsConsumer {
    private MovieCommentAnalyticsRepository analyticsRepository;

    public CommentAnalyticsConsumer(MovieCommentAnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    @KafkaListener(topics = "comment-events", groupId = "analytics-group")
    public void consume(CommentEvent commentEvent) {
        System.out.println("Received event for movieId: " + commentEvent.getMovieId());

        String movieId = commentEvent.getMovieId();

        MovieCommentAnalytics analytics = analyticsRepository.findById(movieId).orElse(new MovieCommentAnalytics(movieId, 0L));

        analytics.setCommentCount(analytics.getCommentCount() + 1);

        analyticsRepository.save(analytics);

        System.out.println("Movie: " + movieId + "-> Total Comments: " + analytics.getCommentCount());
    }
}
