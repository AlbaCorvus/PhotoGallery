package com.mulcahy.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class User{

    @Id
    @GeneratedValue
    private int userId;

    private String name;

    private String password;

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "userId")
    private Collection<Category> category;

    @Lob
    private byte[] image;

    public User(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return userId;
    }

    private void setId(int id) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Category> getCategory() {
        return category;
    }

    public void setCategory(Collection<Category> category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public char getFirstLetter(){
        return name.charAt(0);
    }
    public String getTrailingLetters(){
        return name.substring(1,name.length());
    }
}