
package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.enums.UserRole;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstName;
    private String lastName;

    private String email;
    private String cellNum;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Embedded
    private Address address;

    @Lob
    @Column(name = "License", columnDefinition = "longBlob")
    private byte[] license;

    @Lob
    @Column(name = "Identity Document", columnDefinition = "longBlob")
    private byte[] identityDocument;


    // Default constructor
    public User() {
    }

    // Private constructor for the Builder pattern
    private User(Builder builder) {
        this.userID = builder.userID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.password = builder.password;
        this.userRole = builder.userRole;
        this.cellNum = builder.cellNum;
        this.email = builder.email;
        this.address = builder.address;
        this.license = builder.license;
        this.identityDocument = builder.identityDocument;

    }

    public Long getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCellNum() {
        return cellNum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public UserRole getUserRole() {
        return userRole;
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

    public String getIdentityDocumentBase64() {
        return identityDocument != null ? Base64.getEncoder().encodeToString(identityDocument) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserID(), user.getUserID()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getCellNum(), user.getCellNum()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && getUserRole() == user.getUserRole() && Objects.equals(getAddress(), user.getAddress()) && Objects.deepEquals(getLicense(), user.getLicense()) && Objects.deepEquals(getIdentityDocument(), user.getIdentityDocument());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getFirstName(), getLastName(), getEmail(), getCellNum(), getUsername(), getPassword(), getUserRole(), getAddress(), Arrays.hashCode(getLicense()), Arrays.hashCode(getIdentityDocument()));
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", cellNum='" + cellNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", address=" + address +
                ", license=" + Arrays.toString(license) +
                ", identityDocument=" + Arrays.toString(identityDocument) +
                '}';
    }

    // Builder class for creating User objects
    public static class Builder {
        private Long userID;
        private String firstName;
        private String lastName;
        private String email;
        private String cellNum;
        private String username;
        private String password;
        private Address address;
        private UserRole userRole;
        private byte[] license;
        private byte[] identityDocument;

        public Builder setUserID(Long userID) {
            this.userID = userID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setCellNum(String cellNum) {
            this.cellNum = cellNum;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }


        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setUserRole(UserRole userRole) {
            this.userRole = userRole;
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

        public Builder copy(User user){
            this.userID = user.userID;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.username = user.username;
            this.password = user.password;
            this.userRole = user.userRole;
            this.cellNum = user.cellNum;
            this.email = user.email;
            this.address = user.address;
            this.license = user.license;
            this.identityDocument = user.identityDocument;

            return this;
        }


        public User build() {
            return new User(this);
        }
    }
}
