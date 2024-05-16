package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_evolutions")
public class ProductEvolution {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sprite;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
