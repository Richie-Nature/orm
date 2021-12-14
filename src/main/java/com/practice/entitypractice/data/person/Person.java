package com.practice.entitypractice.data.person;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person extends Humanoid {

    @Type(type="nstring")
    private String name;

    private int age;

    @Column(name="composer", length=512)
    private String favoriteComposer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavoriteComposer() {
        return favoriteComposer;
    }

    public void setFavoriteComposer(String favoriteComposer) {
        this.favoriteComposer = favoriteComposer;
    }
}
