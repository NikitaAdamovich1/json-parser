package com.study.json_parser.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.json_parser.model.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<Review> parseReviews(String filePath) throws IOException {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                JsonNode node = objectMapper.readTree(line);

                String reviewerID = node.has("reviewerID") ? node.get("reviewerID").asText() : "";
                String asin = node.has("asin") ? node.get("asin").asText() : "";
                String reviewerName = node.has("reviewerName") ? node.get("reviewerName").asText() : "";
                String reviewText = node.has("reviewText") ? node.get("reviewText").asText() : "";
                double overall = node.has("overall") ? node.get("overall").asDouble() : 0.0;
                boolean verified = node.has("verified") && node.get("verified").asBoolean();
                long unixReviewTime = node.has("unixReviewTime") ? node.get("unixReviewTime").asLong() : 0L;
                String summary = node.has("summary") ? node.get("summary").asText() : "";

                Review review = Review.builder()
                        .reviewerID(reviewerID)
                        .asin(asin)
                        .reviewerName(reviewerName)
                        .reviewText(reviewText)
                        .overall(overall)
                        .verified(verified)
                        .reviewDate(unixReviewTime != 0L ?
                                Instant.ofEpochSecond(unixReviewTime).atZone(ZoneId.systemDefault()).toLocalDate() : null)
                        .summary(summary)
                        .build();

                reviews.add(review);
            }
        }
        return reviews;
    }
}
