package za.ac.cput.domain;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String userID;
    private Name name;
    private Contact contact;
    private Address address;
    private Boolean license;
    private String role;
    private String picture;
    private Boolean verified;

    protected User() {}

    private User(Builder builder) {
        this.userID = builder.userID;
        this.name = builder.name;
        this.contact = builder.contact;
        this.address = builder.address;
        this.license = builder.license;
        this.role = builder.role;
        this.picture = builder.picture;
        this.verified = builder.verified;
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

    public String getRole() {
        return role;
    }

    public String getPicture() {
        return picture;
    }

    public Boolean getVerified() {
        return verified;
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
                Objects.equals(role, user.role) &&
                Objects.equals(picture, user.picture) &&
                Objects.equals(verified, user.verified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, contact, address, license, role, picture, verified);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name=" + name +
                ", contact=" + contact +
                ", address=" + address +
                ", license=" + license +
                ", role='" + role + '\'' +
                ", picture='" + picture + '\'' +
                ", verified=" + verified +
                '}';
    }

    public static class Builder {
        private String userID;
        private Name name;
        private Contact contact;
        private Address address;
        private Boolean license;
        private String role;
        private String picture;
        private Boolean verified;

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

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder setVerified(Boolean verified) {
            this.verified = verified;
            return this;
        }
        public Builder copyUser(User user) {
            this.userID = user.userID;
            this.name = user.name;
            this.contact = user.contact;
            this.address = user.address;
            this.license = user.license;
            this.role = user.role;
            this.picture = user.picture;
            this.verified = user.verified;
            return this;
        }

        public User buildUser() {
            return new User(this);
        }
    }
}
