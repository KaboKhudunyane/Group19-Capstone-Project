package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/read/{userId}")
    public User read(@PathVariable String userId){
        return userService.read(userId);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void delete (@PathVariable String userId){
        userService.delete(userId);
    }

    /*@GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }*/
}
