package com.shop.fashionaccessory.customer.panel;

import com.shop.fashionaccessory.country.town.address.Address;
import com.shop.fashionaccessory.customer.Customer;
import com.shop.fashionaccessory.customer.service.CustomerServiceLocal;
import com.shop.fashionaccessory.gui_components.Controller;
import com.shop.fashionaccessory.gui_components.panel.FashionAccessoryShopPanel;
import com.shop.fashionaccessory.order.panel.CustomerOrderPanel;
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

import java.util.function.Consumer;

        public class CustomerPanel extends VBox {

        private Label loggedEmployeeLabel = new Label();
        private final Button backButton = new Button("Back");
        private TableView<Customer> customerTableView = new TableView<>();
        private ObservableList<Customer> customerObservableList;
            private TextField searchTextField = new TextField();
            private Button searchButton = new Button("SEARCH");
        private final Button addCustomerButton = new Button("Add Customer");
        private final Button deleteCustomerButton = new Button("Delete Customer");
        private final CheckBox deleteCheckBox = new CheckBox("Delete");
        private final Button ordersButton=new Button("Orders");

        private final Button orderItemButton= new Button("Order Item");


        public CustomerPanel(){
            setSpacing(10);
            setPadding(new Insets(10));

            BorderPane backAndEmployeePanel = getBackAndEmployeePanel();
            setupTableView();
            HBox buttonPanel = getButtonPanel();
            BorderPane searchPanel = getSearchPanel();


            getChildren().addAll(backAndEmployeePanel,searchPanel,customerTableView,buttonPanel);
        }

        private HBox getButtonPanel() {
            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(addCustomerButton, deleteCustomerButton, deleteCheckBox);
            addCustomerButton.setOnAction(this::onClickAddCustomerButton);
            deleteCustomerButton.setOnAction(this::onClickDeleteCustomerButton);
            deleteCustomerButton.setDisable(true);
            deleteCheckBox.setOnAction(this::onClickDeleteCheckBox);
            return hBox;
        }

        private void onClickAddCustomerButton(ActionEvent actionEvent) {
            Controller.instance().setCustomerPanel(this);
            Scene scene = new Scene(new AddCustomerPanel());
            Stage stage = Controller.instance().getAddCustomerStage();
            stage.setScene(scene);
            stage.setTitle("Add customer");
            stage.show();
        }

        private void onClickDeleteCustomerButton(ActionEvent actionEvent) {
            int numberSelectedCustomer=0;
            for(int i=0;i<customerObservableList.size();i++){
                if(customerTableView.getSelectionModel().isSelected(i)){
                    numberSelectedCustomer++;
                }
            }
            if (numberSelectedCustomer!=1) {
                Controller.instance().showDialog("Selektujte kupca kojeg želite izbrisati");
            } else {
                Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
                customerObservableList.remove(selectedCustomer);
                CustomerServiceLocal.SERVICE.remove(selectedCustomer.getId());
            }
        }

        private void onClickDeleteCheckBox(ActionEvent actionEvent) {
            if (deleteCheckBox.isSelected()) {
                deleteCustomerButton.setDisable(false);
            } else {
                deleteCustomerButton.setDisable(true);
            }
        }

        private BorderPane getSearchPanel() {
            HBox searchPanel = new HBox(10);
            searchPanel.getChildren().addAll(searchTextField, searchButton);
            searchTextField.setPromptText("Search name or surname...");
            searchButton.setOnAction(this::onClickSearchButton);
            ordersButton.setOnAction(this::onClickOrdersButton);


            HBox orderPanel=new HBox(10);
            orderPanel.getChildren().addAll(ordersButton,orderItemButton);
            BorderPane buttonBorderPane = new BorderPane(null, null, orderPanel, null, searchPanel);
            return buttonBorderPane;
        }


        private void onClickOrdersButton(ActionEvent actionEvent) {
            int numberSelectedCustomer=0;
            for(int i=0;i<customerObservableList.size();i++){
                if(customerTableView.getSelectionModel().isSelected(i)){
                    numberSelectedCustomer++;
                }
            }
            if (numberSelectedCustomer!=1) {
                Controller.instance().showDialog("Selektujte kupca da bi vidjeli njegove narudžbe");
            } else {
                Controller.instance().setCustomerPanel(this);
                Stage stage=new Stage();
                Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
                CustomerOrderPanel customerOrderPanel=new CustomerOrderPanel(selectedCustomer,stage);
                Scene scene=new Scene(customerOrderPanel,500,400);
                stage.setScene(scene);
                stage.setTitle("Narudžbe kupca");
                stage.show();
            }
        }

        private void onClickSearchButton(ActionEvent actionEvent) {
            if (searchTextField.getText().isEmpty()) {
                Controller.instance().showDialog("Unesite ime ili prezime zaposlenika kojeg želite");
            } else {
                for (int i = 0; i < customerObservableList.size(); i++) {
                    customerTableView.getSelectionModel().clearSelection();
                }
                for (int i = 0; i < customerObservableList.size(); i++) {
                    if (customerObservableList.get(i).getFirstName().equalsIgnoreCase(searchTextField.getText()) || customerObservableList.get(i).getFirstName().equalsIgnoreCase(searchTextField.getText())) {
                        customerTableView.getSelectionModel().select(i);
                    }
                }
            }
            searchTextField.clear();
        }

        private void setupTableView() {
            customerObservableList = CustomerServiceLocal.SERVICE.loadCustomers();
            customerTableView.setItems(customerObservableList);
            customerTableView.setEditable(true);
            MultipleSelectionModel<Customer> selectionModel=customerTableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

            TableColumn<Customer, Integer> idColumn = new TableColumn<>("Id");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Customer, String> firstNameColumnn = new TableColumn<>("Ime");
            firstNameColumnn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            firstNameColumnn.setMinWidth(100);
            firstNameColumnn.setCellFactory(TextFieldTableCell.forTableColumn());
            firstNameColumnn.setOnEditCommit(event -> onFiledChange(event, r -> r.setFirstName(event.getNewValue())));

            TableColumn<Customer, String> lastNameColumn = new TableColumn<>("Prezime");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            lastNameColumn.setMinWidth(100);
            lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            lastNameColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setLastName(event.getNewValue())));


            TableColumn<Customer, Address> addressColumn = new TableColumn<>("Adresa");
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            addressColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setAddress(event.getNewValue())));

            TableColumn<Customer, String> mobileColumn = new TableColumn<>("Mobitel");
            mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
            mobileColumn.setMinWidth(100);
            mobileColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            mobileColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setMobile(event.getNewValue())));

            TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            emailColumn.setMinWidth(150);
            emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            emailColumn.setOnEditCommit(event -> onFiledChange(event, r -> r.setEmail(event.getNewValue())));


            customerTableView.getColumns().addAll(idColumn, firstNameColumnn, lastNameColumn, addressColumn, mobileColumn, emailColumn);
        }


        private <E> void onFiledChange(TableColumn.CellEditEvent<Customer, E> event, Consumer<Customer> customerConsumer) {
            Customer editCustomer = event.getRowValue();
            customerConsumer.accept(editCustomer);
            CustomerServiceLocal.SERVICE.edit(editCustomer);
        }

        private BorderPane getBackAndEmployeePanel() {
            loggedEmployeeLabel = Controller.getCurrentEmployeeLabel();
            backButton.setOnAction(this::onClickLogoutButton);
            return new BorderPane(null, null, loggedEmployeeLabel, null, backButton);
        }

        private void onClickLogoutButton(ActionEvent actionEvent) {
            Scene scene = new Scene(new FashionAccessoryShopPanel());
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("Shop");
        }

        public ObservableList<Customer> getCustomerObservableList() {
            return customerObservableList;
        }

        public void refresh(){
            customerTableView.refresh();
        }

    }


