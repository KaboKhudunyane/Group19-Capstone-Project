package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Promotion;
import za.ac.cput.factory.PromotionFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PromotionControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/promotion";

    private static Promotion promotion;

    @BeforeAll
    public static void setup() {
        promotion = PromotionFactory.createPromotion("001", "Spring Sale", "2023-05-22", "2023-06-22", "20%");
    }

    @Test
    void save() {
        String url = BASE_URL + "/save";
        ResponseEntity<Promotion> postResponse = restTemplate.postForEntity(url, promotion, Promotion.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Promotion savedPromotion = postResponse.getBody();
        assertEquals(promotion.getPromotionID(), savedPromotion.getPromotionID());
        System.out.println("Saved promotion: " + savedPromotion);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + promotion.getPromotionID();
        ResponseEntity<Promotion> response = restTemplate.getForEntity(url, Promotion.class);
        assertEquals(promotion.getPromotionID(), response.getBody().getPromotionID());
        System.out.println("Read promotion: " + response.getBody());
    }

    @Test
    void update() {
       
        Promotion updatedPromotion = promotion;
        updatedPromotion.setDiscount("25%");

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedPromotion);


        ResponseEntity<Promotion> response = restTemplate.getForEntity(BASE_URL + "/read/" + promotion.getPromotionID(), Promotion.class);
        assertEquals("25%", response.getBody().getDiscount());
        System.out.println("Updated promotion: " + response.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + promotion.getPromotionID();
        restTemplate.delete(url);


        ResponseEntity<Promotion> response = restTemplate.getForEntity(BASE_URL + "/read/" + promotion.getPromotionID(), Promotion.class);
        assertNull(response.getBody());
        System.out.println("Promotion deleted successfully.");
    }

    @Test
    void getAll() {
        String url = BASE_URL + "/get-all";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All promotions: ");
        System.out.println(response.getBody());
    }
}
