package com.example.demo.controllers;

import com.example.demo.service.NewsService;
import com.example.demo.utils.IntervalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
public class NewsController{

    @Autowired
    private NewsService newsService;

    @GetMapping("/search")
    public CollectionModel<EntityModel<Map<String,Object>>> searchNews(
            @RequestParam String keyword,
            @RequestParam(required=false, defaultValue="12") long N,
            @RequestParam(required= false, defaultValue="HOURS") String intervalType
    ){
        ChronoUnit unit = ChronoUnit.valueOf(intervalType.toUpperCase());
        List<Map<String, Object>> articles = newsService.getNews(keyword);
        List<Map<String, Object>> groupedArticles = IntervalUtils.groupInBuckets(articles, N, unit);
        List<EntityModel<Map<String, Object>>> articleModels = groupedArticles.stream()
                .map(article -> EntityModel.of(article,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NewsController.class)
                                        .searchNews(keyword, N, intervalType))
                                .withSelfRel()))
                .collect(Collectors.toList());
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NewsController.class)
                        .searchNews(keyword, N, intervalType))
                .withSelfRel();

        Link nextIntervalLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NewsController.class)
                        .searchNews(keyword, N + 1, intervalType))
                .withRel("nextInterval");

        Link previousIntervalLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NewsController.class)
                        .searchNews(keyword, N - 1, intervalType))
                .withRel("previousInterval");

        return CollectionModel.of(articleModels, selfLink, nextIntervalLink, previousIntervalLink);
    }
}