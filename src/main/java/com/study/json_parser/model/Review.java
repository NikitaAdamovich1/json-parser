package com.study.json_parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Review {

    private String reviewerID;

    private String asin;

    private String reviewerName;

    private String reviewText;

    private double overall;

    private boolean verified;

    private LocalDate reviewDate;

    private String summary;
}
