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
    private String comment;
    private LocalDate reviewDate;

    protected Review() {}

    private Review(Builder builder) {
        this.booking = builder.booking;
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
        return rating == review.rating &&
                Objects.equals(reviewID, review.reviewID) &&
                Objects.equals(booking, review.booking) &&
                Objects.equals(comment, review.comment) &&
                Objects.equals(reviewDate, review.reviewDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewID, booking, rating, comment, reviewDate);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", booking=" + booking +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }

    public static class Builder {
        private Booking booking;
        private int rating;
        private String comment;
        private LocalDate reviewDate;

        public Builder setBooking(Booking booking) {
            this.booking = booking;
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
