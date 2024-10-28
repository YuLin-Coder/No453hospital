package com.hos.hospital.service;


import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;

import java.util.List;

public interface SectionService {
      int countByExample(SectionExample example);


	    int deleteByExample(SectionExample example);

	    int deleteByPrimaryKey(Integer id);


	    int insertSelective(Section record);

	    List<Section> selectByExample(Section section);

	    Section selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(Section record,SectionExample example);


	    int updateByPrimaryKeySelective(Section record);

		PageInfo<Section> selectSectionListt(Section section, Integer page, Integer size);


		int count();



}
