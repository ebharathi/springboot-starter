package com.example.RESTApi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RESTApi.service.TVShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/series")
public class TVShowController {

    @Autowired
    private TVShowService tvShowService;

    @GetMapping
    public String getAllTVshows() {
        return tvShowService.getAllTVshows();
    }
}
