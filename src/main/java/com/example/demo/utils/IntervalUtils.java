package com.example.demo.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntervalUtils {
    public static List<Map<String, Object>> groupInBuckets(List<Map<String, Object>> articles, long N, ChronoUnit unit) {
        Instant now = Instant.now();
        return articles.stream()
                .filter(article -> {
                    Instant publishedAt = Instant.parse((String) article.get("publishedAt"));
                    return publishedAt.isAfter(now.minus(N, unit));
                })
                .collect(Collectors.toList());
    }
}