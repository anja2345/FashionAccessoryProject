package com.shop.fashionaccessory.gui_components;

import com.shop.fashionaccessory.customer.panel.CustomerPanel;
import com.shop.fashionaccessory.employee.Employee;
import com.shop.fashionaccessory.employee.panel.EmployeePanel;
import com.shop.fashionaccessory.fashion_accessory.panel.FashionAccessoryPanel;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {

    public static String PU_NAME = "shopPU";
    private static Controller INSTANCE=null;
    private Stage mainStage;
    private Stage addEmployeeStage=new Stage();
    private Stage addCustomerStage=new Stage();
    private Stage addProductStage=new Stage();
    private static Employee currentEmployee;

    private EmployeePanel employeePanel;
    private CustomerPanel customersPanel;
    private FashionAccessoryPanel fashionAccessoryPanel;

    private Controller(){
    }

    public void showDialog(String greška){
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Greška");
        dialog.setContentText(greška);
        dialog.show();
        dialog.setHeight(150);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
    }

    public Stage getAddProductStage() {
        return addProductStage;
    }

    public void setAddProductStage(Stage addProductStage) {
        this.addProductStage = addProductStage;
    }

   public FashionAccessoryPanel getFashionAccessoryPanel() {
        return fashionAccessoryPanel;
    }

    public void setFashionAccessoryPanel(FashionAccessoryPanel fashionAccessoryPanel) {
        this.fashionAccessoryPanel = fashionAccessoryPanel;
    }

    public CustomerPanel getCustomersPanel() {
        return customersPanel;
    }

    public void setCustomerPanel(CustomerPanel customersPanel) {
        this.customersPanel = customersPanel;
    }

    public Stage getAddCustomerStage() {
        return addCustomerStage;
    }

    public void setAddCustomerStage(Stage addCustomerStage) {
        this.addCustomerStage = addCustomerStage;
    }

    public EmployeePanel getEmployeePanel() {
        return employeePanel;
    }

    public void setEmployeePanel(EmployeePanel employeePanel) {
        this.employeePanel = employeePanel;
    }

    public Stage getAddEmployeeStage() {
        return addEmployeeStage;
    }

    public void setAddEmployeeStage(Stage addEmployeeStage) {
        this.addEmployeeStage = addEmployeeStage;
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }
    public static Label getCurrentEmployeeLabel(){
        Label currentEmployeeLabel=new Label();
        currentEmployeeLabel.setText(Controller.getCurrentEmployee().getName() + ", " + Controller.getCurrentEmployee().getSurname());
        return currentEmployeeLabel;
    }

    public static void setCurrentEmployee(Employee currentEmployee) {
        Controller.currentEmployee = currentEmployee;
    }

    public Stage getMainStage() {
        mainStage.centerOnScreen();
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public static Controller instance(){
        if(INSTANCE==null){
            INSTANCE=new Controller();
        }
        return INSTANCE;
    }

}
