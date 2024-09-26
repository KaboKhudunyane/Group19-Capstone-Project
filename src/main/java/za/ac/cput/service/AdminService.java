package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IService<Admin,Long>{

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin read(Long adminId) {
        return adminRepository.findAdminByAdminId(adminId);
    }

    @Override
    public Admin update(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void delete(Long adminId) {
        adminRepository.deleteAdminByAdminId(adminId);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

   /* public Optional<Admin> findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }*/
   public Admin authenticateAdmin(String username, String password) {
       Admin admin = adminRepository.findAdminByUsername(username);
       if (admin != null && admin.getPassword().equals(password)) {
           return admin;
       }
       return null;
   }
    public long countAdmins() {
        return adminRepository.countAdmins();
    }
}
