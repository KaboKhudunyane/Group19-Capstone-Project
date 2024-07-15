package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class User {
    @Id
    private String userID;
    @Embedded
    private Name name;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    private Boolean license;
    private String picture;

    protected User() {}

    private User(Builder builder) {
        this.userID = builder.userID;
        this.name = builder.name;
        this.contact = builder.contact;
        this.address = builder.address;
        this.license = builder.license;
        this.picture = builder.picture;
    }

    public String getUserID() {
        return userID;
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

    public Boolean getLicense() {
        return license;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public boolean equals(Object userObject) {
        if (this == userObject) return true;
        if (userObject == null || getClass() != userObject.getClass()) return false;
        User user = (User) userObject;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(name, user.name) &&
                Objects.equals(contact, user.contact) &&
                Objects.equals(address, user.address) &&
                Objects.equals(license, user.license) &&
                Objects.equals(picture, user.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, contact, address, license, picture);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name=" + name +
                ", contact=" + contact +
                ", address=" + address +
                ", license=" + license +
                ", picture='" + picture + '\'' +
                '}';
    }

    public static class Builder {
        private String userID;
        private Name name;
        private Contact contact;
        private Address address;
        private Boolean license;
        private String picture;

        public Builder setUserID(String userID) {
            this.userID = userID;
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

        public Builder setLicense(Boolean license) {
            this.license = license;
            return this;
        }

        public Builder setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder copyUser(User user) {
            this.userID = user.userID;
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
