package com.example.ProjectBackend.dtos;

import java.util.List;

public class CountryDto {
    private Integer id;
    private String name;
    private List<ProvinceDto> provinces;

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

    public List<ProvinceDto> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceDto> provinces) {
        this.provinces = provinces;
    }
}
