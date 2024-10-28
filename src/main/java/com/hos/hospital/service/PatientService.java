package com.hos.hospital.service;


import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.AdminExample;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.PatientExample;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;

import java.util.List;

public interface PatientService {

	   int countByExample(PatientExample example);


	    int deleteByPrimaryKey(Integer id);


	    int insertSelective(Patient record);

	    List<Patient> selectByExample(PatientExample example);

	    Patient selectByPrimaryKey(Integer id);



	    int updateByPrimaryKeySelective(Patient record);


		PageInfo<Patient> selectPatientList(Patient patient, Integer page, Integer size);


		List<Patient> selectPatient(Patient patient);


}
