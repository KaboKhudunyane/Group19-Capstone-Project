package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Review {
    @Id
    private String reviewId;
    private String bookingId;
    private double rating;
    private String comment;
    private String date;

    protected Review() {}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.bookingId = builder.bookingId;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.date = builder.date;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Double.compare(review.rating, rating) == 0 &&
                Objects.equals(reviewId, review.reviewId) &&
                Objects.equals(bookingId, review.bookingId) &&
                Objects.equals(comment, review.comment) &&
                Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, bookingId, rating, comment, date);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public static class Builder {
        private String reviewId;
        private String bookingId;
        private double rating;
        private String comment;
        private String date;

        public Builder setReviewId(String reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setBookingId(String bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.bookingId = review.bookingId;
            this.rating = review.rating;
            this.comment = review.comment;
            this.date = review.date;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}

