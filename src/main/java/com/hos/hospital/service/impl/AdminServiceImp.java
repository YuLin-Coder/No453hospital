package com.hos.hospital.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.mapper.AdminMapper;
import com.hos.hospital.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService  {
	
    @Autowired
    private  AdminMapper  am;

	
	
	@Override
	public int insertSelective(Admin record) {
		// TODO Auto-generated method stub
		return am.insertSelective(record);
	}

	@Override
	public List<Admin> selectByExample(AdminExample example) {
		// TODO Auto-generated method stub
		return am.selectByExample(example);
	}

	@Override
	public Admin selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return am.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Admin record) {
		// TODO Auto-generated method stub
		return am.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Admin> selectAdmin(Admin admin) {
		AdminExample ae  = new AdminExample();
        AdminExample.Criteria criteria = ae.createCriteria();
        if(admin.getUsername() != null){
            criteria.andUsernameEqualTo(admin.getUsername());
        }
        if(admin.getPassword() != null){
            criteria.andPasswordEqualTo(admin.getPassword());
        }
        return am.selectByExample(ae);
	}
  
	
	
}
