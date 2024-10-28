package com.hos.hospital.service;


import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.BannersExample;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Messages;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;

import java.util.List;

public interface DoctorService {

    int countByExample(DoctorExample example);


    int deleteByPrimaryKey(Integer id);

    int insertSelective(Doctor record);

    List<Doctor> selectByExample(DoctorExample example);
    

    Doctor selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);


	List<Doctor> selectDoctor(Doctor doctor);

	PageInfo<Doctor> selectDoctorList(Doctor doctor, Integer page, Integer size);


	List<Doctor> selectTime(Doctor doctor);





}
