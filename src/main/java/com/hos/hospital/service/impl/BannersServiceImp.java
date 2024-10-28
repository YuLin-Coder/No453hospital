package com.hos.hospital.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.BannersExample;
import com.hos.hospital.mapper.AdminMapper;
import com.hos.hospital.mapper.BannersMapper;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.BannersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannersServiceImp implements  BannersService  {
	
    @Autowired
    private   BannersMapper  bm;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return bm.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Banners record) {
		// TODO Auto-generated method stub
		return  bm.insertSelective(record);
	}

	@Override
	public List<Banners> selectByExample(BannersExample example) {
		// TODO Auto-generated method stub
		return  bm.selectByExample(example);
	}

	@Override
	public Banners selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return  bm.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Banners record) {
		// TODO Auto-generated method stub
		return bm.updateByPrimaryKeySelective(record);
	}

	
	
  
	
	
}
