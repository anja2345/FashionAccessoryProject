package com.shop.fashionaccessory.country.service;

import com.shop.fashionaccessory.country.Country;


import java.util.List;

public interface CountryServiceLocal {
    CountryServiceLocal SERVICE = new CountryService();

    void create(Country country);

    void edit(Country country);

    void remove(Country country);

    void remove(Integer id);

    Country find(Integer id);

    List<Country> findAll();
    Country findByName(String name);
}

