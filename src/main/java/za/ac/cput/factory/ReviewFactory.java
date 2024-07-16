package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class ReviewFactory {
    public static Review buildReview(Booking booking, int rating, String comment, LocalDate reviewDate) {
        if (booking == null || rating <= 0 || Helper.isNullOrEmpty(comment) || reviewDate == null) {
            return null;
        }

        return new Review.Builder()
                .setBooking(booking)
                .setRating(rating)
                .setComment(comment)
                .setReviewDate(reviewDate)
                .buildReview();
    }
}
