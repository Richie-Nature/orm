package com.practice.entitypractice.repository;

import com.practice.entitypractice.data.person.Humanoid;
import com.practice.entitypractice.data.person.Outfit;
import com.practice.entitypractice.data.person.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PersonQuery {
    @PersistenceContext
    EntityManager entityManager;

    private static final String FIND_PERSON_BY_COMPOSER =
            "select p from Person p " +
                    "where p.favoriteComposer like :favoriteComposer";
    private static final String FIND_HUMANOID_BY_OUTFIT =
            "select h from Humanoid h where :outfit member of h.outfits";

    public Person findPersonByFavoriteComposer(String favoriteComposer) {
        TypedQuery<Person> query = entityManager.createQuery(FIND_PERSON_BY_COMPOSER, Person.class);
        query.setParameter("favoriteComposer", favoriteComposer);
        return query.getSingleResult();
    }

    public List<Humanoid> findHumanoidByOutfit(Outfit o) {
        TypedQuery<Humanoid> query = entityManager.createQuery(FIND_HUMANOID_BY_OUTFIT, Humanoid.class);
        query.setParameter("outfit", o);
        return query.getResultList();
    }

    public List<Humanoid> findHumanoidByOutfitCriteria(Outfit o) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Humanoid> criteria = cb.createQuery(Humanoid.class);
        Root<Humanoid> root = criteria.from(Humanoid.class);

        criteria.select(root).where(cb.isMember(o, root.get("outfits")));
        return entityManager.createQuery(criteria).getResultList();
    }
}
