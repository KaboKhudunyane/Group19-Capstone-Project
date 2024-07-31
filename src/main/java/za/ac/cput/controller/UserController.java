package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.*; 
import za.ac.cput.service.UserService;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAllExceptions(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestParam("firstName") String firstName,
                                       @RequestParam("middleName") String middleName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("email") String email,
                                       @RequestParam("mobileNumber") String mobileNumber,
                                       @RequestParam("streetName") String streetName,
                                       @RequestParam("suburb") String suburb,
                                       @RequestParam("city") String city,
                                       @RequestParam("province") String province,
                                       @RequestParam("zipCode") String zipCode,
                                       @RequestParam("license") Boolean license,
                                       @RequestParam(value = "picture", required = false) MultipartFile picture) throws IOException {
        // Log received parameters
        System.out.println("Received parameters:");
        System.out.println("First Name: " + firstName);
        System.out.println("Middle Name: " + middleName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Street Name: " + streetName);
        System.out.println("Suburb: " + suburb);
        System.out.println("City: " + city);
        System.out.println("Province: " + province);
        System.out.println("Zip Code: " + zipCode);
        System.out.println("License: " + license);
        if (picture != null) {
            System.out.println("Picture size: " + picture.getBytes().length);
        }
        // Process file and create User
        byte[] pictureBytes = picture != null ? picture.getBytes() : null;
        Name name = new Name.Builder()
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .buildName();
        Contact contact = new Contact.Builder()
                .setEmail(email)
                .setMobileNumber(mobileNumber)
                .buildContact();
        Address address = new Address.Builder()
                .setStreetName(streetName)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setZipCode(zipCode)
                .buildAddress();
        User user = new User.Builder()
                .setName(name)
                .setContact(contact)
                .setAddress(address)
                .setLicense(license)
                .setPicture(pictureBytes)
                .buildUser();
        User createdUser = userService.create(user);
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping("/read/{userID}")
    public ResponseEntity<User> read(@PathVariable Long userID) {
        User user = userService.read(userID);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<Void> delete(@PathVariable Long userID) {
        userService.delete(userID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
}