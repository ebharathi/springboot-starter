package com.example.RESTApi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;

@Service
public class MovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.token}")
    private String token;

    // to excuete api calls
    private final OkHttpClient client;

    // constructor
    public MovieService() {
        this.client = new OkHttpClient();
    }

    public String getAllMovies() {
        String url = "https://api.themoviedb.org/3/trending/all/day?language=en-US";

        // building request using request
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new Exception("Unexpected Error from API:" + response);
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Failed to fetch movies\"}";
        }
    }

    public String getMovieById(@RequestParam String movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?language=en-US";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new Exception("Unexpected error from API: " + response);
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Failed to fetch movies\"}";
        }
    }
}
