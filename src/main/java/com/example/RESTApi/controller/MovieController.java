package com.example.RESTApi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RESTApi.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public String getMovieById(@PathVariable String movieId) {
        return movieService.getMovieById(movieId);
    }

}
