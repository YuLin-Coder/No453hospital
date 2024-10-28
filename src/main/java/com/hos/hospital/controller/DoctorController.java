package com.hos.hospital.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Messages;
import com.hos.hospital.entity.MessagesExample;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.PatientExample;
import com.hos.hospital.entity.Section;
import com.hos.hospital.entity.SectionExample;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.DoctorService;
import com.hos.hospital.service.MessagesService;
import com.hos.hospital.service.PatientService;
import com.hos.hospital.service.SectionService;
import com.hos.hospital.utils.StringRandom;


/**
 * 医生端
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
     @Autowired
     private AdminService adminService;

     @Autowired
     private DoctorService doctorService;
     
     @Autowired
     private SectionService sectionService;
     
     @Autowired
     private  PatientService  patientService;
     
     
     @Autowired
     private   MessagesService  messagesService;
     
     
     private Integer size  = 8;//每页显示数量

     
     
     
     /**
  	  * 修改信息
      * @param model
      * @return
      */
     @RequestMapping("/tiaomessagelist")
     public String tiaomessagelist(@RequestBody  List<Messages> mlist,Model model) {

    	 System.out.println(mlist.size());
    	 
         model.addAttribute("mlist",mlist);
         return    "doctor/messageList";
     }
     
     
     
     @RequestMapping("/index")
     public String index(Model model,HttpServletRequest request) {
    	  HttpSession session = request.getSession();
          Doctor dt = (Doctor) session.getAttribute("DOCTOR");
          if(dt == null) {
        	  return  "redirect:/login/index";  
          }
         int  doctor  = doctorService.countByExample(null); //医生总数
         int  section = sectionService.count(); //科室总数
         //患者总数
         int patient = 0;
         List<Patient> selectByExample = patientService.selectByExample(null);
         Messages   mess  = new Messages();
         for (Patient pa : selectByExample) {
        	 if(pa.getName() != null) {
        		mess.setDid(dt.getId());
         	 	mess.setUsername(pa.getName());
         	    List<Messages> selectMessages = messagesService.selectMessages(mess);
         	    if(selectMessages.size() > 0 )
         	    {
         	    	patient++;
         	    }
        	 }
		 }
	     //预约总数
	 	 MessagesExample me  = new  MessagesExample();
		 MessagesExample.Criteria mecriteria = me.createCriteria();
		 mecriteria.andDidEqualTo(dt.getId());
         int  messages = messagesService.countByExample(me); 
         model.addAttribute("doctor",doctor);
         model.addAttribute("section",section);
         model.addAttribute("patient",patient);
         model.addAttribute("messages",messages);
         PageInfo<Doctor> pageInfo  =  doctorService.selectDoctorList(null,1,4);
          if(pageInfo.getList() != null && pageInfo.getList().size() >0 ) {
        	    List<Doctor> list = pageInfo.getList();
        	    StringBuffer sb = new StringBuffer();
        	    StringBuffer shu = new StringBuffer();
        	    int v = list.size()-1;
        	    for(int i=0;i<list.size();i++) {
	        		 if(v==i) {
	        			 sb.append(list.get(i).getName());
		        		 shu.append(list.get(i).getYipeoples());
	        		}else {
	        			 sb.append(list.get(i).getName()+",");
		        		 shu.append(list.get(i).getYipeoples()+",");
	        		}
	        	 }
	        	   model.addAttribute("name",sb.toString());
	               model.addAttribute("nu",shu.toString());
          }
         return  "doctor/index";
     }
     
	    
     /**
  	  * 修改信息
      * @param model
      * @return
      */
     @RequestMapping("/doctorUptatePage")
     public String doctorUptatePage(Model model,HttpServletRequest request) {
         HttpSession session = request.getSession();
         Doctor dt = (Doctor) session.getAttribute("DOCTOR");
         if(dt != null) {
        	 Doctor  doctor = doctorService.selectByPrimaryKey(dt.getId());
 			List<Section> sectionlist2  = null;
 			model.addAttribute("doctor",doctor);
 			//科室
 	    	Section  se = new  Section();
 	    	se.setType(1);
 		    List<Section> sectionlist = sectionService.selectByExample(se);
 		    model.addAttribute("sectionlist", sectionlist);
 	    	//科室详情
 		    Section se1 = sectionService.selectByPrimaryKey(doctor.getSid());
 		    if(se1 != null) {
 		    	Section  section = new  Section();
 		    	section.setPid(se1.getPid());
 		    	section.setType(2);
 		    	sectionlist2 = sectionService.selectByExample(section);
 			    model.addAttribute("sectionlist2", sectionlist2);
 			    model.addAttribute("se1", se1);
 		    } 
         }
         return  "doctor/doctorUptate";
     }
     
     
     
     /**
      *  修改医生信息
      */
     @RequestMapping("/messageTime")
     public String messageTime(String name,Model model,HttpServletRequest request) {
	   	HttpSession session = request.getSession();
        Doctor dt = (Doctor) session.getAttribute("DOCTOR");
        if(name !=  null) {
        	Messages  mess  = new Messages();
        	mess.setDid(dt.getId());
     	 	mess.setUsername(name);
     	    List<Messages> selectMessages = messagesService.selectMessagesTWO(mess);
     	    model.addAttribute("messagesList", selectMessages);
        }
         return  "doctor/messageTime";
     }
     
     
	     /**
	      *  修改医生信息
	      */
	     @RequestMapping("/admindoctorUptate")
	     public String adminUptatePassword(Doctor doctor,Model model) {
	        if(doctor !=  null && doctor.getId() != null) {
	     	   if(doctor.getSid() != null) {
	     		   Section section = sectionService.selectByPrimaryKey(doctor.getSid());
	     		   doctor.setSid(section.getId());
	     		   doctor.setSname(section.getName());
	     	   }
	     	   doctorService.updateByPrimaryKeySelective(doctor);
	        }
	         return  "redirect:/doctor/index";
	     }
	     
	     
	     /**
	      * 预约信息列表
	      */
	     @RequestMapping("/messageList")
	     public String doctorList(Model model,  Messages messages, @RequestParam(value="page",defaultValue="1")Integer page,Integer type,HttpServletRequest request) {
	     	if(messages == null) {
	     		messages = new Messages();
	     	}
	         HttpSession session = request.getSession();
	         Doctor dt = (Doctor) session.getAttribute("DOCTOR");
	         if(dt != null){
	        	 messages.setDid(dt.getId());
	         }else{
	        	  return  "redirect:/login/index";
	         }
	     	
	     	messages.setType(type);
	     	//底层数据
	     	PageInfo<Messages> pageInfo = messagesService.selectMessagesList(messages,page,size);
	     	//工作区数据
	     	messages.setTime(new Date());
	    	List<Messages> list = messagesService.selectMessagesPai(messages);
	    	 model.addAttribute("mlist",list);
	    	 model.addAttribute("messagesList",pageInfo.getList());
	         model.addAttribute("pageInfo",pageInfo);
	         model.addAttribute("messages",messages);
	         model.addAttribute("type",type);
	         return    "doctor/messageList";
	     }
	     
	     
	     /**
			 *医生列表查询
			 */
		    @RequestMapping(value = "/messageAjax")
		    @ResponseBody
		    public  List<Messages>  doctorList(HttpServletRequest request) {
		    	 Messages		messages = new Messages();
		         HttpSession session = request.getSession();
		         Doctor dt = (Doctor) session.getAttribute("DOCTOR");
		         messages.setDid(dt.getId());
		       
		     	messages.setType(1);
		     	messages.setTime(new Date());
		    	PageInfo<Messages> pageInfo2 = messagesService.selectMessagesListDemo(messages,1,99);
		       
		       return pageInfo2.getList();
		    }  
		    
      /**
			 *医生列表查询
			 */
		    @RequestMapping(value = "/messagesQundingUptate")
		    @ResponseBody
		    public String  messagesQundingUptate(Integer id) {
		    	   if(id != null) {
			        	Messages messages = new Messages();
			        	messages.setId(id);
			        	messages.setType(3); //3表示预约成功
			        	messagesService.updateByPrimaryKeySelective(messages);
			        	Messages selectByPrimaryKey = messagesService.selectByPrimaryKey(id);
			        	Messages  mes = new Messages();
			        	mes.setType(1);
			        	mes.setTime(new Date());
			        	mes.setDid(selectByPrimaryKey.getDid());
				    	List<Messages> list = messagesService.selectMessagesPai(mes);
				    	for (int i = 0; i < list.size(); i++) {
				    		list.get(i).setPai(i+1);
				    		messagesService.updateByPrimaryKeySelective(list.get(i));
						}
			        }
		       
		       return "ok";
		    }  
 
	     
	     
	     /**
	         *患者信息列表
	      */
	     @RequestMapping("/patientList")
	     public String messageList(Model model, Patient patient, @RequestParam(value="page",defaultValue="1")Integer page,HttpServletRequest request) {
	     	if(patient == null) {
	     		patient = new Patient();
	     	}
	        HttpSession session = request.getSession();
	         Doctor dt = (Doctor) session.getAttribute("DOCTOR");
	         if(dt == null){
	        	  return  "redirect:/login/index";
	         }
				/*
				 * PageInfo<Patient> pageInfo =
				 * patientService.selectPatientList(patient,1,size); List<Patient> list =
				 * pageInfo.getList(); List<Patient> list2 = new ArrayList<Patient>(); Messages
				 * messages = new Messages(); boolean pan = false; SimpleDateFormat sdf = new
				 * SimpleDateFormat("yyyy-MM-dd"); for (Patient pa : list) { if(pa.getPid() !=
				 * null && pa.getPid() != 0){ messages.setDid(dt.getId());
				 * messages.setUid(pa.getPid()); messages.setUsername(pa.getName());
				 * List<Messages> ml = messagesService.selectMessages(messages); if(ml.size() >
				 * 0 ){ Date time = ml.get(0).getTime(); pa.setUsername(sdf.format(time));
				 * pa.setPhone(dt.getName()); pa.setIdentitys(dt.getSname()); list2.add(pa); }
				 * 
				 * } } if(list2.size() <= 8) { pageInfo.setPages(1); }
				 */
	         Messages messages = new Messages();
//	         messages.setTime(new Date());
	         messages.setType(1);
	         messages.setDid(dt.getId());    
	         PageInfo<Messages> pageInfo = messagesService.selectMessagesList(messages, 1, size);
	         model.addAttribute("doctorList",pageInfo.getList());
	         model.addAttribute("pageInfo",pageInfo);
	         model.addAttribute("patient",patient);
	         return    "doctor/patientList";
	     }
	     
	    /**
		   *预约信息列表
	     * @throws ParseException 
		 */
	    @RequestMapping(value = "/tiaozhuanList")
	    @ResponseBody
	    public  String  messagesList(@RequestParam("xiao")Integer xiao,@RequestParam("da")Integer da)  {
	    	Messages message = new Messages();
	    	if(xiao != null & da != null) {
	    		Messages mexiao = messagesService.selectByPrimaryKey(xiao);
	    		Integer px = mexiao.getPai();
	    		Messages meda = messagesService.selectByPrimaryKey(da);
	    		mexiao.setPai(meda.getPai());
	    		meda.setPai(px);
	    		messagesService.updateByPrimaryKeySelective(mexiao);
	    		messagesService.updateByPrimaryKeySelective(meda);
	    	  }
		      return  null;
	    }
  
	     /**
	      *  确定预约
	      */
	     @RequestMapping("/messagesUptate")
	     public String messagesUptate(Integer id) {
	        if(id != null) {
	        	Messages messages = new Messages();
	        	messages.setId(id);
	        	messages.setType(3); //3表示预约成功
	        	messagesService.updateByPrimaryKeySelective(messages);
	        }
	         return  "redirect:/doctor/messageList?type=1";
	     }
	     
	     /**
	      *  取消
	      */
	     @RequestMapping("/messagesQuXiao")
	     public String messagesQuXiao(Integer id) {
	        if(id != null) {
	        	Messages messages = new Messages();
	        	messages.setId(id);
	        	messages.setType(2); //2取消预约
	        	messagesService.updateByPrimaryKeySelective(messages);
	        }
	         return  "redirect:/doctor/messageList?type=1";
	     }
	     
	     
	     /**
	      *  退号
	      */
	     @RequestMapping("/messagesTui")
	     public String messagesTui(Integer id) {
	        if(id != null) {
	        	Messages messages = new Messages();
	        	messages.setId(id);
	        	messages.setType(4); //4退号失败
	        	messagesService.updateByPrimaryKeySelective(messages);
	        }
	         return  "redirect:/doctor/messageList?type=3";
	     }
	     
	     
}
