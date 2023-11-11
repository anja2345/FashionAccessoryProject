package com.shop.fashionaccessory.order.panel;

import com.shop.fashionaccessory.customer.Customer;
import com.shop.fashionaccessory.gui_components.Controller;
import com.shop.fashionaccessory.order.Order;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class CustomerOrderPanel extends BorderPane {
    private Customer currentCustomer;
    private Stage currentStage;
    private ListView<Order> orderListView = new ListView<>();
    private ObservableList<Order> orderObservableList;


    public CustomerOrderPanel(Customer customer,Stage stage){
        setCenter(orderListView);
        setOpaqueInsets(new Insets(10));
        setPadding(new Insets(10));

        currentCustomer=customer;
        currentStage=stage;
        orderObservableList = orderListView.getItems();
        List<Order> orderList=customer.getOrderList();
        orderObservableList.addAll(orderList);
        orderListView.setMaxHeight(300);

    }


}
