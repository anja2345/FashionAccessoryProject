package com.shop.fashionaccessory.country.town.address;

import com.shop.fashionaccessory.country.town.Town;
import com.shop.fashionaccessory.customer.Customer;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address",catalog = "shop")
@NamedQueries(value = {
        @NamedQuery(name = "Address.findByName",query = "SELECT a FROM Address a WHERE a.name = :name")
})
public class Address implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    private String name;
    @JoinColumn(name = "id_town", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Town town;
    @OneToMany(mappedBy = "address")
    private List<Customer> customerList;

    public Address() {
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name+","+town;
    }
}
