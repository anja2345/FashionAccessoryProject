package com.shop.fashionaccessory.country.service;

import com.shop.fashionaccessory.country.Country;
import com.shop.fashionaccessory.service.BaseService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

class CountryService extends BaseService<Country> implements CountryServiceLocal {

    public CountryService() {

        super(Country.class);
    }

    @Override
    public Country findByName(String name) {
        try {
            Query query =getEntityManager().createNamedQuery("Country.findByName");
            query.setParameter("name", name);
            return (Country) query.getSingleResult();
        }catch (NoResultException exception){
            throw  exception;
        }
    }
}