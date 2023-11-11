package com.shop.fashionaccessory.customer.panel;

import com.shop.fashionaccessory.country.Country;
import com.shop.fashionaccessory.country.service.CountryServiceLocal;
import com.shop.fashionaccessory.country.town.Town;
import com.shop.fashionaccessory.country.town.address.Address;
import com.shop.fashionaccessory.country.town.address.service.AdressServiceLocal;
import com.shop.fashionaccessory.country.town.service.TownServiceLocal;
import com.shop.fashionaccessory.customer.Customer;
import com.shop.fashionaccessory.customer.service.CustomerServiceLocal;
import com.shop.fashionaccessory.gui_components.Controller;
import jakarta.persistence.NoResultException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.List;

public class AddCustomerPanel extends GridPane {
    private final TextField firstNameTextField = new TextField();
    private final Label firstNameLabel = new Label("Ime: ");

    private final TextField lastNameTextField = new TextField();
    private final Label lastNameLabel = new Label("Prezime: ");
    private final Label mobileLabel = new Label("Mobile: ");
    private final TextField mobileTextField = new TextField();
    private final Label emailLabel = new Label("Email: ");
    private final TextField emailTextField = new TextField();
    private final ComboBox<Country> countryComboBox=new ComboBox<>();
    private final Label countryLabel=new Label("Država: ");
    private final ComboBox<Town> townComboBox=new ComboBox<>();
    private final Label townLabel=new Label("Grad: ");
    private final TextField addressTextField = new TextField();
    private final Label addreslabel = new Label("Adresa: ");
    private final Button saveCustomerButton = new Button("SAVE");
    public AddCustomerPanel() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        setComponents();
        setUpComboBox();
        addComponents();
    }

    private void setComponents() {
        firstNameTextField.setMaxWidth(200);
        firstNameTextField.setPromptText("Enter name...");
        lastNameTextField.setMaxWidth(200);
        lastNameTextField.setPromptText("Enter surname...");
        countryComboBox.setMaxWidth(200);
        townComboBox.setMaxWidth(200);
        addressTextField.setMaxWidth(200);
        addressTextField.setPromptText("Enter address...");
        mobileTextField.setMaxWidth(200);
        mobileTextField.setPromptText("Enter mobile...");
        emailTextField.setMaxWidth(200);
        emailTextField.setPromptText("Enter email...");
        saveCustomerButton.setMaxWidth(50);
        saveCustomerButton.setOnAction(this::onClickSaveCustomerButton);
    }

    private void addComponents() {
        add(firstNameLabel, 0, 0);
        add(firstNameTextField, 1, 0);
        add(lastNameLabel, 0, 1);
        add(lastNameTextField, 1, 1);
        add(countryLabel, 0, 3);
        add(countryComboBox, 1, 3);
        add(townLabel, 0, 4);
        add(townComboBox, 1, 4);
        add(addreslabel, 0, 5);
        add(addressTextField, 1, 5);
        add(mobileLabel, 0, 6);
        add(mobileTextField, 1, 6);
        add(emailLabel, 0, 7);
        add(emailTextField, 1, 7);
        add(saveCustomerButton, 0, 9);
    }

    private void onClickSaveCustomerButton(ActionEvent actionEvent) {
        if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                mobileTextField.getText().isEmpty() || addressTextField.getText().isEmpty() ||
                countryComboBox.getValue()==null || townComboBox.getValue()==null) {
            Controller.instance().showDialog("Niste popunili sva polja!");
        }else{
            Customer customer = new Customer();
            customer.setFirstName(firstNameTextField.getText());
            customer.setLastName(lastNameTextField.getText());
            customer.setMobile(mobileTextField.getText());
            customer.setEmail(emailTextField.getText());

            try {
                Address addressInBase= AdressServiceLocal.SERVICE.findByName(addressTextField.getText());
                if(addressInBase.getTown()!=townComboBox.getValue()){
                    throw new NoResultException();
                }
                addressInBase.setTown(townComboBox.getValue());
                customer.setAddress(addressInBase);
            }catch (NoResultException exception){
                Address address=new Address();
                address.setTown(townComboBox.getValue());
                address.setName(addressTextField.getText());
                AdressServiceLocal.SERVICE.create(address);
                customer.setAddress(address);
            }
            CustomerServiceLocal.SERVICE.create(customer);
            Controller.instance().getAddCustomerStage().close();
            Controller.instance().getCustomersPanel().getCustomerObservableList().add(customer);
        }
        clearTextField();
    }

    private void clearTextField() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailTextField.clear();
        mobileTextField.clear();
        addressTextField.clear();
    }

    private void setUpComboBox() {
        ObservableList<Country> countryObservableList = countryComboBox.getItems();
        ObservableList<Town> townObservableList=townComboBox.getItems();

        List<Country> countryList = CountryServiceLocal.SERVICE.findAll();
        countryObservableList.addAll(countryList);
        countryComboBox.setValue(countryList.get(0));
        countryComboBox.setOnAction(this::onClickCountryComboBox);

        List<Town> townList= TownServiceLocal.SERVICE.findByCountry(countryComboBox.getValue());
        townObservableList.addAll(townList);


    }

    private void onClickCountryComboBox(ActionEvent actionEvent) {
        townComboBox.getItems().removeAll(townComboBox.getItems());
        ObservableList<Town> townObservableList=townComboBox.getItems();
        List<Town> townList= TownServiceLocal.SERVICE.findByCountry(countryComboBox.getValue());
        townObservableList.addAll(townList);
    }
}
