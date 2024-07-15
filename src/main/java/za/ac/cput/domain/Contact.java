package za.ac.cput.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Contact implements Serializable {
    @Id
    private String email;
    private String mobileNumber;

    protected Contact() {}

    private Contact(Builder builder) {
        this.email = builder.email;
        this.mobileNumber = builder.mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public boolean equals(Object contactObject) {
        if (this == contactObject) return true;
        if (contactObject == null || getClass() != contactObject.getClass()) return false;
        Contact contact = (Contact) contactObject;
        return Objects.equals(email, contact.email) &&
                Objects.equals(mobileNumber, contact.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, mobileNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String email;
        private String mobileNumber;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }
        public Contact.Builder copyContact(Contact contact) {
            this.email = contact.email;
            this.mobileNumber = contact.mobileNumber;
            return this;
        }
        public Contact buildContact() {
            return new Contact(this);
        }
    }
}
