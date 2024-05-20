package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class SupportTicket {
    @Id
    private String ticketID;
    private String userID;
    private String subject;
    private String description;
    private String dateCreated;
    private String status;

    protected SupportTicket(){}

    private SupportTicket(Builder builder) {
        this.ticketID = builder.ticketID;
        this.userID = builder.userID;
        this.subject = builder.subject;
        this.description =builder. description;
        this.dateCreated = builder.dateCreated;
        this.status = builder.status;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTicket that = (SupportTicket) o;
        return Objects.equals(ticketID, that.ticketID) && Objects.equals(userID, that.userID) && Objects.equals(subject, that.subject) && Objects.equals(description, that.description) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, userID, subject, description, dateCreated, status);
    }

    @Override
    public String toString() {
        return "SupportTicket{" +
                "ticketID='" + ticketID + '\'' +
                ", userID='" + userID + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder{
        private String ticketID;
        private String userID;
        private String subject;
        private String description;
        private String dateCreated;
        private String status;

        public Builder setTicketID(String ticketID) {
            this.ticketID = ticketID;
            return this;
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy (SupportTicket supportTicket){
            this.ticketID = supportTicket.ticketID;
            this.userID = supportTicket.userID;
            this.subject = supportTicket.subject;
            this.description =supportTicket. description;
            this.dateCreated = supportTicket.dateCreated;
            this.status = supportTicket.status;
            return this;
        }
        public SupportTicket build(){return new SupportTicket(this);}
    }
}
