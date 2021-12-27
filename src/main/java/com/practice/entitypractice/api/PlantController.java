package com.practice.entitypractice.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.practice.entitypractice.data.inventory.Plant;
import com.practice.entitypractice.data.inventory.PlantDTO;
import com.practice.entitypractice.data.inventory.Views;
import com.practice.entitypractice.service.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

//    @GetMapping
//    public PlantDTO getPlantDTO(String name){
//        return convertEntityToPlantDTO(plantService.getPlantByName(name));
//    }
    @PostMapping("/new")
    public PlantDTO create(@RequestBody Plant plant) {
        return convertEntityToPlantDTO(plantService.save(plant));
    }

    @GetMapping("/under-price/{price}")
    public List<PlantDTO> getPlantsCheaperThan(@PathVariable BigDecimal price) {
       return plantService.getPlantsCheaperThan(price).stream()
               .map(plant -> convertEntityToPlantDTO(plant))
               .collect(Collectors.toList());
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.isPlantDelivered(id);
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    private static PlantDTO convertEntityToPlantDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }

    private static Plant convertPlantDTOToEntity(PlantDTO plantDTO) {
        Plant plant = new Plant();
        BeanUtils.copyProperties(plantDTO, plant);
        return plant;
    }
}
