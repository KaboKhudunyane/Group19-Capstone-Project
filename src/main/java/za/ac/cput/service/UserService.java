package za.ac.cput.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements IService<User, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user = new User.Builder().copy(user).setPassword(encodedPassword).build();
        return userRepository.save(user);
    }

    @Override
    public User read(Long userID) {
        return userRepository.findById(userID).orElse(null);
    }

    @Override
    public User update(User user) {
        // Encode the password before updating the user
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user = new User.Builder().copy(user).setPassword(encodedPassword).build();
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userID) {
        userRepository.deleteById(userID);
    }

    public long countUsers() {
        return userRepository.countUser();
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
