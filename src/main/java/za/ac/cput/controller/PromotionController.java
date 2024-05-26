package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Promotion;
import za.ac.cput.service.PromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/save")
    public Promotion save(@RequestBody Promotion promotion) {
        return promotionService.create(promotion);
    }

    @GetMapping("/read/{promotionId}")
    public Promotion read(@PathVariable String promotionId) {
        return promotionService.read(promotionId);
    }

    @PutMapping("/update")
    public Promotion update(@RequestBody Promotion promotion) {
        return promotionService.update(promotion);
    }

    @DeleteMapping("/delete/{promotionId}")
    public void delete(@PathVariable String promotionId) {
        promotionService.delete(promotionId);
    }

    @GetMapping("/get-all")
    public List<Promotion> getAllPromotions() {
        return promotionService.getAll();
    }
}

