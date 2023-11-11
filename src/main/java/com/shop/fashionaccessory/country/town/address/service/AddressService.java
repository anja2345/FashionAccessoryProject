package com.shop.fashionaccessory.country.town.address.service;

import com.shop.fashionaccessory.country.town.address.Address;
import com.shop.fashionaccessory.service.BaseService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

 class AddressService extends BaseService<Address> implements AdressServiceLocal {
    public AddressService() {
        super(Address.class);
    }
    @Override
    public Address findByName(String name) {
        try {
            Query query =getEntityManager().createNamedQuery("Address.findByName");
            query.setParameter("name", name);
            return (Address) query.getSingleResult();
        }catch (NoResultException exception){
            throw  exception;
        }
}}
