package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID; // Renamed from 'id' to 'reviewID'

    @ManyToOne
    private Booking booking;
    private int rating;

    @ManyToOne
    private User user;

    private String comment;
    private LocalDate reviewDate;

    protected Review() {}

    private Review(Builder builder) {
        this.booking = builder.booking;
        this.user = builder.user;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
    }

    public Long getReviewID() {
        return reviewID;
    } // Getter for 'reviewID'

    public Booking getBooking() {
        return booking;
    }

    public User getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return getRating() == review.getRating() && Objects.equals(getReviewID(), review.getReviewID()) && Objects.equals(getBooking(), review.getBooking()) && Objects.equals(getUser(), review.getUser()) && Objects.equals(getComment(), review.getComment()) && Objects.equals(getReviewDate(), review.getReviewDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewID(), getBooking(), getRating(), getUser(), getComment(), getReviewDate());
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", booking=" + booking +
                ", rating=" + rating +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }

    public static class Builder {
        private Booking booking;
        private User user;
        private int rating;
        private String comment;
        private LocalDate reviewDate;

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder copyReview(Review review) {
            this.booking = review.booking;
            this.user = review.user;
            this.rating = review.rating;
            this.comment = review.comment;
            this.reviewDate = review.reviewDate;
            return this;
        }

        public Review buildReview() {
            return new Review(this);
        }
    }
}
