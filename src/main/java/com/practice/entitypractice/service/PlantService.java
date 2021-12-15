package com.practice.entitypractice.service;

import com.practice.entitypractice.data.inventory.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    public Plant getPlantByName(String name) {
        return new Plant();
    }
}
