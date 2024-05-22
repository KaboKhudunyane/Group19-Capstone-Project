package za.ac.cput.factory;

import za.ac.cput.domain.Promotion;
import za.ac.cput.util.Helper;

public class PromotionFactory {
    public static Promotion createPromotion(String promotionID, String description, String startDate,
                                            String endDate, String discount) {

        if (Helper.isNullOrEmpty(promotionID) ||
                Helper.isNullOrEmpty(description) ||
                Helper.isNullOrEmpty(startDate) ||
                Helper.isNullOrEmpty(endDate) ||
                Helper.isNullOrEmpty(discount)) {
            return null;
        }

        return new Promotion.Builder()
                .setPromotionID(promotionID)
                .setDescription(description)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setDiscount(discount)
                .buildPromotion();
    }
}

