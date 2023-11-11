package com.shop.fashionaccessory.fashion_accessory.service;

import com.shop.fashionaccessory.fashion_accessory.FashionAccessory;
import com.shop.fashionaccessory.service.BaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

class FashionAccessoryService extends BaseService<FashionAccessory> implements FashionAccessoryServiceLocal {

    public FashionAccessoryService() {
        super(FashionAccessory.class);
    }


    @Override
    public ObservableList<FashionAccessory> loadFashionAccessory() {
        List<FashionAccessory> fashionAccessory=findAll();
        return FXCollections.observableList(fashionAccessory);
    }
}
