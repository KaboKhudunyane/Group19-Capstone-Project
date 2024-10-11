package za.ac.cput.domain;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Account account;

    @Embedded
    private Name name;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @Lob
    @Column(name = "License", length = 65535)
    private byte[] license;

    @Lob
    @Column(name = "Identity Document", length = 65535)
    private byte[] identityDocument;

    // Enum for Role
    public enum Role {
        USER,
        ADMIN
    }

    // Default constructor
    public User() {}

    // Private constructor for the Builder pattern
    private User(Builder builder) {
        this.account = builder.account;
        this.name = builder.name;
        this.contact = builder.contact;
        this.address = builder.address;
        this.license = builder.license;
        this.identityDocument = builder.identityDocument;
        this.role = builder.role;
    }

    // Getters
    public Long getUserID() {
        return userID;
    }

    public Role getRole() {
        return role;
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

    public Address getAddress() {
        return address;
    }

    public byte[] getLicense() {
        return license;
    }

    public byte[] getIdentityDocument() {
        return identityDocument;
    }

    // Setters
    public void setRole(Role role) {
        this.role = role;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLicense(byte[] license) {
        this.license = license;
    }

    public void setIdentityDocument(byte[] identityDocument) {
        this.identityDocument = identityDocument;
    }

    // Equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                role == user.role &&
                Objects.equals(account, user.account) &&
                Objects.equals(name, user.name) &&
                Objects.equals(contact, user.contact) &&
                Objects.equals(address, user.address) &&
                Arrays.equals(license, user.license) &&
                Arrays.equals(identityDocument, user.identityDocument);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userID, role, account, name, contact, address);
        result = 31 * result + Arrays.hashCode(license);
        result = 31 * result + Arrays.hashCode(identityDocument);
        return result;
    }

    // Builder class for creating User objects
    public static class Builder {
        private Account account;
        private Name name;
        private Contact contact;
        private Address address;
        private byte[] license;
        private byte[] identityDocument;
        private Role role;

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

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setLicense(byte[] license) {
            this.license = license;
            return this;
        }

        public Builder setIdentityDocument(byte[] identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder copyUser(User user) {
            this.role = user.role;
            this.account = user.account;
            this.name = user.name;
            this.contact = user.contact;
            this.address = user.address;
            this.license = user.license;
            this.identityDocument = user.identityDocument;

            return this;
        }

        public User buildUser() {
            return new User(this);
        }
    }
}
