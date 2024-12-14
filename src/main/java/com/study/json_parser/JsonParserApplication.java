package com.study.json_parser;

import com.study.json_parser.service.AnalyzeService;
import com.study.json_parser.util.CsvWriter;
import com.study.json_parser.util.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JsonParserApplication {

	private static final String JSON_FILE_PATH = "src/main/resources/Software_5.json";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JsonParserApplication.class, args);

		JsonParser parser = new JsonParser();
		AnalyzeService analyzer = new AnalyzeService();
		CsvWriter csvWriter = new CsvWriter();

		analyzer.addReviews(parser.parseReviews(JSON_FILE_PATH));

		csvWriter.writePopularProductsToCsv("popular_products.csv", analyzer.getPopularProducts());

		csvWriter.writeProductsByRatingToCsv("products_by_rating.csv", analyzer.getProductsByRating());
	}
}
