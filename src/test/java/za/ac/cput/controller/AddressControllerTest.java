package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:3306/group19-capstone-project/address";
    private static Address address;

    @BeforeAll
    public static void setup() {
        address = AddressFactory.createAddress("123 Street", "Suburb", "City", "State", "12345");
    }

    @Test
    void create() {
        String url = BASE_URL + "/save";
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, address, Address.class);
        assertNotNull(postResponse);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

        Address savedAddress = postResponse.getBody();
        assertNotNull(savedAddress);
        assertEquals(address.getStreetName(), savedAddress.getStreetName());
        System.out.println("Saved address: " + savedAddress);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + address.getStreetName();
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertNotNull(response.getBody());
        assertEquals(address.getStreetName(), response.getBody().getStreetName());
        System.out.println("Read address: " + response.getBody());
    }

    @Test
    void update() {
        // Modify address data for update
        Address updatedAddress = new Address.Builder()
                .copyAddress(address)
                .setSuburb("New Suburb")
                .buildAddress();

        String url = BASE_URL + "/update";
        restTemplate.put(url, updatedAddress);

        // Retrieve updated address and assert changes
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + address.getStreetName(), Address.class);
        assertNotNull(response.getBody());
        assertEquals("New Suburb", response.getBody().getSuburb());
        System.out.println("Updated address: " + response.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + address.getStreetName();
        restTemplate.delete(url);

        // Ensure address is deleted
        ResponseEntity<Address> response = restTemplate.getForEntity(BASE_URL + "/read/" + address.getStreetName(), Address.class);
        assertNull(response.getBody());
        System.out.println("Address deleted successfully.");
    }

    @Test
    void getAllAddresses() {
        String url = BASE_URL + "/getAllAddresses";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Address[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Address[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        for (Address a : response.getBody()) {
            System.out.println(a);
        }
    }
}