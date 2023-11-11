package com.shop.fashionaccessory.gui_components.panel;

import com.shop.fashionaccessory.customer.panel.CustomerPanel;
import com.shop.fashionaccessory.employee.panel.EmployeePanel;
import com.shop.fashionaccessory.fashion_accessory.panel.FashionAccessoryPanel;
import com.shop.fashionaccessory.gui_components.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FashionAccessoryShopPanel extends VBox {

        private Hyperlink currentEmployeeLink = new Hyperlink();
        private final Label shopLabel = new Label("Fashion Accessories Shop");
        private final Button fashionAccessoryButton = new Button("Modni dodaci");
        private final Button customerButton = new Button("Kupci");
        private final Button employeeButton = new Button("Zaposleni");


        private final Button logoutButton = new Button("Odjava");

         public FashionAccessoryShopPanel() {
            setSpacing(10);
            setPadding(new Insets(10));

          BorderPane logoutAndEmployeePanel = getLogoutAndEmployeePanel();
            BorderPane fashionAccessoryShopPanel = getFashionAccessoryShopPanel();
            VBox buttonPanel= getButtonPanel();
            buttonSetOnAction();

            getChildren().addAll (logoutAndEmployeePanel,fashionAccessoryShopPanel,buttonPanel);
        }

        private void buttonSetOnAction() {
            fashionAccessoryButton.setOnAction(this::onClickFashionAccesoryButton);
            employeeButton.setOnAction(this::onClickEmployeeButton);
            customerButton.setOnAction(this::onClickCustomerButton);

        }

        private void onClickCustomerButton(ActionEvent actionEvent) {
            Scene scene = new Scene(new CustomerPanel());
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("Customers");
        }

        private void onClickEmployeeButton(ActionEvent actionEvent) {
            Scene scene = new Scene(new EmployeePanel());
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("Employees");
        }

        private void onClickFashionAccesoryButton(ActionEvent actionEvent) {
            Scene scene = new Scene(new FashionAccessoryPanel());
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("FashionAccessory");
        }

       private BorderPane getLogoutAndEmployeePanel() {
            currentEmployeeLink.setText(Controller.getCurrentEmployeeLabel().getText());
            currentEmployeeLink.setOnAction(this::onClickCurrentEmployeeLink);
            logoutButton.setOnAction(this::onClickLogoutButton);
            return new BorderPane(null, null, currentEmployeeLink, null, logoutButton);
        }

        private void onClickCurrentEmployeeLink(ActionEvent actionEvent) {
            Stage stage = new Stage();
            //Scene scene = new Scene(new EditCurrentEmployeePanel(stage));
            //stage.setScene(scene);
            stage.setTitle("My data");
            stage.show();
        }

        /*private FlowPane getButtonAndDatePanel() {
            dateLabel.setText("Datum: ");
            VBox dateAndSaldoPanel = new VBox(dateLabel, datePicker, saldoHyperlink);
            saldo = getSaldo();
            saldoHyperlink.setText(saldo + " KM");
            saldoHyperlink.setOnAction(this::onClickSaldoHyperLink);
            datePicker.setOnAction(this::onClickDatePicker);
            datePicker.setMaxWidth(120);
            dateAndSaldoPanel.setSpacing(10);

            VBox buttonPanel = getButtonPanel();
            FlowPane buttonAndDatePanel = new FlowPane(buttonPanel, dateAndSaldoPanel);
            buttonAndDatePanel.setAlignment(Pos.CENTER);
            buttonAndDatePanel.setPadding(new Insets(5));
            buttonAndDatePanel.setHgap(30);
            return buttonAndDatePanel;
        }

        private void onClickSaldoHyperLink(ActionEvent actionEvent) {
            Stage stage = new Stage();
            LocalDate date = datePicker.getValue();
            DateOrderPanel dateOrderPanel = new DateOrderPanel(date, stage);
            Scene scene = new Scene(dateOrderPanel, 500, 400);
            stage.setScene(scene);
            stage.setTitle("Narudžbe ");
            stage.show();

        }

        private Double getSaldo() {
            Double saldo = 0.;
            List<Order> orderList = OrderServiceLocal.SERVICE.findByDate(datePicker.getValue());
            for (int i = 0; i < orderList.size(); i++) {
                if(orderList.get(i).getOrderStatus().getId()==2){
                    continue;
                }
                List<OrderItem> orderItemList = orderList.get(i).getOrderItemList();
                for (int j = 0; j < orderItemList.size(); j++) {
                    OrderItem orderItem = orderItemList.get(j);
                    saldo += orderItem.getUnitPrice() * orderItem.getQuantity();
                }
            }
            return saldo;
        }


        private void onClickDatePicker(ActionEvent actionEvent) {
            System.out.println("usaoooo");
            saldo = getSaldo();
            saldoHyperlink.setText(saldo + " KM");
        }*/

        private VBox getButtonPanel() {
            if (Controller.getCurrentEmployee().getPrivilege().getName().equals("user")) {
                employeeButton.setDisable(true);
            }
            fashionAccessoryButton.setMinWidth(120);
            customerButton.setMinWidth(120);
            employeeButton.setMinWidth(120);
            VBox buttonPanel = new VBox(fashionAccessoryButton, customerButton, employeeButton);
            buttonPanel.setSpacing(10);
            return buttonPanel;
        }

        private BorderPane getFashionAccessoryShopPanel() {
            BorderPane fashionAccessoryShopPanel = new BorderPane(shopLabel);
            fashionAccessoryShopPanel.setPadding(new Insets(20));
            shopLabel.setFont(new Font("Arial", 35));
            return fashionAccessoryShopPanel;
        }


        private void onClickLogoutButton(ActionEvent actionEvent) {
            LoginPanel loginPanel = new LoginPanel();
            Scene scene = new Scene(loginPanel);
            Controller.instance().getMainStage().setScene(scene);
            Controller.instance().getMainStage().setTitle("Login");
            Controller.setCurrentEmployee(null);
        }

    }

