package com.practice.entitypractice.data.person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class PersonManager {

    @PersistenceContext
    EntityManager entityManager;

    public void persistExample(Person p) {
        entityManager.persist(p); //write p to the database
        p.setFavoriteComposer("Johann Strauss II"); //will update database
    }

    public void findExample(Long id) {
        Person p = entityManager.find(Person.class, id); //retrieve an instance by its key
        p.setFavoriteComposer("Sir Malcolm Arnold"); //will update database
    }

    public void getReferenceExample(Long personId, Long outfitId) {
        Person p = entityManager.find(Person.class, personId);
        Outfit outfitRef = entityManager.getReference(Outfit.class, outfitId);
        p.getOutfits().add(outfitRef);
    }

    public void mergeExample(Person detachedPerson) {
        detachedPerson.setFavoriteComposer("Rimsky Korsakov");
        Person managedPerson = entityManager.merge(detachedPerson); //will have no effect on database
        detachedPerson.setFavoriteComposer("Antonio Salieri"); //will overwrite Korsakov
        managedPerson.setFavoriteComposer("C.P.E. Bach");
    }

    public void deleteExample(Long id) {
        Person p = entityManager.find(Person.class, id); //retrieve an instance by its key
        entityManager.remove(p);
    }
}
