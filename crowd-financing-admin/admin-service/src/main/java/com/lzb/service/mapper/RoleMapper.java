package com.lzb.service.mapper;

import com.lzb.admin.bean.Role;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface RoleMapper extends Mapper<Role> {
    Role selectByIdRole(Long id);
    List<Role> selectAll();
    @Update("UPDATE  t_admin_role ta SET ta.roleid=#{rid} WHERE ta.adminid=#{aid}")
    int editRoles(Long aid,Long rid);
    @Insert("INSERT INTO t_admin_role (roleid,adminid)values(#{rid},#{aid})")
    int addRoles(Long aid,Long rid);
}
