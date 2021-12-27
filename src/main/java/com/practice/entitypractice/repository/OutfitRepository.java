package com.practice.entitypractice.repository;

import com.practice.entitypractice.data.person.Outfit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutfitRepository extends CrudRepository<Outfit, Long> {

    //finds a single outfit by attribute
    Outfit findByHat(String hat);

    //operators like And/Or, Lessthan/greaterthan, null/notnull
    Outfit findByHatAndShoes(String hat, String shoes);
}
