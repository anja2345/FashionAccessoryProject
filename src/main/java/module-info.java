module com.shop.fashionaccessory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires org.controlsfx.controls;
    requires java.sql;
    requires java.sql.rowset;


    opens com.shop.fashionaccessory to javafx.base,org.hibernate.orm.core,javafx.fxml;
    opens com.shop.fashionaccessory.employee to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.employee.privilege to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.employee.privilege.service to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.service to javafx.base,org.hibernate.orm.core,jakarta.persistence;
    opens com.shop.fashionaccessory.fashion_accessory to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.fashion_accessory.service to javafx.base, org.hibernate.orm.core;
    opens com.shop.fashionaccessory.order to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.order.order_item to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.country.town to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.country to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.country.town.address to javafx.base,org.hibernate.orm.core;
    opens com.shop.fashionaccessory.customer to javafx.base,org.hibernate.orm.core;



    exports com.shop.fashionaccessory;
    exports com.shop.fashionaccessory.gui_components;
    exports com.shop.fashionaccessory.gui_components.panel;
    exports com.shop.fashionaccessory.employee;



}