package com.shop.fashionaccessory.order.service;

import com.shop.fashionaccessory.order.Order;
import com.shop.fashionaccessory.service.BaseService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

 class OrderService extends BaseService<Order> implements OrderServiceLocal{
    public OrderService() {
        super(Order.class);
    }

    @Override
    public List<Order> findByDate(LocalDate date) {
        try {
            Query query =getEntityManager().createNamedQuery("Order.findByDate");
            query.setParameter("date", date);
            return query.getResultList();
        }catch (NoResultException exception){
            throw  exception;
        }
    }
}
