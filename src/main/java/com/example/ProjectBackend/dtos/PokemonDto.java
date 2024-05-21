package com.example.ProjectBackend.dtos;


public class PokemonDto {
    private Integer id;
    private String name;
    private String sprite;
    private String type;
    private Integer weight;
    private Integer height;
    private Integer price;
    private Integer subtotal;
    private Integer taxes;
    private Integer save;
    private String description;
    private Integer generation;
    private String cries;
    private Boolean isLegendary;
//    private List<Evolution> evolutions;

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

//    public List<Evolution> getEvolutions() {
//        return evolutions;
//    }
//
//    public void setEvolutions(List<Evolution> evolutions) {
//        this.evolutions = evolutions;
//    }


}
