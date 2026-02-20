package com.mongo_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongo_project.entity.Movies;

@Service
public class MovieService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movies> getAllMovies(int page, int size){
        Query query = new Query();

        query.skip((long) (page - 1) * size);
        query.limit(size);

        return mongoTemplate.find(query, Movies.class);
    }

    public List<Movies> getByIdAndImdbRatingGreaterThan(int year, double rating){
        Query query = new Query();

        query.addCriteria(Criteria.where("year").is(year));
        query.addCriteria(Criteria.where("imdb.rating").gte(rating));

        return mongoTemplate.find(query, Movies.class);
    }
}
