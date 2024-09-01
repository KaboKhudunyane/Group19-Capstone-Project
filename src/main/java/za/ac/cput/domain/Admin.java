package za.ac.cput.domain;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "Administrators")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Embedded
    private Name name;

    @Embedded
    private Contact contact;

    @Column(nullable = false)  // Ensures that 'password' cannot be null in the database
    private String password;

    public Admin() {
    }

    private Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.name = builder.name;
        this.contact = builder.contact;
        this.password = builder.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminId, admin.adminId) &&
                Objects.equals(name, admin.name) &&
                Objects.equals(contact, admin.contact) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, name, contact, password);
    }

    @Override
    public String toString() {
        return ".........Administrator......." + "\n" +
                "adminId=" + adminId + "\n" +
                "Name: " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName() + "\n" +
                "Contact:" + "\n" +
                "Email- " + contact.getEmail() + "\n" +
                "Tel- " + contact.getMobileNumber() + "\n" +
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

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private Long adminId;
        private Name name;
        private Contact contact;
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

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder copyAdmin(Admin admin) {
            this.adminId = admin.adminId;
            this.name = admin.name;
            this.contact = admin.contact;
            this.password = admin.password;
            return this;
        }

        public Admin buildAdmin() {
            if (this.password == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            return new Admin(this);
        }
    }
}

