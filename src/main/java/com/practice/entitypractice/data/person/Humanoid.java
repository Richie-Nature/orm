package com.practice.entitypractice.data.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public abstract class Humanoid {
    @Id
    @GeneratedValue
    Long id;

    @OneToMany(mappedBy = "humanoid")
    List<Outfit> outfits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
