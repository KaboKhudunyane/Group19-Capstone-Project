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
    @Embedded
    private Name name;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    private Boolean license;
    @Lob
    @Column(length = 65535)
    private byte[] picture;
    protected User() {}
    private User(Builder builder) {
        this.name = builder.name;
        this.contact = builder.contact;
        this.address = builder.address;
        this.license = builder.license;
        this.picture = builder.picture;
    }
    public Long getUserID() {
        return userID;
    }
    public Name getName() {return name;}
    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean getLicense() {
        return license;
    }

    public byte[] getPicture() {
        return picture;
    }

    @Override
    public boolean equals(Object userObject) {
        if (this == userObject) return true;
        if (userObject == null || getClass() != userObject.getClass()) return false;
        User user = (User) userObject;
        return Objects.equals(name, user.name) &&
                Objects.equals(contact, user.contact) &&
                Objects.equals(address, user.address) &&
                Objects.equals(license, user.license) &&
                Objects.equals(picture, user.picture);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userID, name, contact, address, license);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    public static class Builder {
        private Name name;
        private Contact contact;
        private Address address;
        private Boolean license;
        private byte[] picture;

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
        public Builder setLicense(Boolean license) {
            this.license = license;
            return this;
        }
        public Builder setPicture(byte[] picture) {
            this.picture = picture;
            return this;
        }
        public Builder copyUser(User user) {
            this.name = user.name;
            this.contact = user.contact;
            this.address = user.address;
            this.license = user.license;
            this.picture = user.picture;
            return this;
        }
        public User buildUser() {
            return new User(this);
        }
    }
}