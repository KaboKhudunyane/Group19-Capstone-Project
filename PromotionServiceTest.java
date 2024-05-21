package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Promotion;
import za.ac.cput.factory.PromotionFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    private PromotionService promotionService;

    @Test
    void testCreate() {
        Promotion promotion = PromotionFactory.createPromotion("001", "Spring Sale", "2023-05-22",
                "2023-06-22", "20%");
        Promotion createdPromotion = promotionService.create(promotion);
        assertNotNull(createdPromotion);
        System.out.println("Created Promotion: " + createdPromotion);
    }

    @Test
    void testRead() {
        Promotion readPromotion = promotionService.read("001");
        assertNotNull(readPromotion);
        System.out.println("Read Promotion: " + readPromotion);
    }

    @Test
    void testUpdate() {
        Promotion newPromotion = new Promotion.Builder()
                .copyPromotion(PromotionFactory.createPromotion("001", "Spring Sale", "2023-05-22",
                        "2023-06-22", "20%"))
                .setDiscount("25%")
                .buildPromotion();
        Promotion updatedPromotion = promotionService.update(newPromotion);
        assertNotNull(updatedPromotion);
        System.out.println("Updated Promotion: " + updatedPromotion);
    }

    @Test
    void testDelete() {
        promotionService.delete("001");
        System.out.println("Promotion deleted successfully");
    }
}

