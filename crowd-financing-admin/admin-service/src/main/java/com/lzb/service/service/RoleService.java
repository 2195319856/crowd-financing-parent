package com.lzb.service.service;

import com.lzb.admin.bean.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectRole();
    Role selectByIdRole(Long id);
   int editRoles(Long aid,Long rid);
}
