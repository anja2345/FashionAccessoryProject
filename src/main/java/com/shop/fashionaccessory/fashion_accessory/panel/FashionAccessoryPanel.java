package com.shop.fashionaccessory.fashion_accessory.panel;

import com.shop.fashionaccessory.fashion_accessory.FashionAccessory;
import com.shop.fashionaccessory.fashion_accessory.service.FashionAccessoryServiceLocal;
import com.shop.fashionaccessory.gui_components.Controller;
import com.shop.fashionaccessory.gui_components.panel.FashionAccessoryShopPanel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.Consumer;

public class FashionAccessoryPanel extends VBox {
    private Label currentEmployeeLabel = new Label();
    private final Button backButton = new Button("Back");
    private TableView<FashionAccessory> fashionAccessoryTableView = new TableView<>();
    private ObservableList<FashionAccessory> fashionAccessoryObservableList;
    private TextField searchTextField = new TextField();
    private Button searchButton = new Button("Search");
    private final Button addProductButton = new Button("Add Product");
    private final Button deleteProductButton = new Button("Delete Product");
    private final CheckBox deleteCheckBox = new CheckBox("Delete");
    private final RadioButton nameRadioButton=new RadioButton("Name search");

    private final RadioButton materialRadioButton=new RadioButton("Material search");
    private final TextField quantityTextField=new TextField();
    private final Button getItemButton=new Button("Get Item");

    public  FashionAccessoryPanel(){
        setSpacing(10);
        setPadding(new Insets(10));

        BorderPane backAndEmployeePanel = getBackAndEmployeePanel();
        setupTableView();
        HBox radioHBox=getRadioPanel();
        HBox searchHBox=getSearchPanel();
        HBox getItemHBox=getItemPanel();
        HBox buttonPanel=getButtonPanel();

        getChildren().addAll(backAndEmployeePanel,fashionAccessoryTableView,radioHBox,searchHBox,getItemHBox,buttonPanel);
    }

    private HBox getButtonPanel() {
        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(addProductButton, deleteProductButton, deleteCheckBox);
        addProductButton.setOnAction(this::onClickAddCustomerButton);
        deleteProductButton.setOnAction(this::onClickDeleteCustomerButton);
        deleteProductButton.setDisable(true);
        deleteCheckBox.setOnAction(this::onClickDeleteCheckBox);
        return buttonHBox;
    }

    private void onClickAddCustomerButton(ActionEvent actionEvent) {
        Controller.instance().setFashionAccessoryPanel(this);
        Scene scene = new Scene(new AddFashionAccessoryPanel());
        Stage stage = Controller.instance().getAddProductStage();
        stage.setScene(scene);
        stage.setTitle("Add product");
        stage.show();
    }

    private void onClickDeleteCustomerButton(ActionEvent actionEvent) {
        int numberSelectedProducts=0;
        for(int i=0;i<fashionAccessoryObservableList.size();i++){
            if(fashionAccessoryTableView.getSelectionModel().isSelected(i)){
                numberSelectedProducts++;
            }
        }
        if (numberSelectedProducts!=1) {
            Controller.instance().showDialog("Selektujte proizvod kojeg želite izbrisati");
        } else {
            FashionAccessory selectedProduct = fashionAccessoryTableView.getSelectionModel().getSelectedItem();
            fashionAccessoryObservableList.remove(selectedProduct);
            FashionAccessoryServiceLocal.SERVICE.remove(selectedProduct.getId());
        }
    }

    private void onClickDeleteCheckBox(ActionEvent actionEvent) {
        if (deleteCheckBox.isSelected()) {
            deleteProductButton.setDisable(false);
        } else {
            deleteProductButton.setDisable(true);
        }
    }

    private HBox getItemPanel() {
        quantityTextField.setPromptText("Number of items..");
        getItemButton.setOnAction(this::onClickGetButton);
        getItemButton.setMinWidth(70);
        HBox getItemHBox=new HBox(20);
        getItemHBox.getChildren().addAll(quantityTextField,getItemButton);
        return getItemHBox;
    }

    private void onClickGetButton(ActionEvent actionEvent) {
        ObservableList<FashionAccessory> selectedProducts = fashionAccessoryTableView.getSelectionModel().getSelectedItems();
        int numberItems=0;
        for (int i=0;i<selectedProducts.size();i++){
            numberItems+=selectedProducts.get(i).getQuantity();
        }
        quantityTextField.setText(String.valueOf(numberItems));
    }

