package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Promotion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PromotionFactoryTest {

    @Test
    public void testBuildPromotion() {
        // Create a promotion here
        Promotion promotion = PromotionFactory.createPromotion("001", "Spring Sale", "2023-05-22",
                "2023-06-22", "20%");
        assertNotNull(promotion);
        System.out.println(promotion);
    }

    @Test
    public void testBuildPromotionWithFail() {
        // Create a promotion with a null parameter so test fails
        Promotion promotion = PromotionFactory.createPromotion(null, null, null, null, null);
        assertNotNull(promotion);
        System.out.println(promotion);
    }
}

