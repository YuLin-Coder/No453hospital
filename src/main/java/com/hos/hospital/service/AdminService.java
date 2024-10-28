package com.hos.hospital.service;


import org.apache.ibatis.annotations.Param;

import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;

import java.util.List;

public interface AdminService {

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

	List<Admin> selectAdmin(Admin admin);


}