    private HBox getSearchPanel() {
        searchTextField.setPromptText("Search name..");
        searchButton.setOnAction(this::onClickSearchButton);
        searchButton.setMinWidth(70);
        HBox searchHBox = new HBox(20);
        searchHBox.getChildren().addAll(searchTextField, searchButton);
        return searchHBox;
    }

    private void onClickSearchButton(ActionEvent actionEvent) {
        ObservableList<FashionAccessory> list = fashionAccessoryTableView.getItems();
        for (int i = 0; i < list.size(); i++) {
            fashionAccessoryTableView.getSelectionModel().clearSelection();
        }
        for (int i = 0; i < list.size(); i++) {
            if (nameRadioButton.isSelected()) {
                if (list.get(i).getName().equalsIgnoreCase(searchTextField.getText())) {
                    fashionAccessoryTableView.getSelectionModel().select(i);
                }
            }else if(materialRadioButton.isSelected()){
                if (list.get(i).getMaterial().equalsIgnoreCase(searchTextField.getText())) {
                    fashionAccessoryTableView.getSelectionModel().select(i);
                }
            }
        }
        searchTextField.clear();
    }

    private HBox getRadioPanel() {
        nameRadioButton.setSelected(true);
        nameRadioButton.setOnAction(this::onClickSearchRadioButton);
        materialRadioButton.setOnAction(this::onClickSearchRadioButton);
        ToggleGroup searchGroup = new ToggleGroup();
        nameRadioButton.setToggleGroup(searchGroup);
        materialRadioButton.setToggleGroup(searchGroup);
        HBox searchRadioBox = new HBox(10);
        searchRadioBox.getChildren().addAll(nameRadioButton, materialRadioButton);
        return searchRadioBox;
    }

    private void onClickSearchRadioButton(ActionEvent actionEvent) {
        if (actionEvent.getSource() == nameRadioButton) {
            searchTextField.setPromptText("Search name..");
        } else if (actionEvent.getSource() == materialRadioButton) {
            searchTextField.setPromptText("Search material..");
        }
    }

    private void setupTableView() {
        fashionAccessoryObservableList = FashionAccessoryServiceLocal.SERVICE.loadFashionAccessory();
        fashionAccessoryTableView.setItems(fashionAccessoryObservableList);
        fashionAccessoryTableView.setEditable(true);
        MultipleSelectionModel<FashionAccessory> selectionModel=fashionAccessoryTableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<FashionAccessory, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setName(event.getNewValue())));

        TableColumn<FashionAccessory, String> materialColumn = new TableColumn<>("Materijal");
        materialColumn.setMinWidth(200);
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        materialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        materialColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setMaterial(event.getNewValue())));

        TableColumn<FashionAccessory, Double> priceColumn = new TableColumn<>("Jedinična cijena");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setCellFactory(TextFieldTableCell.<FashionAccessory, Double>forTableColumn(new DoubleStringConverter()));
        priceColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setPrice(event.getNewValue())));

        TableColumn<FashionAccessory, Integer> quantityColumn = new TableColumn<>("Količina");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setCellFactory(TextFieldTableCell.<FashionAccessory, Integer>forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setQuantity(event.getNewValue())));



        fashionAccessoryTableView.getColumns().addAll(nameColumn, materialColumn, priceColumn, quantityColumn);
    }

    private <E> void onFiledChange(TableColumn.CellEditEvent<FashionAccessory, E> event, Consumer<FashionAccessory> fashionAccessoryConsumer) {
        FashionAccessory editFashionAccessory = event.getRowValue();
        fashionAccessoryConsumer.accept(editFashionAccessory);
        FashionAccessoryServiceLocal.SERVICE.edit(editFashionAccessory);
    }

    private BorderPane getBackAndEmployeePanel() {
        currentEmployeeLabel = Controller.getCurrentEmployeeLabel();
        backButton.setOnAction(this::onClickLogoutButton);
        return new BorderPane(null, null, currentEmployeeLabel, null, backButton);
    }

    private void onClickLogoutButton(ActionEvent actionEvent) {
        Scene scene = new Scene(new FashionAccessoryShopPanel());
        Controller.instance().getMainStage().setScene(scene);
        Controller.instance().getMainStage().setTitle("Shop");
    }

    public ObservableList<FashionAccessory> getFashionAccessoryObservableList() {
        return fashionAccessoryObservableList;
    }}
