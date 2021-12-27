package com.practice.entitypractice.repository;

import com.practice.entitypractice.data.person.Humanoid;
import com.practice.entitypractice.data.person.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * JpaRepository is an extension of CrudRepository that provides some other
 * JPA-specific methods, such as getOne, which returns an Entity reference,
 * just like entityManager.getReference
 */
public interface HumanoidRepository extends JpaRepository<Humanoid, Long> {
    //associations and attributes can be referenced by chaining attribute
    //names. Here i'm referencing Humanoid.outfits.hat
    List<Humanoid> findAllByOutfitsHat(String hat);

    //you can provide specific JPQL Queries
    @Query("select h from Humanoid h where :outfit member of h.outfits")
    List<Humanoid> findAllByOutfit(@Param("outfit")Outfit outfit);

    //does same as above but uses key word
    List<Humanoid> findAllByOutfitsContaining(Outfit outfit);

    //automatically uses query named Humanoid.findAllNamedQuery
//    List<Humanoid> findAllNamedQuery(Outfit outfit);
}
