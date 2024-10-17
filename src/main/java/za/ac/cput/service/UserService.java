package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IService<User, Long>, UserDetailsService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User create(User user) {
        if (passwordEncoder == null) {
            throw new IllegalStateException("PasswordEncoder is not configured");
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        if (user.getRole() == null) {
            user.setRole(User.Role.ROLE_USER);
        }

        return userRepository.save(user);
    }
    @Override
    public User read(Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        return userOptional.orElse(null);
    }
    public User authenticate(String username, String password) {
        if (passwordEncoder == null) {
            throw new IllegalStateException("PasswordEncoder is not configured");
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
    @Override
    public void delete(Long userID) {
        userRepository.deleteById(userID);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public long countUser() {
        return userRepository.count();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
