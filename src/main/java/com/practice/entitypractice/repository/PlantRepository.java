package com.practice.entitypractice.repository;

import com.practice.entitypractice.data.inventory.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    /////
    //check if a plant by this id exists where delivery has been completed
    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean completed);

    //using jpql directly
    @Query("select p.delivery.completed from Plant p where p.id = :plantId")
    Boolean deliveryCompleted(Long plantId);

    //to return a wrapper class, construct as projection
    @Query("select new java.lang.Boolean(p.delivery.completed) from Plant p where p.id = :plantId")
    Boolean deliveryCompletedBoolean(Long plantId);
    /////

    ////
    @Query("select p from Plant p where price < :price")
    List<Plant> findAllByPrice(@Param("price")BigDecimal price);

    List<Plant> findByPriceLessThan(BigDecimal price);
}
