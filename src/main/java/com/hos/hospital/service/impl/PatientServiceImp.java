package com.hos.hospital.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.BannersExample;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.PatientExample;
import com.hos.hospital.mapper.AdminMapper;
import com.hos.hospital.mapper.BannersMapper;
import com.hos.hospital.mapper.DoctorMapper;
import com.hos.hospital.mapper.PatientMapper;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.BannersService;
import com.hos.hospital.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp  implements  PatientService  {
	
    @Autowired
    private   PatientMapper  pm;
	

	@Override
	public int countByExample(PatientExample example) {
		// TODO Auto-generated method stub
		return pm.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return pm.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Patient record) {
		// TODO Auto-generated method stub
		return pm.insertSelective(record);
	}

	@Override
	public List<Patient> selectByExample(PatientExample example) {
		// TODO Auto-generated method stub
		return pm.selectByExample(example);
	}

	@Override
	public Patient selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return pm.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Patient record) {
		// TODO Auto-generated method stub
		return  pm.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageInfo<Patient> selectPatientList(Patient patient, Integer page, Integer size) {
		     PageHelper.startPage(page,size);
		     PatientExample se  = new PatientExample();
		     PatientExample.Criteria criteria = se.createCriteria();
	        if(patient != null){
	            if(patient.getName() != null && !"".equals(patient.getName())){
	                //模糊查询
	                criteria.andNameLike( "%" +patient.getName() +"%");
	            }
	            
	        }
	        se.setOrderByClause("id desc");
	        List<Patient> shops = pm.selectByExample(se);
	        PageInfo<Patient> pageinfo = new PageInfo<Patient>(shops);
	        return pageinfo;
	}

	@Override
	public List<Patient> selectPatient(Patient patient) {
		  PatientExample se  = new PatientExample();
		  PatientExample.Criteria criteria = se.createCriteria();
	        if(patient != null){
	           if(patient.getUsername() != null) {
	        	   criteria.andUsernameEqualTo(patient.getUsername());
	           }
    		   if(patient.getPassword() != null) {
	        	   criteria.andPasswordEqualTo(patient.getPassword());
	           }
    		   if(patient.getPid() != null) {
    			   criteria.andPidEqualTo(patient.getPid());
    		   }
	        }
	        se.setOrderByClause("id desc");
	        return  pm.selectByExample(se);
	}

   
  
	
	
}
