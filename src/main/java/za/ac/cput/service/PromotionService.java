package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Promotion;
import za.ac.cput.repository.PromotionRepository;

import java.util.List;

@Service
public class PromotionService implements IService<Promotion, String> {
    private PromotionRepository promotionRepository;

    @Autowired
    PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion create(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion read(String promotionID) {
        return promotionRepository.findByPromotionID(promotionID).orElse(null);
    }

    @Override
    public Promotion update(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void delete(String promotionID) {
        promotionRepository.deleteByPromotionID(promotionID);
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }
}
