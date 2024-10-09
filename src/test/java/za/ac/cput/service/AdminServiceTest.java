package za.ac.cput.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Account; // Make sure to import the Account class
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.AccountFactory; // Import the AccountFactory

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    private final Name name = NameFactory.createName("Thato", "Emeka", "Nwamadi");
    private final Contact contact = ContactFactory.createContact("295732963@mycput.ac.za", "0654545212");
    private final Account account = AccountFactory.createAccount("adminUsername", "adminPassword"); // Create the Account like Name and Contact
    Admin a, b, c;

    @Test
    @Order(1)
    void testCreate() {
        Admin admin = AdminFactory.buildAdmin(name, contact, account); // Pass Account
        a = adminService.create(admin);
        assertNotNull(a);
        assertEquals(admin.getAdminId(), a.getAdminId()); // Verify created ID
    }

    @Test
    @Order(2)
    void testRead() {
        b = AdminFactory.buildAdmin(name, contact, account); // Pass Account
        adminService.create(b);
        Admin read = adminService.read(b.getAdminId());
        assertEquals(b.getAdminId(), read.getAdminId());
        System.out.println(b.toString() + " \n " + read.toString());
    }

    @Test
    @Order(3)
    void testUpdate() {
        Account newAccount = AccountFactory.createAccount("adminUsername2", "adminPassword2"); // New Account for update
        a = AdminFactory.buildAdmin(name, contact, newAccount); // Pass Account
        adminService.create(a);
        Name updatedName = NameFactory.createName("Josh", "Malick", "Orrian");
        Admin updated = new Admin.Builder().copyAdmin(a).setName(updatedName).buildAdmin();
        adminService.update(updated);
        assertEquals("Josh", adminService.read(a.getAdminId()).getName().getFirstName());
    }

    @Test
    @Order(4)
    void testDelete() {
        c = AdminFactory.buildAdmin(name, contact, account); // Pass Account
        adminService.create(c);
        adminService.delete(c.getAdminId());
        assertNull(adminService.read(c.getAdminId()));
    }

    @Test
    @Order(5)
    void testAuthenticateAdmin() {
        a = AdminFactory.buildAdmin(name, contact, account); // Pass Account
        adminService.create(a);

        Admin authenticatedAdmin = adminService.authenticateAdmin("adminUsername", "adminPassword");
        assertNotNull(authenticatedAdmin);
        assertEquals("adminUsername", authenticatedAdmin.getAccount().getUsername()); // Access account details
    }

    @Test
    @Order(6)
    void testAuthenticateAdminWithInvalidCredentials() {
        a = AdminFactory.buildAdmin(name, contact, account); // Pass Account
        adminService.create(a);

        Admin authenticatedAdmin = adminService.authenticateAdmin("admin5", "wrongPassword");
        assertNull(authenticatedAdmin);
    }

    @Test
    @Order(7)
    void testGetAll() {
        System.out.println(adminService.getAll());
        assertNotNull(adminService.getAll());
    }

    @Test
    @Order(8)
    void testCount() {
        System.out.println("Number of records in the Admin table: " + adminService.countAdmins());
        assertTrue(adminService.countAdmins() >= 0); // Ensure the count is non-negative
    }
}
