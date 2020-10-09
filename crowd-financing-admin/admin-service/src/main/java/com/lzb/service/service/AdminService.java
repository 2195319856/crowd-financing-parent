package com.lzb.service.service;

import com.github.pagehelper.PageInfo;
import com.lzb.admin.bean.Admin;
import com.lzb.admin.vo.AdminVo;


public interface AdminService{
   Admin adminLogin(AdminVo adminVo);
   PageInfo<Admin> findAdmin(Integer page,Integer rows,String key);
   Admin findByAdmin(Long id);
   int editAdmin(Admin admin);
   int deleteAdmin(Long id);
   int addAdmin(Admin admin);
   int deleteAdminIds(String ids);
}
