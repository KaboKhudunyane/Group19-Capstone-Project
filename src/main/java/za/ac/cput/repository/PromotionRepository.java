package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Promotion;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    Promotion findByPromotionID(String promotionID);

    void deleteByPromotionID(String promotionID);

    //List<Promotion> getAllPromotions();
}

