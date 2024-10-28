package com.hos.hospital.mapper;

import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SectionMapper {
    int countByExample(SectionExample example);

    int deleteByExample(SectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Section record);

    int insertSelective(Section record);

    List<Section> selectByExample(SectionExample example);

    Section selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByExample(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

	int count();
}