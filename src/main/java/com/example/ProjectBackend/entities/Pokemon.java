package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sprite;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer subtotal;

    @Column(nullable = false)
    private Integer taxes;

    @Column(nullable = false)
    private Integer save;

    @Column(nullable = false, length = 1024)
    private String description;

    @Column(nullable = false)
    private Integer generation;

    @Column(nullable = false)
    private String cries;

    @Column(nullable = false)
    private Boolean isLegendary;

    @Column(nullable = false)
    private String icon_sprite;

//    @Column(length = 1024)
//    private List<OtherSprites> other_sprites;

//    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
//    private List<Evolution> evolutions;


    public Pokemon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getTaxes() {
        return taxes;
    }

    public void setTaxes(Integer taxes) {
        this.taxes = taxes;
    }

    public Integer getSave() {
        return save;
    }

    public void setSave(Integer save) {
        this.save = save;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public String getCries() {
        return cries;
    }

    public void setCries(String cries) {
        this.cries = cries;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    public void setLegendary(Boolean legendary) {
        isLegendary = legendary;
    }

    public String getIcon_sprite() {
        return icon_sprite;
    }

    public void setIcon_sprite(String icon_sprite) {
        this.icon_sprite = icon_sprite;
    }


//    public List<Evolution> getEvolutions() {
//        return evolutions;
//    }
//
//    public void setEvolutions(List<Evolution> evolutions) {
//        this.evolutions = evolutions;
//    }
}
