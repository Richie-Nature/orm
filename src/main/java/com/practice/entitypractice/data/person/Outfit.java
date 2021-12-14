package com.practice.entitypractice.data.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Outfit {
    @Id
    @GeneratedValue
    private Long id;

    private String gloves;
    private String hat;
    private String dress;

    @ManyToOne
    private Humanoid humanoid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGloves() {
        return gloves;
    }

    public void setGloves(String gloves) {
        this.gloves = gloves;
    }

    public String getHat() {
        return hat;
    }

    public void setHat(String hat) {
        this.hat = hat;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }
}
