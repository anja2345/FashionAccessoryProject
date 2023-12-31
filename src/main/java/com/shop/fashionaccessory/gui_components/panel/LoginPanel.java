package com.shop.fashionaccessory.gui_components.panel;

import com.shop.fashionaccessory.employee.Employee;
import com.shop.fashionaccessory.employee.service.EmployeeServiceLocal;
import com.shop.fashionaccessory.gui_components.Controller;
import com.shop.fashionaccessory.password.PasswordValidator;
import jakarta.persistence.NoResultException;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class LoginPanel extends GridPane {
    private final Label usernameLabel = new Label("Korisničko ime:");
    private final Label passwordLabel = new Label("Lozinka:");
    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Prijava");
    private final Button cancelButton = new Button("Odustani");
    private final Label messageLabel = new Label();


    public LoginPanel(){
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        setAlignment(Pos.CENTER);

        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER_RIGHT);
        loginButton.setOnAction(this::onLoginButtonClick);
        cancelButton.setOnAction(this::onCancelButtonClick);
        flowPane.setHgap(5);
        flowPane.getChildren().addAll(loginButton, cancelButton);
        add(flowPane, 1, 2);
        add(messageLabel, 1, 3);
}
    private void onCancelButtonClick(ActionEvent event) {
        usernameTextField.clear();
        passwordField.clear();
        messageLabel.setText("");
    }


    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            messageLabel.setText("Nije dozvoljen prazan unos korisničkog imena ili lozinke");
            return;
        }
        login(username, password);
    }
    private void login(String username, String password) {
        try {
            Employee employee = EmployeeServiceLocal.SERVICE.findbyUsername(username);
            PasswordValidator passwordValidator = new PasswordValidator();
            String employeeStoredPassword = employee.getPassword();
            if (employee != null && passwordValidator.validatePassword(password, employeeStoredPassword)) {
                Controller.setCurrentEmployee(employee);
                Scene scene = new Scene(new FashionAccessoryShopPanel());
                Controller.instance().getMainStage().setScene(scene);
                Controller.instance().getMainStage().setTitle("Shop");
            } else {
                messageLabel.setText("Neispravna lozinka.");
            }
        } catch (NoResultException e) {
            messageLabel.setText("Nesipravno korisničko ime.");
            System.err.println(e.getMessage());
        }
    }

    }








