package com.mongo_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo_project.entity.Movies;
import com.mongo_project.service.MovieService;


@RestController
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public ResponseEntity<List<Movies>> getAllMovies(@RequestParam(defaultValue = "10") int page, @RequestParam(defaultValue = "10") int size) {
        List<Movies> list = movieService.getAllMovies(page, size);
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/yearAndRating")
    public ResponseEntity<List<Movies>> getByYearAndRating(@RequestParam int year, @RequestParam double rating){
        List<Movies> list = movieService.getByIdAndImdbRatingGreaterThan(year, rating);
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }
}
