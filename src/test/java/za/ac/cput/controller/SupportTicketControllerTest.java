package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.SupportTicket;
import za.ac.cput.factory.SupportTicketFactory;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SupportTicketControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL ="mysql://${MYSQL_HOST:localhost}:3306/CarShare";

    private static SupportTicket supportTicket;

    @BeforeAll
    public static void setup(){
        supportTicket = SupportTicketFactory.createSupportTicket("S255p", "MT55", "Rentered", "BMW", "20/05/2024", "Approved");
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<SupportTicket> postResponse = restTemplate.postForEntity(url,supportTicket, SupportTicket.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        SupportTicket  supportTicketSaved = postResponse.getBody();
        assertEquals(supportTicket.getTicketID(), supportTicketSaved.getTicketID());
        System.out.println("Saved data:" + supportTicketSaved);

    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + supportTicket.getTicketID();
        System.out.println("URL:" + url);
        ResponseEntity<SupportTicket> response = restTemplate.getForEntity(url,SupportTicket.class);
        assertEquals(supportTicket.getTicketID(), response.getBody().getTicketID());
        System.out.println("read" + response.getBody());

    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update";
        SupportTicket newSupportTicket = new SupportTicket.Builder().copy(supportTicket)
                .setStatus("Not Approved").buildSupportTicket();
        ResponseEntity<SupportTicket> postResponse = restTemplate.postForEntity(url, newSupportTicket,SupportTicket.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        SupportTicket supportTicketUpdated = postResponse.getBody();
        assertEquals(newSupportTicket.getTicketID(), supportTicketUpdated.getTicketID());
        System.out.println("data Updated:" + supportTicketUpdated);
    }

    @Test
    void  d_delete() {
        String url = BASE_URL + "/delete/" + supportTicket.getTicketID();
        System.out.println("URL:" + url);
        restTemplate.delete(url);
        System.out.println("SupportTicket Deleted");

    }

    @Test
    void e_getAllSupportTicket() {
        String url =BASE_URL +"/getAllSupportTicket";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        System.out.println("Show All:");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
