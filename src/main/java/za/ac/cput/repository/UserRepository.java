package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Method to find a user by user ID
    User findByUserID(String userID);

    // Method to delete a user by user ID
    void deleteByUserID(String userID);

    // Method to get all users
    List<User> getAllUsers();
}
