package com.hos.hospital.service;


import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.BannersExample;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;

import java.util.List;

public interface BannersService {


    int deleteByPrimaryKey(Integer id);


    int insertSelective(Banners record);

    List<Banners> selectByExample(BannersExample example);

    Banners selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Banners record);

   

	

}
