package com.shop.fashionaccessory.order.order_item;

import com.shop.fashionaccessory.FashionAccessoryApp;
import com.shop.fashionaccessory.fashion_accessory.FashionAccessory;
import com.shop.fashionaccessory.order.Order;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_item",catalog = "shop")
public class OrderItem implements Serializable {

    @Id
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Order order;

    @Id
    @JoinColumn(name = "id_fashion_accessories", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FashionAccessory fashionAccessory;
    @Basic(optional = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "unit_price")
    private double unitPrice;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FashionAccessory getFashionAccessory() {
        return fashionAccessory;
    }

    public void setFashionAccessory(FashionAccessory fashionAccessory) {
        this.fashionAccessory = fashionAccessory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(order, orderItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order);
    }

    @Override
    public String toString() {
        return "Proizvod: "+fashionAccessory.getName()+"-"+fashionAccessory.getMaterial()+",  cijena: "+unitPrice+",  Količina: "+quantity+", Ukupna cijena: "+quantity*unitPrice;
    }
}

