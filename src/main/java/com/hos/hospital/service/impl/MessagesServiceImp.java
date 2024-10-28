package com.hos.hospital.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.BannersExample;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Messages;
import com.hos.hospital.entity.MessagesExample;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.PatientExample;
import com.hos.hospital.mapper.AdminMapper;
import com.hos.hospital.mapper.BannersMapper;
import com.hos.hospital.mapper.MessagesMapper;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.BannersService;
import com.hos.hospital.service.MessagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImp implements   MessagesService  {
	
    @Autowired
    private   MessagesMapper  mm;

	@Override
	public int countByExample(MessagesExample example) {
		// TODO Auto-generated method stub
		return mm.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mm.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Messages record) {
		// TODO Auto-generated method stub
		return mm.insertSelective(record);
	}

	@Override
	public List<Messages> selectByExample(MessagesExample example) {
		// TODO Auto-generated method stub
		return mm.selectByExample(example);
	}

	@Override
	public Messages selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mm.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Messages record) {
		// TODO Auto-generated method stub
		return mm.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Messages> selectMessages(Messages message) {
		// TODO Auto-generated method stub
		MessagesExample se  = new  MessagesExample();
		MessagesExample.Criteria criteria = se.createCriteria();
        if(message != null){
            if(message.getType() != null){
            	if(message.getType() != -1) {
            		  criteria.andTypeEqualTo(message.getType());
            	}else {
            		criteria.andTypeNotEqualTo(2);	
            	}
            }
            if(message.getUid() != null) {
            	criteria.andUidEqualTo(message.getUid());
            }
            if(message.getDid() != null) {
            	criteria.andDidEqualTo(message.getDid());
            }
            if(message.getUsername() != null) {
            	criteria.andUsernameEqualTo(message.getUsername());
            }
            if(message.getTime() != null) {
            	criteria.andTimeEqualTo(message.getTime());
            }
        }
        se.setOrderByClause("id desc");
		return mm.selectByExample(se);
	}
	
	
	

	@Override
	public PageInfo<Messages> selectMessagesList(Messages messages, Integer page, Integer size) {
		    PageHelper.startPage(page,size);
		    MessagesExample se  = new MessagesExample();
		    MessagesExample.Criteria criteria = se.createCriteria();
	        if(messages != null){
	            if(messages.getUsername() != null && !"".equals(messages.getUsername())){
	                //模糊查询
	                criteria.andUsernameLike( "%" +messages.getUsername() +"%");
	            }
	            if(messages.getUid() != null) {
	            	criteria.andUidEqualTo(messages.getUid());
	            }
	            if(messages.getType() != null){
	            	criteria.andTypeEqualTo(messages.getType());
	            }
	            if(messages.getDid() != null) {
	            	criteria.andDidEqualTo(messages.getDid());
	            }
	            if(messages.getTime() != null) {
	            	criteria.andTimeEqualTo(messages.getTime());
	            }
	            
	        }
	        se.setOrderByClause("id desc");
	        List<Messages> shops = mm.selectByExample(se);
	        PageInfo<Messages> pageinfo = new PageInfo<Messages>(shops);
	        return pageinfo;
	}

	@Override
	public List<Messages> selectMessagesTWO(Messages message) {
		// TODO Auto-generated method stub
				MessagesExample se  = new  MessagesExample();
				MessagesExample.Criteria criteria = se.createCriteria();
		        if(message != null){
		            if(message.getType() != null){
		            	if(message.getType() != -1) {
		            		  criteria.andTypeEqualTo(message.getType());
		            	}else {
		            		criteria.andTypeNotEqualTo(2);	
		            	}
		            }
		            if(message.getUid() != null) {
		            	criteria.andUidEqualTo(message.getUid());
		            }
		            if(message.getDid() != null) {
		            	criteria.andDidEqualTo(message.getDid());
		            }
		            if(message.getUsername() != null) {
		            	criteria.andUsernameEqualTo(message.getUsername());
		            }
		            if(message.getTime() != null) {
		            	criteria.andTimeEqualTo(message.getTime());
		            }
		        }
		        se.setOrderByClause("time desc");
				return mm.selectByExample(se);
	}

	@Override
	public PageInfo<Messages> selectMessagesListDemo(Messages messages, int page, int size) {
	    PageHelper.startPage(page,size);
	    MessagesExample se  = new MessagesExample();
	    MessagesExample.Criteria criteria = se.createCriteria();
        if(messages != null){
            if(messages.getUsername() != null && !"".equals(messages.getUsername())){
                //模糊查询
                criteria.andUsernameLike( "%" +messages.getUsername() +"%");
            }
            if(messages.getUid() != null) {
            	criteria.andUidEqualTo(messages.getUid());
            }
            if(messages.getType() != null){
            	criteria.andTypeEqualTo(messages.getType());
            }
            if(messages.getDid() != null) {
            	criteria.andDidEqualTo(messages.getDid());
            }
            if(messages.getTime() != null) {
            	criteria.andTimeEqualTo(messages.getTime());
            }
            
        }
        List<Messages> shops = mm.selectByExample(se);
        PageInfo<Messages> pageinfo = new PageInfo<Messages>(shops);
        return pageinfo;
	}

	@Override
	public List<Messages> selectMessagesPai(Messages messages) {
		    MessagesExample se  = new MessagesExample();
		    MessagesExample.Criteria criteria = se.createCriteria();
	        if(messages != null){
	            if(messages.getUsername() != null && !"".equals(messages.getUsername())){
	                //模糊查询
	                criteria.andUsernameLike( "%" +messages.getUsername() +"%");
	            }
	            if(messages.getUid() != null) {
	            	criteria.andUidEqualTo(messages.getUid());
	            }
	            if(messages.getType() != null){
	            	criteria.andTypeEqualTo(messages.getType());
	            }
	            if(messages.getDid() != null) {
	            	criteria.andDidEqualTo(messages.getDid());
	            }
	            if(messages.getTime() != null) {
	            	criteria.andTimeEqualTo(messages.getTime());
	            }
	        }
	        se.setOrderByClause("pai Asc");
	    	return    mm.selectByExample(se);
	}

	
  
	
	
}
