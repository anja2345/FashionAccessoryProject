package com.shop.fashionaccessory.order.service;

import com.shop.fashionaccessory.order.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderServiceLocal {
    OrderServiceLocal SERVICE=new OrderService();

    void create(Order order);

    void edit(Order order);

    void remove(Order order);

    void remove(Integer id);

    Order find(Integer id);

    List<Order> findAll();
    List<Order> findByDate(LocalDate date);
}
