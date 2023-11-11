package com.shop.fashionaccessory.employee.service;

import com.shop.fashionaccessory.employee.Employee;
import javafx.collections.ObservableList;

import java.util.List;

public interface EmployeeServiceLocal {

    EmployeeServiceLocal SERVICE = new EmployeeService();

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    void remove(Integer id);

    Employee find(Integer id);

    List<Employee> findAll();

    Employee findbyUsername(String username);

    ObservableList<Employee> loadEmployee();

}
