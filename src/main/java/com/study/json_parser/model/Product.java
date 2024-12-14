package com.study.json_parser.model;

import com.study.json_parser.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Product {

    private String asin;

    private List<Review> reviews = new ArrayList<>();

    public Product(String asin) {
        this.asin = asin;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        return reviews.stream().mapToDouble(Review::getOverall).average().orElse(0);
    }

    public int getReviewCount() {
        return reviews.size();
    }

    public String getAsin() {
        return asin;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
