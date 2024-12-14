package com.study.json_parser.util;

import com.study.json_parser.model.Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    public static void writePopularProductsToCsv(String fileName, List<Product> products) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write("ASIN продукта, количество отзывов");
            writer.newLine();
            for (Product product : products) {
                writer.write(product.getAsin() + "," + product.getReviewCount());
                writer.newLine();
            }
        }
    }

    public static void writeProductsByRatingToCsv(String fileName, List<Product> products) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write("ASIN продукта, средний рейтинг");
            writer.newLine();
            for (Product product : products) {
                writer.write(product.getAsin() + "," + product.getAverageRating());
                writer.newLine();
            }
        }
    }
}
