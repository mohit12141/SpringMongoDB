package com.mongo_project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo_project.entity.MovieCommentAnalytics;

@Repository
public interface MovieCommentAnalyticsRepository extends MongoRepository<MovieCommentAnalytics, String> {
    
}
