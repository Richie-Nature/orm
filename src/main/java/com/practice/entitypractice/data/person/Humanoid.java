package com.practice.entitypractice.data.person;

import javax.persistence.*;
import java.util.List;

@Entity //use @MappedSuperClass instead to by default create a table_per_class
//@Inheritance(strategy = InheritanceType.JOINED)
// InheritanceType.SINGLE_TABLE (default), InheritanceType.TABLE_PER_CLASS
public abstract class Humanoid {
    @Id
    @GeneratedValue
    Long id;

    @OneToMany(mappedBy = "humanoid", fetch = FetchType.LAZY)
    List<Outfit> outfits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<Outfit> outfits) {
        this.outfits = outfits;
    }
}
