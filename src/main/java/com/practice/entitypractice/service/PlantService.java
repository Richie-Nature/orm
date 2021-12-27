package com.practice.entitypractice.service;

import com.practice.entitypractice.data.inventory.Plant;
import com.practice.entitypractice.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

    public Plant getPlantByName(String name) {
        return new Plant();
    }

    public Plant save(Plant plant) {
        return plantRepository.save(plant);
    }

    public Boolean isPlantDelivered(Long plantId) {
        //return plantRepository.deliveryCompleted(plantId);
        return plantRepository.existsPlantByIdAndDeliveryCompleted(plantId, true);
    }

    public List<Plant> getPlantsCheaperThan(BigDecimal price) {
        //return plantRepository.findAllByPrice(price);
        return plantRepository.findByPriceLessThan(price);
    }
}
