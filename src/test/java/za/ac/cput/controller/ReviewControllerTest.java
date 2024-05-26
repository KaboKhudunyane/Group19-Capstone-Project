package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Review;
import za.ac.cput.factory.ReviewFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {
    private TestRestTemplate restTemplate;


    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/review";

    private static Review review;



    @BeforeAll
    public static void setup() {

        review = ReviewFactory.buildReview("12","01",2d,"Good","12 May 2024");
    }

    @Test
    @Order(1)
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Review> postResponse = restTemplate.postForEntity(url, review, Review.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Review savedReview = postResponse.getBody();
        assertEquals(review.getBookingId(), savedReview.getBookingId());
        System.out.println("Saved data: " + savedReview);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + review.getBookingId();
        System.out.println("URL" + url);
        ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
        assertEquals(review.getBookingId(), response.getBody().getReviewId());
        System.out.println("Read: " + response.getBody());
    }


    @Test
    void getAll() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("View all: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
