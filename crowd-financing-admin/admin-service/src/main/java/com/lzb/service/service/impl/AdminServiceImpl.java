package com.lzb.service.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzb.admin.bean.Admin;
import com.lzb.admin.bean.Menu;
import com.lzb.admin.vo.AdminVo;
import com.lzb.service.mapper.AdminMapper;
import com.lzb.service.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(AdminVo adminVo) {
        Admin admin = null;
        try {
            admin = adminMapper.login(adminVo);
            ArrayList<Menu> arrayList = new ArrayList<>();
            ArrayList<Menu> list = new ArrayList<>();
            for (Menu menu :admin.getRole().getMenu()) {
                if (menu.getPid()==0){
                    arrayList.add(menu);
                }else {
                    list.add(menu);
                }
            }
            for (Menu menu: arrayList) {
                for (Menu menu1:list) {
                    if (menu.getMenuid()==menu1.getPid()){
                        menu.getChildren().add(menu1);
                    }
                }
            }
            admin.getRole().getMenu().clear();
            admin.getRole().getMenu().addAll(arrayList);
            return admin;
        } catch (Exception e) {
        }
       return null;
    }

    public PageInfo<Admin> findAdmin(Integer page,Integer rows,String key){
        PageHelper.startPage(page,rows);
        Example example = new Example(Admin.class);
        if (StringUtil.isNotEmpty(key)){
            example.createCriteria().orLike("username","%"+key+"%");
        }
        List<Admin> list = adminMapper.selectByExample(example);

        PageInfo<Admin> pageInfo = new PageInfo<>(list);
       return pageInfo;
    }

    @Override
    public Admin findByAdmin(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int editAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    @Transactional
    public int deleteAdmin(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int addAdmin(Admin admin) {
        int i=0;
        try {
          i = adminMapper.insert(admin);
        } catch (Exception e) {
        }
        return i;
    }

    @Override
    public int deleteAdminIds(String ids) {
        int i=0;
        try {
          i=  adminMapper.deleteByIds(ids);
        } catch (Exception e) {
        }
        return i;
    }


}
