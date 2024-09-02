package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name= "Administrators")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Embedded
    private Name name;

    @Embedded
    private Contact contact;
    @Column(unique = true)
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(Builder buildAdmin) {
        this.adminId = buildAdmin.adminId;
        this.name = buildAdmin.name;
        this.contact = buildAdmin.contact;
        this.username = buildAdmin.username;
        this.password = buildAdmin.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminId, admin.adminId) &&
                Objects.equals(name, admin.name) &&
                Objects.equals(contact, admin.contact) &&
                Objects.equals(username, admin.username) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, name, contact, username, password);
    }

    @Override
    public String toString() {
        return ".........Administrator.......\n" +
                "adminId=" + adminId + "\n" +
                "Name:" + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName() + "\n" +
                "Contact:\n" +
                "Email- " + contact.getEmail() + "\n" +
                "Tel- " + contact.getMobileNumber() + "\n" +
                "Username: " + username + "\n" +  // Include username in toString
                "Password: " + password + "\n" +
                ".......................";
    }

    public Long getAdminId() {
        return adminId;
    }

    public Name getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String getUsername() {  // New getter for username
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {

        private Long adminId;
        private Name name;
        private Contact contact;
        private String username;  // New field for username
        private String password;

        public Builder setAdminId(Long adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder setUsername(String username) {  // New setter for username
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder copyAdmin(Admin a) {
            this.adminId = a.adminId;
            this.name = a.name;
            this.contact = a.contact;
            this.username = a.username;  // Copy username
            this.password = a.password;
            return this;
        }

        public Admin buildAdmin() {
            return new Admin(this);
        }
    }
}
