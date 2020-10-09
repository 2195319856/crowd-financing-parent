package com.lzb.service.mapper;

import com.lzb.admin.bean.Admin;
import com.lzb.admin.vo.AdminVo;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface AdminMapper extends Mapper<Admin>, IdsMapper<Admin> {
    Admin login(AdminVo adminVo);
}
