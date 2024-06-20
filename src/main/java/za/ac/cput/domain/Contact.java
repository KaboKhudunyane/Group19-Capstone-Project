package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
@Entity
public class Contact {
    @Id
    private String contactId;
    private String email;
    private int mobileNo;

    protected Contact() {}

    private Contact(Builder builder) {
        this.contactId = builder.contactId;
        this.email = builder.email;
        this.mobileNo = builder.mobileNo;
    }

    public String getContactId() {
        return contactId;
    }

    public String getEmail() {
        return email;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return mobileNo == contact.mobileNo &&
                Objects.equals(contactId, contact.contactId) &&
                Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, email, mobileNo);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                '}';
    }

    public static class Builder {
        private String contactId;
        private String email;
        private int mobileNo;

        public Builder setContactId(String contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMobileNo(int mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public Builder copyContact(Contact contact) {
            this.contactId = contact.contactId;
            this.email = contact.email;
            this.mobileNo = contact.mobileNo;
            return this;
        }

        public Contact buildContact() {
            return new Contact(this);
        }
    }
}
