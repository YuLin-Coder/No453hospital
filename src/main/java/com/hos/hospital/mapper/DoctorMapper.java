package com.hos.hospital.mapper;

import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorMapper {
    int countByExample(DoctorExample example);

    int deleteByExample(DoctorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    List<Doctor> selectByExample(DoctorExample example);

    Doctor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Doctor record, @Param("example") DoctorExample example);

    int updateByExample(@Param("record") Doctor record, @Param("example") DoctorExample example);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);
    
	List<Doctor> selectTime(Doctor doctor);
}