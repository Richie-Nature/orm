package com.practice.entitypractice;

import com.practice.entitypractice.data.delivery.Delivery;
import com.practice.entitypractice.data.inventory.Plant;
import com.practice.entitypractice.repository.PlantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
public class DataJpaTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PlantRepository plantRepository;


//	@BeforeAll
//	public void setup() {
//		List<Plant> plants = getPlants();
//		plants.forEach((plant) -> {
//			plant.setDelivery(getDelivery());
//		});
//		getDelivery().setPlants(plants);
//	}

    @Test
    void testPriceLessThan() throws Exception {
        List<Plant> plants = getPlants().stream().map(plant -> testEntityManager.persist(
                new Plant(plant.getName(), plant.getPrice())
        )).collect(Collectors.toList());
        List<Plant> cheaperPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(8.00));

        Assertions.assertNotNull(cheaperPlants);
        Assertions.assertEquals(1, cheaperPlants.size(), "Size");
        Assertions.assertEquals(plants.get(1).getId(), cheaperPlants.get(0).getId(), "Id");
    }

    @Test
    void testDeliveryCompleted() throws Exception {
        Plant plant = testEntityManager.persist
                (new Plant(getPlants().get(0).getName(), getPlants().get(0).getPrice()));
        Delivery delivery = getDelivery();

        Delivery savedDelivery = testEntityManager.persist
                (new Delivery(delivery.getRecipientName(),
                        delivery.getAddress(),
                        delivery.getDeliveryTime()));
        delivery.setPlants(Collections.singletonList(plant));
        plant.setDelivery(savedDelivery);

        Assertions.assertFalse(plantRepository.existsPlantByIdAndDeliveryCompleted(plant.getId(), true));
        savedDelivery.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompletedBoolean(plant.getId()));
    }

    /**
     * creates plant to test
     */
    private List<Plant> getPlants() {
        Plant plant = new Plant();
        plant.setName("Cactus");
        plant.setPrice(BigDecimal.valueOf(9.00));

        Plant plant2 = new Plant();
        plant2.setName("Tomatoes");
        plant2.setPrice(BigDecimal.valueOf(6.00));

        List<Plant> plants = new ArrayList<>();
        plants.add(plant);
        plants.add(plant2);
        return plants;
    }

    /**
     * creates delivery to test
     */
    private Delivery getDelivery() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryTime(LocalDateTime.parse("2020-03-07T18:07"));
        delivery.setRecipientName("Terry");
        delivery.setAddress("123, some hal street");
        return delivery;
    }

}
