package com.hos.hospital.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Messages;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;
import com.hos.hospital.mapper.AdminMapper;
import com.hos.hospital.mapper.DoctorMapper;
import com.hos.hospital.mapper.SectionMapper;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.DoctorService;
import com.hos.hospital.service.SectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DoctorServiceImp implements  DoctorService  {
	
    @Autowired
    private   DoctorMapper  dm;

	@Override
	public int countByExample(DoctorExample example) {
		return  dm.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dm.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Doctor record) {
		// TODO Auto-generated method stub
		return dm.insertSelective(record);
	}

	@Override
	public List<Doctor> selectByExample(DoctorExample example) {
		// TODO Auto-generated method stub
		return dm.selectByExample(example);
	}

	@Override
	public Doctor selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dm.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Doctor record) {
		// TODO Auto-generated method stub
		return dm.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Doctor record) {
		// TODO Auto-generated method stub
		return dm.updateByPrimaryKey(record);
	}

	@Override
	public List<Doctor> selectDoctor(Doctor doctor) {
		DoctorExample se  = new  DoctorExample();
		DoctorExample.Criteria criteria = se.createCriteria();
        if(doctor != null){
            if(doctor.getName() != null && !"".equals(doctor.getName())){
                //模糊查询
                criteria.andNameLike( "%" +doctor.getName() +"%");
            }
            if(doctor.getUsername() != null){
                criteria.andUsernameEqualTo(doctor.getUsername());
            }
            if(doctor.getPasswoed() != null){
                criteria.andPasswoedEqualTo(doctor.getPasswoed());
            }
            if(doctor.getBegindate() != null) {
            	criteria.andBegindateEqualTo(doctor.getBegindate());
           
            }
            if(doctor.getSid() != null) {
            	criteria.andSidEqualTo(doctor.getSid());
            }
        }
        se.setOrderByClause("id desc");
		return dm.selectByExample(se);
	}

	@Override
	public PageInfo<Doctor> selectDoctorList(Doctor doctor, Integer page, Integer size) {
		    PageHelper.startPage(page,size);
		    DoctorExample se  = new DoctorExample();
		    DoctorExample.Criteria criteria = se.createCriteria();
	        if(doctor != null){
	            if(doctor.getName() != null && !"".equals(doctor.getName())){
	                //模糊查询
	                criteria.andNameLike( "%" +doctor.getName() +"%");
	            }
	            
	        }
	        se.setOrderByClause("id desc");
	        List<Doctor> shops = dm.selectByExample(se);
	        PageInfo<Doctor> pageinfo = new PageInfo<Doctor>(shops);
	        return pageinfo;
	}

	@Override
	public List<Doctor> selectTime(Doctor doctor) {
		// TODO Auto-generated method stub
		return dm.selectTime(doctor);
	}


	

 
	
	
}
