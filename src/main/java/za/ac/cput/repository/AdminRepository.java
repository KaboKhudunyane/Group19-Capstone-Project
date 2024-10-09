package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByAdminId(Long adminId);
    void deleteAdminByAdminId(Long adminId);

    // Update this method to access username through the Account
    Admin findAdminByAccountUsername(String username);

    @Query("SELECT COUNT(a) FROM Admin a")
    long countAdmins();
}
