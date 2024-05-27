package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.CountryDto;
import com.example.ProjectBackend.dtos.ProvinceDto;
import com.example.ProjectBackend.entities.Country;
import com.example.ProjectBackend.entities.Province;
import com.example.ProjectBackend.repositories.CountryRepository;
import com.example.ProjectBackend.repositories.ProvinceRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<Country> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        Map<Integer, List<Province>> provincesByCountryId = provinceRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(province -> province.getCountry().getId()));
        for (Country country : countries) {
            country.setProvinces(provincesByCountryId.getOrDefault(country.getId(), List.of()));
        }

        return countries;
    }
}
