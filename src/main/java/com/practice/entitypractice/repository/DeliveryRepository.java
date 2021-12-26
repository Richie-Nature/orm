package com.practice.entitypractice.repository;

import com.practice.entitypractice.data.delivery.Delivery;
import com.practice.entitypractice.data.inventory.Plant;
import com.practice.entitypractice.data.projection.RecipientAndPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        entityManager.remove(find(id));
    }

    public List<Delivery> findByRecipientName(String name) {
        TypedQuery<Delivery> query = entityManager.createQuery("Delivery.findByRecipientName", Delivery.class);
        query.setParameter("recipientName", name);
        return query.getResultList();
    }

    // One possible way to solve this - query a list of Plants with deliveryId matching
    // the one provided and sum their prices.
    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = criteriaBuilder.createQuery(RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);
        query.select(criteriaBuilder.construct(
                RecipientAndPrice.class,
                root.get("delivery").get("name"),
                criteriaBuilder.sum(root.get("price"))
        )).where(criteriaBuilder.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
