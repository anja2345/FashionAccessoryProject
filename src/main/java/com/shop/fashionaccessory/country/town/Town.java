package com.shop.fashionaccessory.country.town;

import com.shop.fashionaccessory.country.Country;
import com.shop.fashionaccessory.country.town.address.Address;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "town",catalog = "shop")
@NamedQueries(value = {
        @NamedQuery(name = "Town.findByCountry",query = "SELECT t FROM Town t WHERE t.country = :country"),
        @NamedQuery(name = "Town.findByName",query = "SELECT t FROM Town t WHERE t.name = :name")
})
public class Town implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    private String name;
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country country;

    @OneToMany(mappedBy = "town")
    private List<Address> addressList;

    public Town() {
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(id, town.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name+","+country;
    }
}

