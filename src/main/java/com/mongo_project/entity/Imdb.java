package com.mongo_project.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Imdb {
    private Double rating;
    private Long votes;
    @Field("id")
    private Long id;
    

    public Imdb(Double rating, Long votes, Long id) {
        this.rating = rating;
        this.votes = votes;
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public Long getVotes() {
        return votes;
    }
    public void setVotes(Long votes) {
        this.votes = votes;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
    
}
