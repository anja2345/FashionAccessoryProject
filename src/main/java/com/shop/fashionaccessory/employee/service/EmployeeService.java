package com.shop.fashionaccessory.employee.service;

import com.shop.fashionaccessory.employee.Employee;
import com.shop.fashionaccessory.service.BaseService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

 class EmployeeService extends BaseService<Employee> implements EmployeeServiceLocal {

    public EmployeeService(){
        super(Employee.class);
    }

    public Employee findbyUsername(String username) {
        try {
            Query query =getEntityManager().createNamedQuery("Employee.findByUsername");
            query.setParameter("username", username);
            return (Employee) query.getSingleResult();
        }catch (NoResultException exception){
            throw  exception;
        }
    }

    @Override
    public ObservableList<Employee> loadEmployee() {
        List<Employee> employees=findAll();
        return FXCollections.observableList(employees);
    }
}
