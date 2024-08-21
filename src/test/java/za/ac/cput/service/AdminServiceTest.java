package za.ac.cput.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;
    private Name name = NameFactory.createName("Thato", "Emeka", "Nwamadi");
    private Contact contact = ContactFactory.createContact("295732963@mycput.ac.za", "0654545212");
    Admin a,b,c;


//    @BeforeEach
//    void setUp() {
//        admin = AdminFactory.createAdmin("adminId", "firstName", "lastName", "email@example.com", "password123");
//    }

    @Test
    void testCreate() {
        a = AdminFactory.bluidAdmin(name,contact,"J1234565");
        Admin created = adminService.create(a);
        assertNotNull(created);
    }

    @Test
    void testRead() {
        b = AdminFactory.bluidAdmin(name,contact,"J1234565");
        adminService.create(b);
        Admin read = adminService.read(b.getAdminId());
        assertEquals(b, read);
        System.out.println(b.toString()+ " \n " + b.toString());
    }

    @Test
    void testUpdate() {
        a = AdminFactory.bluidAdmin(name,contact,"J1234565");
        adminService.create(a);
        Name name = NameFactory.createName("Josh", "Malick", "Orrian");
        Admin updated = new Admin.Builder().copyAdmin(a).setName(name).buildAdmin();
        adminService.update(updated);
        assertEquals("Josh", adminService.read(a.getAdminId()).getName().getFirstName());
    }

    @Test
    void testDelete() {
        c = AdminFactory.bluidAdmin(name,contact,"J1234565");
        adminService.create(c);
        adminService.delete(c.getAdminId());
        assertNull(adminService.read(c.getAdminId()));
    }
}
