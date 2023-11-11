package com.shop.fashionaccessory;

import com.shop.fashionaccessory.gui_components.Controller;
import com.shop.fashionaccessory.gui_components.panel.LoginPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FashionAccessoryApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Controller.instance().setMainStage(stage);
        LoginPanel loginPanel=new LoginPanel();
        Scene scene = new Scene(loginPanel);
        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    }