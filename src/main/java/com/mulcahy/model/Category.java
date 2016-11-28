package com.mulcahy.model;


import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
public class Category{

    @Id
    @GeneratedValue
    private int id;

    private int userId;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "#[0-9a-fA-F]{6}")
    private String colorCode;


    @OneToMany(cascade= CascadeType.ALL,mappedBy="userId")
    private Collection<Gif> gif;

    public Category(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Gif> getGif() {
        return gif;
    }

    public void setGif(Collection<Gif> gif) {
        this.gif = gif;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}