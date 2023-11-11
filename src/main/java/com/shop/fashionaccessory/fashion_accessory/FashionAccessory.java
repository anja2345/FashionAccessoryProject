package com.shop.fashionaccessory.fashion_accessory;

import com.shop.fashionaccessory.order.order_item.OrderItem;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="fashion_accessories",catalog = "shop")
@NamedQueries({
        @NamedQuery(name = "FashionAccessory.findAll", query = "SELECT f FROM FashionAccessory f")
})
public class FashionAccessory implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String material;
    @Basic(optional = false)
    private double price;
    @Basic(optional = false)
    private int quantity;
    @OneToMany(mappedBy = "fashionAccessory")
    private List<OrderItem> orderItemList;

    public FashionAccessory() {
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FashionAccessory fashionAccessory = (FashionAccessory) o;
        return Objects.equals(id, fashionAccessory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }

}

