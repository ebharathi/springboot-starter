package com.example.RESTApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class TVShowService {

    @Value("${tmdb.token}")
    private String token;

    private final OkHttpClient client;

    public TVShowService() {
        this.client = new OkHttpClient();
    }

    public String getAllTVshows() {
        System.out.println("CALLING ALL TV SHOWS API[+]");
        String url = "https://api.themoviedb.org/3/trending/tv/day?language=en-US";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new Exception("Failed to fetch tv shows");
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
