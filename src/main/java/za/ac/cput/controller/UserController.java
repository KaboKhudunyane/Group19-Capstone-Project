package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
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
                                       @RequestParam(value = "license", required = false) MultipartFile licenseFile,
                                       @RequestParam(value = "identityDocument", required = false) MultipartFile identityDocumentFile)
            throws IOException {
        // Process the files into byte arrays
        byte[] licenseBytes = licenseFile != null ? licenseFile.getBytes() : null;
        byte[] identityDocumentBytes = identityDocumentFile != null ? identityDocumentFile.getBytes() : null;
        // Create the Name, Contact, and Address objects
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
        // Create the User object with the processed images
        User user = new User.Builder()
                .setName(name)
                .setContact(contact)
                .setAddress(address)
                .setLicense(licenseBytes)
                .setIdentityDocument(identityDocumentBytes)
                .buildUser();
        // Create the user using the service
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
