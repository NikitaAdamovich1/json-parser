package com.study.json_parser.service;

import com.study.json_parser.model.Product;
import com.study.json_parser.model.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyzeService {

    private Map<String, Product> products = new HashMap<>();

    public void addReviews(List<Review> reviews) {
        for (Review review : reviews) {
            products.computeIfAbsent(review.getAsin(), Product::new).addReview(review);
        }
    }

    public List<Product> getPopularProducts() {
        return products.values().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getReviewCount(), p1.getReviewCount()))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByRating() {
        return products.values().stream()
                .sorted((p1, p2) -> Double.compare(p2.getAverageRating(), p1.getAverageRating()))
                .collect(Collectors.toList());
    }
}
