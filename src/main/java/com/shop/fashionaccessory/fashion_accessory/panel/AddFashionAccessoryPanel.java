package com.shop.fashionaccessory.fashion_accessory.panel;

import com.shop.fashionaccessory.fashion_accessory.FashionAccessory;
import com.shop.fashionaccessory.fashion_accessory.service.FashionAccessoryServiceLocal;
import com.shop.fashionaccessory.gui_components.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.Consumer;

public class AddFashionAccessoryPanel extends GridPane {
    private final TextField nameTextField = new TextField();
    private final Label nameLabel = new Label("Ime: ");
    private final TextField materialTextField = new TextField();

    private final Label materialLabel = new Label("Materijal: ");
    private final TextField quantityTextField = new TextField();
    private final Label quantityLabel = new Label("Količina: ");
    private final TextField priceTextField = new TextField();
    private final Label priceLabel = new Label("Jedinična cijena: ");
    private final Button addProductButton = new Button("SAVE");

    public AddFashionAccessoryPanel() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20));

        setComponents();
        addComponents();
    }

    private void addComponents() {
        add(nameLabel, 0, 0);
        add(nameTextField, 1, 0);
        add(materialLabel, 0, 1);
        add(materialTextField, 1, 1);
        add(priceLabel, 0, 2);
        add(priceTextField, 1, 2);
        add(quantityLabel, 0, 3);
        add(quantityTextField, 1, 3);
        add(addProductButton, 0, 4);
    }

    private void setComponents() {
        nameTextField.setMaxWidth(200);
        nameTextField.setPromptText("Enter name...");
        materialTextField.setMaxWidth(200);
        materialTextField.setPromptText("Enter material...");
        priceTextField.setMaxWidth(200);
        priceTextField.setPromptText("Enter price...");
        quantityTextField.setMaxWidth(200);
        quantityTextField.setPromptText("Enter quantity...");
        addProductButton.setOnAction(this::onClickAddFashionAccessoryButton);
    }

    private void onClickAddFashionAccessoryButton(ActionEvent actionEvent) {
        if (nameTextField.getText().isEmpty() || materialTextField.getText().isEmpty() ||
                priceTextField.getText().isEmpty() || quantityTextField.getText().isEmpty()) {
            Controller.instance().showDialog("Niste popunili sva polja!");
        } else {
            FashionAccessory fashionAccessory = new FashionAccessory();
            fashionAccessory.setName(nameTextField.getText());
            fashionAccessory.setMaterial(materialTextField.getText());
            fashionAccessory.setPrice(Double.parseDouble(priceTextField.getText()));
            fashionAccessory.setQuantity(Integer.parseInt(quantityTextField.getText()));
            FashionAccessoryServiceLocal.SERVICE.create(fashionAccessory);
            Controller.instance().getAddProductStage().close();
            Controller.instance().getFashionAccessoryPanel().getFashionAccessoryObservableList().add(fashionAccessory);
        }
        clearTextField();
    }

    private void clearTextField() {
        nameTextField.clear();
        materialTextField.clear();
        priceTextField.clear();
        quantityTextField.clear();
    }












}

























