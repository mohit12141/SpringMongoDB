package com.mongo_project.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "movies")
public class Movies {
    @Field("title")
    private String name;
    private String rated;
    private String poster;
    private String plot;
    private String type;
    private String year;
    private String[] genres;
    private Imdb imdb;
    
    public Movies(String name, String rated, String poster, String plot, String type, String year, String[] genres,
            Imdb imdb) {
        this.name = name;
        this.rated = rated;
        this.poster = poster;
        this.plot = plot;
        this.type = type;
        this.year = year;
        this.genres = genres;
        this.imdb = imdb;
    }

    public Imdb getImdb() {
        return imdb;
    }

    public void setImdb(Imdb imdb) {
        this.imdb = imdb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    

}
