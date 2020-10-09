package com.lzb.service.controller;
import com.github.pagehelper.PageInfo;
import com.lzb.admin.bean.Admin;
import com.lzb.admin.bean.Role;
import com.lzb.admin.vo.AdminVo;
import com.lzb.common.date.AppDateUtils;
import com.lzb.common.response.RestResponse;
import com.lzb.common.token.JwtToken;
import com.lzb.service.service.AdminService;
import com.lzb.service.service.RoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private RoleService roleService;
    @RequestMapping(value = "login")
    public RestResponse<Admin> adminLogin(@RequestBody AdminVo adminVo){
        Admin admin = adminService.adminLogin(adminVo);
        if (admin==null){
            return new RestResponse<>(500,"账号或密码错误", null,null);
        }else {
            String jwtToken = JwtToken.createJwtToken(admin.getId(), admin.getLoginacct(), 10);
            return new RestResponse<>(200,"登录成功",admin,jwtToken);
        }
    }
    @RequestMapping(value = "findAdmin")
    public RestResponse<PageInfo<Admin>> findAdmin(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "rows",defaultValue = "5")Integer rows,@RequestParam(value = "key",required = false)String key){
        PageInfo<Admin> adminPageInfo = adminService.findAdmin(page, rows, key);
        return new RestResponse<>(200,null,adminPageInfo,null);
    }
    @RequestMapping(value = "findByAdmin")
    public RestResponse<Admin> findByAdmin(Long id){
        Admin admin = adminService.findByAdmin(id);
        return new RestResponse<>(200,null,admin,null);
    }
    @RequestMapping(value = "editAdmin")
    public RestResponse<Admin> editAdmin(@RequestBody Admin admin){
        if (admin.getId()==0||admin.getUsername().trim().equals("")||admin.getPassword().trim().equals("")||admin.getUsername().trim().equals("")||admin.getEmail().trim().equals("")){
            return new RestResponse<>(500,"不能为空",null,null);
        }else {
            int i = adminService.editAdmin(admin);
            if (i==1){
                return new RestResponse<>(200,"修改成功",null,null);
            }else {
                return new RestResponse<>(500,"修改失败",null,null);
            }
        }

    }
    @RequestMapping(value = "deleteAdmin")
    public RestResponse<Admin> deleteAdmin(Long id){
        if (id==1){
            return new RestResponse<>(500,"无权删除",null,null);
        }else {
            int i = adminService.deleteAdmin(id);
            if (i==1){
                return new RestResponse<>(200,"删除成功",null,null);
            }else {
                return new RestResponse<>(500,"删除失败",null,null);
            }
        }

    }
    @RequestMapping(value = "addAdmin")
    public RestResponse<Admin> addAdmin(@RequestBody Admin admin){
        admin.setCreatetime(AppDateUtils.getFormatTime());
        int i = adminService.addAdmin(admin);
        if (i==1){
            return new RestResponse<>(200,"添加成功",null,null);
        }else {
            return new RestResponse<>(500,"添加失败",null,null);
        }
    }

    @RequestMapping(value = "deleteIdsAdmin")
    public RestResponse<Admin> deleteIdsAdmin(String ids){
            if (ids.trim().equals("")){
                return new RestResponse<>(500,"未选中数据",null,null);
            }else {
                String[] split = ids.split(",");
                boolean delete=false;
                for (int i = 0; i <split.length; i++) {
                    if (split[i].trim().equals("1")){
                        break;
                    }else {
                        delete=true;
                    }
                }
                if (delete){
                    int i = adminService.deleteAdminIds(ids);
                    if (i>0){
                        return new RestResponse<>(200,"批量删除成功",null,null);
                    }else {
                        return new RestResponse<>(500,"批量删除失败",null,null);
                    }
                }else {
                    return new RestResponse<>(500,"无权删除",null,null);
                }
            }

    }

    @RequestMapping(value = "selectRole")
    public RestResponse<List<Role>> selectRole(){
        List<Role> roles = roleService.selectRole();
        return new RestResponse<>(200,null,roles,null);
    }

    @RequestMapping(value = "selectByIdRole")
    public RestResponse<Role> selectByIdRole(Long id){
        Role role = roleService.selectByIdRole(id);
        return new RestResponse<>(200,null,role,null);
    }
    @RequestMapping(value = "editRoles")
    public RestResponse<List<Role>> editRoles(Long aid,Long rid){
        int i = roleService.editRoles(aid, rid);
        if (i==1){
            return new RestResponse<>(200,"分配成功",null,null);
        }else {
            return new RestResponse<>(500,"分配失败",null,null);
        }
    }
}
