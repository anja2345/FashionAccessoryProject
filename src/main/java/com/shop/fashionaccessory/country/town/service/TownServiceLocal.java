package com.shop.fashionaccessory.country.town.service;

import com.shop.fashionaccessory.country.Country;
import com.shop.fashionaccessory.country.town.Town;

import java.util.List;

public interface TownServiceLocal {
    TownServiceLocal SERVICE = new TownService();

    void create(Town town);

    void edit(Town town);

    void remove(Town town);

    void remove(Integer id);

    Town find(Integer id);

    List<Town> findAll();

    List<Town> findByCountry(Country country);
    Town findByName(String name);

}
