package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    private final String apiKey = "83c95c63559e49e9b2e04f2af442042f"; // Secure API key using env variable

    public List<Map<String, Object>> getNews(String keyword) {
        String url = UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/everything")
                .queryParam("q", keyword)
                .queryParam("apiKey", apiKey)
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> res = restTemplate.getForObject(url, Map.class);
        System.out.print(res.get("status"));
        if (res != null && res.get("status").equals("ok")) {
            System.out.print(res.get("articles"));
            return (List<Map<String, Object>>) res.get("articles");
        } else {

            throw new RuntimeException("Failed to fetch news data from NewsAPI");
        }
    }
}