package com.hos.hospital.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;
import com.hos.hospital.mapper.AdminMapper;
import com.hos.hospital.mapper.SectionMapper;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.SectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  SectionServiceImp implements SectionService  {
	
    @Autowired
    private  SectionMapper  sm;

	@Override
	public int deleteByExample(SectionExample example) {
		// TODO Auto-generated method stub
		return sm.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return  sm.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Section record) {
		// TODO Auto-generated method stub
		return  sm.insertSelective(record);
	}

	@Override
	public List<Section> selectByExample(Section section) {
		// TODO Auto-generated method stub
		 SectionExample se  = new SectionExample();
	        SectionExample.Criteria criteria = se.createCriteria();
	        if(section != null){
	            if(section.getName() != null && !"".equals(section.getName())){
	                //模糊查询
	                criteria.andNameLike( "%" +section.getName() +"%");
	            }
	            if(section.getType() != null) {
	            	criteria.andTypeEqualTo(section.getType() );
	            }
	            if(section.getPid() != null) {
	            	criteria.andPidEqualTo(section.getPid() );
	            }
	            
	        }
		return  sm.selectByExample(se);
	}

	@Override
	public Section selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return  sm.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Section record, SectionExample example) {
		// TODO Auto-generated method stub
		return  sm.updateByExampleSelective(record,example);
	}

	@Override
	public int updateByPrimaryKeySelective(Section record) {
		// TODO Auto-generated method stub
		return  sm.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageInfo<Section> selectSectionListt(Section section, Integer page, Integer size) {
	        PageHelper.startPage(page,size);
	        SectionExample se  = new SectionExample();
	        SectionExample.Criteria criteria = se.createCriteria();
	        if(section != null){
	            if(section.getName() != null && !"".equals(section.getName())){
	                //模糊查询
	                criteria.andNameLike( "%" +section.getName() +"%");
	            }
	            if(section.getType() != null) {
	            	criteria.andTypeEqualTo(section.getType() );
	            }
	            if(section.getPid() != null) {
	            	criteria.andPidEqualTo(section.getPid() );
	            }
	            
	        }
	        List<Section> shops = sm.selectByExample(se);
	        PageInfo<Section> pageinfo = new PageInfo<Section>(shops);
	        return pageinfo;
	
	}

	@Override
	public int countByExample(SectionExample example) {
		// TODO Auto-generated method stub
		return sm.countByExample(example);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return  sm.count();
	}

 
	
	
}
