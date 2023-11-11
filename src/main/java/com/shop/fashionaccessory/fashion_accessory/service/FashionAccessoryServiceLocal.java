package com.shop.fashionaccessory.fashion_accessory.service;

import com.shop.fashionaccessory.fashion_accessory.FashionAccessory;
import javafx.collections.ObservableList;

import java.util.List;

public interface FashionAccessoryServiceLocal {

    FashionAccessoryServiceLocal SERVICE=new FashionAccessoryService();

    void create(FashionAccessory fashionAccessory);

    void edit(FashionAccessory fashionAccessory);

    void remove(FashionAccessory fashionAccessory);
    void remove(Integer id);

    FashionAccessory find(Integer id);

    List<FashionAccessory> findAll();
    ObservableList<FashionAccessory> loadFashionAccessory();
}
