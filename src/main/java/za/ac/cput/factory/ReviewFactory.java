package za.ac.cput.factory;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;

public class ReviewFactory {
    public static Review buildReview(String ReviewId, String bookingId, Double Rating, String comment, String date) {
        if(Helper.isNullOrEmpty(ReviewId) || Helper.isNullOrEmpty(bookingId)
                ||  Rating == null || Helper.isNullOrEmpty(comment)
                || Helper.isNullOrEmpty(date) )
            return null;

        return new Review.Builder()
                .setReviewId(ReviewId)
                .setBookingId(bookingId)
                .setRating(Rating)
                .build();
    }

    public static Review buildReview( String bookingId, Double Rating, String comment, String date) {
        if( Helper.isNullOrEmpty(bookingId)
                ||  Rating == null || Helper.isNullOrEmpty(comment)
                || Helper.isNullOrEmpty(date) )
            return null;

        return new Review.Builder()
                .setBookingId(bookingId)
                .setRating(Rating)
                .build();

    }
}
