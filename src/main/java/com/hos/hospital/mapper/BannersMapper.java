package com.hos.hospital.mapper;

import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.BannersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BannersMapper {
    int countByExample(BannersExample example);

    int deleteByExample(BannersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Banners record);

    int insertSelective(Banners record);

    List<Banners> selectByExample(BannersExample example);

    Banners selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Banners record, @Param("example") BannersExample example);

    int updateByExample(@Param("record") Banners record, @Param("example") BannersExample example);

    int updateByPrimaryKeySelective(Banners record);

    int updateByPrimaryKey(Banners record);
}