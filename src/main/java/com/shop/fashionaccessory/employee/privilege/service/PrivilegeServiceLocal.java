package com.shop.fashionaccessory.employee.privilege.service;

import com.shop.fashionaccessory.employee.privilege.Privilege;

import java.util.List;

public interface PrivilegeServiceLocal {

    PrivilegeServiceLocal SERVICE=new PrivilegeService();

    void create(Privilege privilege);

    void edit(Privilege privilege);

    void remove(Privilege privilege);
    void remove(Integer id);

    Privilege find(Integer id);

    List<Privilege> findAll();
}
