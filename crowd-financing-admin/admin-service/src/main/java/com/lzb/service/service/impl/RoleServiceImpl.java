package com.lzb.service.service.impl;

import com.lzb.admin.bean.Role;
import com.lzb.service.mapper.RoleMapper;
import com.lzb.service.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    public List<Role> selectRole(){
        return roleMapper.selectAll();
    }

    @Override
    public Role selectByIdRole(Long id) {
        return roleMapper.selectByIdRole(id);
    }

    @Override
    public int editRoles(Long aid, Long rid) {
        Role role = roleMapper.selectByIdRole(aid);
        if(role==null){
            return roleMapper.addRoles(aid,rid);
        }else {
            return roleMapper.editRoles(aid,rid);
        }

    }
}
