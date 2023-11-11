package com.shop.fashionaccessory.country.town.address.service;

import com.shop.fashionaccessory.country.town.address.Address;

import java.util.List;

public interface AdressServiceLocal {

    AdressServiceLocal SERVICE = new AddressService();

    void create(Address address);

    void edit(Address address);

    void remove(Address address);

    void remove(Integer id);

    Address find(Integer id);

    List<Address> findAll();

    Address findByName(String name);
}
