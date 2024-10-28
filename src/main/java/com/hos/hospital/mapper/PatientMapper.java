package com.hos.hospital.mapper;

import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.PatientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PatientMapper {
    int countByExample(PatientExample example);

    int deleteByExample(PatientExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Patient record);

    int insertSelective(Patient record);

    List<Patient> selectByExample(PatientExample example);

    Patient selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Patient record, @Param("example") PatientExample example);

    int updateByExample(@Param("record") Patient record, @Param("example") PatientExample example);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);
}