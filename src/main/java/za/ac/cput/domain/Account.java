package za.ac.cput.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class Account implements Serializable {
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    protected Account() {}
    private Account(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
    @Override
    public String toString() {
        return "Contact{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public static class Builder {
        private String username;
        private String password;
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Account.Builder copyAccount(Account account) {
            this.username = account.username;
            this.password = account.password;
            return this;
        }
        public Account buildAccount() {
            return new Account(this);
        }
    }
}