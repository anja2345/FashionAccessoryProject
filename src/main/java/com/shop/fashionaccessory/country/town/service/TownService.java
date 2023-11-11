package com.shop.fashionaccessory.country.town.service;

import com.shop.fashionaccessory.country.Country;
import com.shop.fashionaccessory.country.town.Town;
import com.shop.fashionaccessory.service.BaseService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;

 class TownService extends BaseService<Town> implements TownServiceLocal{

    public TownService() {
        super(Town.class);
    }

    @Override
    public List<Town> findByCountry(Country country) {
        Query query= getEntityManager().createNamedQuery("Town.findByCountry");
        query.setParameter("country", country);
        return query.getResultList();
    }
    @Override
    public Town findByName(String name) {
        try {
            Query query =getEntityManager().createNamedQuery("Town.findByName");
            query.setParameter("name", name);
            return (Town) query.getSingleResult();
        }catch (NoResultException exception){
            throw  exception;
        }
    }
}
