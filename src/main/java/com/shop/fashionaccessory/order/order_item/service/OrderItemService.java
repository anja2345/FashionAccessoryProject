package com.shop.fashionaccessory.order.order_item.service;

import com.shop.fashionaccessory.order.order_item.OrderItem;
import com.shop.fashionaccessory.service.BaseService;

public class OrderItemService extends BaseService<OrderItem> implements OrderItemServiceLocal {

    public OrderItemService() {
        super(OrderItem.class);
    }
}
