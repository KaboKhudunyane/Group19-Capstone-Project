package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Administrators")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Embedded
    private Account account;

    @Embedded
    private Name name;

    @Embedded
    private Contact contact;

    public Admin() {
    }
    private Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.account = builder.account;
        this.name = builder.name;
        this.contact = builder.contact;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminId, admin.adminId) &&
                Objects.equals(account, admin.account) &&
                Objects.equals(name, admin.name) &&
                Objects.equals(contact, admin.contact);
    }
    @Override
    public int hashCode() {
        return Objects.hash(adminId, account, name, contact);
    }
    @Override
    public String toString() {
        return ".........Administrator......." + "\n" +
                "adminId=" + adminId + "\n" +
                "Username: " + account.getUsername() + "\n" +
                "Password: " + account.getPassword() + "\n" +  // Added password here
                "Name: " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName() + "\n" +
                "Contact:" + "\n" +
                "Email- " + contact.getEmail() + "\n" +
                "Tel- " + contact.getMobileNumber() + "\n" +
                ".......................";
    }
    public Long getAdminId() {
        return adminId;
    }
    public Account getAccount() {
        return account;
    }
    public Name getName() {
        return name;
    }
    public Contact getContact() {
        return contact;
    }
    public static class Builder {
        private Long adminId;
        private Account account;
        private Name name;
        private Contact contact;
        public Builder setAdminId(Long adminId) {
            this.adminId = adminId;
            return this;
        }
        public Builder setAccount(Account account) {
            this.account = account;
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
        public Builder copyAdmin(Admin admin) {
            this.adminId = admin.adminId;
            this.account = admin.account;
            this.name = admin.name;
            this.contact = admin.contact;
            return this;
        }
        public Admin buildAdmin() {
            return new Admin(this);
        }
    }
}