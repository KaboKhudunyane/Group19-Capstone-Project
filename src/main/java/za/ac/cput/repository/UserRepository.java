package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountUsername(String username);

    Optional<User> findById(Long userID);  // Changed to Optional for null safety

    void deleteById(Long userID);  // Updated method to delete by Id

    @Query("SELECT COUNT(u) FROM User u")
    long countUser();
}
