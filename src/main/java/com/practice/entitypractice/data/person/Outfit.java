package com.practice.entitypractice.data.person;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Outfit.findByHat",
                query = "select o from Outfit o where o.hat = :hat"
        ),
        @NamedQuery(
                name = "Outfit.findBySock",
                query = "select 0 from Outfit o where o.sock = :sock"
        )
})
@Entity
public class Outfit {
    @Id
    @GeneratedValue
    private Long id;

    private String gloves;
    private String hat;
    private String dress;
    private String sock;

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

    public String getSock() {
        return sock;
    }

    public void setSock(String sock) {
        this.sock = sock;
    }
}
