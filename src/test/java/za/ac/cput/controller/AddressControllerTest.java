package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/Group19-Capstone-Project/Address";

    private static Address adr;
    @BeforeAll
    public static void setup() {
        Address address = AddressFactory.createAddress("011", "575", "123 Street","District 6", 7441);
    }
    @Test
    void create() {
        String url = BASE_URL + "/save";
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, adr, Address.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Address savedAddress = postResponse.getBody();
        assertEquals(adr.getAddressId(), savedAddress.getAddressId());
        System.out.println("Address aved: " + savedAddress);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + adr.getAddressId();
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertEquals(adr.getAddressId(), response.getBody().getAddressId());
        System.out.println("Read user: " + response.getBody());
    }
    @Test
    void update() {
        // Set updated address data
        Address updatedAddress = new Address.Builder()
                .copyAddress(adr)
                .buildAddress();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedAddress);

        // Retrieve Updated address
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + adr.getAddressId(), Address.class);
        assertNull(response.getBody());
        System.out.println(" Updated Address: " + response.getBody());
    }
    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + adr.getAddressId();
        restTemplate.delete(url);

        // Tests if is deleted
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + adr.getAddressId(), Address.class);
        assertNull(response.getBody());
        System.out.println("Address deleted successfully.");
    }

    @Test
    void getAllAddress() {
        String url = BASE_URL + "/getAllAddress";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("All Addresses: ");
        System.out.println(response.getBody());
    }
}
