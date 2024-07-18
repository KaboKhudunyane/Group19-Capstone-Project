package za.ac.cput.domain;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name = "SupportTickets")
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;
    @ManyToOne
    private User user;
    private String subject;
    private String message;
    private LocalDate dateCreated;
    protected SupportTicket() {}
    private SupportTicket(Builder builder) {
        this.user = builder.user;
        this.subject = builder.subject;
        this.message = builder.message;
        this.dateCreated = builder.dateCreated;
    }
    public Long getTicketID() {
        return ticketID;
    }
    public User getUser() {
        return user;
    }
    public String getSubject() {
        return subject;
    }
    public String getMessage() {
        return message;
    }
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTicket that = (SupportTicket) o;
        return Objects.equals(ticketID, that.ticketID) &&
                Objects.equals(user, that.user) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(message, that.message) &&
                Objects.equals(dateCreated, that.dateCreated);
    }
    @Override
    public int hashCode() {
        return Objects.hash(ticketID, user, subject, message, dateCreated);
    }
    @Override
    public String toString() {
        return "SupportTicket{" +
                "ticketID=" + ticketID +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
    public static class Builder {
        private User user;
        private String subject;
        private String message;
        private LocalDate dateCreated;
        public Builder setUser(User user) {
            this.user = user;
            return this;
        }
        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setDateCreated(LocalDate dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }
        public Builder copy(SupportTicket supportTicket) {
            this.user = supportTicket.user;
            this.subject = supportTicket.subject;
            this.message = supportTicket.message;
            this.dateCreated = supportTicket.dateCreated;
            return this;
        }
        public SupportTicket build() {
            return new SupportTicket(this);
        }
    }
}