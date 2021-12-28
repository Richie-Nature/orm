package com.practice.entitypractice.data.candy;

import java.util.List;

public interface CandyDAO {

    List<Candy> findAll();
    void addToDelivery(Long candyId, Long deliveryId);
    List<Candy> findByDelivery(Long deliveryId);
}
