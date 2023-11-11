package com.shop.fashionaccessory.employee.privilege.service;

import com.shop.fashionaccessory.employee.privilege.Privilege;
import com.shop.fashionaccessory.service.BaseService;

public class PrivilegeService extends BaseService<Privilege> implements PrivilegeServiceLocal {

    public PrivilegeService() {
        super(Privilege.class);
    }
}
