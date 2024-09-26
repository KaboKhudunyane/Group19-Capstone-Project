package za.ac.cput.service;

import org.junit.jupiter.api.Order;
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
    Admin a, b, c;

    @Test
    @Order(1)
    void testCreate() {
        a = AdminFactory.buildAdmin(name, contact, "adminUsername7", "J1234565");
        Admin created = adminService.create(a);
        assertNotNull(created);
    }

    @Test
    @Order(2)
    void testRead() {
        b = AdminFactory.buildAdmin(name, contact, "adminUsername", "J1234565");
        adminService.create(b);
        Admin read = adminService.read(b.getAdminId());
        assertEquals(b, read);
        System.out.println(b.toString() + " \n " + read.toString());
    }
    @Test
    @Order(3)
    void testUpdate() {
        a = AdminFactory.buildAdmin(name, contact, "adminUsername2", "J1234565");
        adminService.create(a);
        Name updatedName = NameFactory.createName("Josh", "Malick", "Orrian");
        Admin updated = new Admin.Builder().copyAdmin(a).setName(updatedName).buildAdmin();
        adminService.update(updated);
        assertEquals("Josh", adminService.read(a.getAdminId()).getName().getFirstName());
    }

    @Test
    @Order(4)
    void testDelete() {
        c = AdminFactory.buildAdmin(name, contact, "adminUsername90", "J1234565");
        adminService.create(c);
        adminService.delete(c.getAdminId());
        assertNull(adminService.read(c.getAdminId()));
    }


    @Test
    @Order(5)
    void testAuthenticateAdmin() {
        a = AdminFactory.buildAdmin(name, contact, "admin1", "adminPassword");
        adminService.create(a);

        Admin authenticatedAdmin = adminService.authenticateAdmin("admin1", "adminPassword");
        assertNotNull(authenticatedAdmin);
        assertEquals("admin1", authenticatedAdmin.getUsername());
    }


    @Test
    @Order(6)
    void testAuthenticateAdminWithInvalidCredentials() {
        a = AdminFactory.buildAdmin(name, contact, "admin4", "adminPassword");
        adminService.create(a);

        Admin authenticatedAdmin = adminService.authenticateAdmin("admin5", "wrongPassword");
        assertNull(authenticatedAdmin);
    }
    @Test
    @Order(7)
    void getAll() {
        System.out.println(adminService.getAll());
    }

    @Test
    @Order(8)
    void testCount() {
        System.out.println("Number of records in the Amin table:" +adminService.countAdmins());
    }
}
package za.ac.cput.service;

import org.junit.jupiter.api.Order;
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
    Admin a, b, c;

    @Test
    @Order(1)
    void testCreate() {
        a = AdminFactory.buildAdmin(name, contact, "adminUsername7", "J1234565");
        Admin created = adminService.create(a);
        assertNotNull(created);
    }

    @Test
    @Order(2)
    void testRead() {
        b = AdminFactory.buildAdmin(name, contact, "adminUsername", "J1234565");
        adminService.create(b);
        Admin read = adminService.read(b.getAdminId());
        assertEquals(b, read);
        System.out.println(b.toString() + " \n " + read.toString());
    }
    @Test
    @Order(3)
    void testUpdate() {
        a = AdminFactory.buildAdmin(name, contact, "adminUsername2", "J1234565");
        adminService.create(a);
        Name updatedName = NameFactory.createName("Josh", "Malick", "Orrian");
        Admin updated = new Admin.Builder().copyAdmin(a).setName(updatedName).buildAdmin();
        adminService.update(updated);
        assertEquals("Josh", adminService.read(a.getAdminId()).getName().getFirstName());
    }

    @Test
    @Order(4)
    void testDelete() {
        c = AdminFactory.buildAdmin(name, contact, "adminUsername90", "J1234565");
        adminService.create(c);
        adminService.delete(c.getAdminId());
        assertNull(adminService.read(c.getAdminId()));
    }


    @Test
    @Order(5)
    void testAuthenticateAdmin() {
        a = AdminFactory.buildAdmin(name, contact, "admin1", "adminPassword");
        adminService.create(a);

        Admin authenticatedAdmin = adminService.authenticateAdmin("admin1", "adminPassword");
        assertNotNull(authenticatedAdmin);
        assertEquals("admin1", authenticatedAdmin.getUsername());
    }


    @Test
    @Order(6)
    void testAuthenticateAdminWithInvalidCredentials() {
        a = AdminFactory.buildAdmin(name, contact, "admin4", "adminPassword");
        adminService.create(a);

        Admin authenticatedAdmin = adminService.authenticateAdmin("admin5", "wrongPassword");
        assertNull(authenticatedAdmin);
    }
    @Test
    @Order(7)
    void getAll() {
        System.out.println(adminService.getAll());
    }

    @Test
    @Order(8)
    void testCount() {
        System.out.println("Number of records in the Amin table:" +adminService.countAdmins());
    }
}
