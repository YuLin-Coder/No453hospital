package com.hos.hospital.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Messages;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.PatientExample;
import com.hos.hospital.entity.Section;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.BannersService;
import com.hos.hospital.service.DoctorService;
import com.hos.hospital.service.MessagesService;
import com.hos.hospital.service.PatientService;
import com.hos.hospital.service.SectionService;

/**
 * 后端管理员控制层
 */
@Controller
@RequestMapping("/api")
public class PatientController {
	   private Integer size  = 6;//每页显示数量
    @Autowired
    private AdminService adminService;
  
    @Autowired
    private SectionService sectionService;
    
    @Autowired
    private BannersService  bannersService;   
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private   PatientService  patientService;
    
    @Autowired
    private   MessagesService  messagesService;
    

    /**
     * 医生列表
     */
    @RequestMapping("/doctorList1")
    public String doctorList(Model model, Doctor doctor, @RequestParam(value="page",defaultValue="1")Integer page) {
    	if(doctor == null) {
    		doctor = new Doctor();
    	}
    	PageInfo<Doctor> pageInfo  =  doctorService.selectDoctorList(doctor,page,size);
    	
    	List<Doctor> list = pageInfo.getList();
        model.addAttribute("doctorList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("doctor",doctor);
        return    "patient/doctorList";
    }
       /**
  		 *登录
        * @throws ParseException 
  		 */
  	    @RequestMapping(value = "/userLogin")
  	    @ResponseBody
  	    public  Patient  userLogin(@RequestBody Patient patient) throws ParseException {
  	    	List<Patient>  list = patientService.selectPatient(patient);
  	    	if(patient != null && patient.getUsername() != null && patient.getPassword() != null) {
	  	    	if(list.size() > 0) {
	  	    	    return  list.get(0);
	  	    	}
  	    	}
  	      return  patient;
  	    } 
       /**
	 	 *登录
        * @throws ParseException 
		 */
	    @RequestMapping(value = "/passwordSave")
	    @ResponseBody
	    public  String  passwordSave(@RequestBody Patient patient ) throws ParseException {
	    	if(patient != null && patient.getUsername() != null && patient.getPassword() != null) {
	    		Patient  pa = new Patient();
	    	    pa.setUsername(patient.getUsername());
		    	List<Patient>  list = patientService.selectPatient(pa);
	    		if(list.size() > 0) {
	  	    	    return  "err";
	  	    	}
	    		patientService.insertSelective(patient);
	    	    return  "ok";
	    	}
  	    	
	      return  "err";
	    } 
	  
    
    
  	    
       /**
   		 *登录验证
         * @throws ParseException 
   		 */
   	    @RequestMapping(value = "/userLoginView")
   	    @ResponseBody
   	    public  String  userLoginView(HttpServletRequest request) throws ParseException {
   	    	   HttpSession session = request.getSession();
   	    	   Patient  patient =(Patient) session.getAttribute("USER");
   	    	   System.out.println("*********登陆验证********");
		         System.out.println(patient);   
   	            if(patient != null) {
   	             return  "ok";
   	            }
   		        
   	         return  "err";
   	    } 

	    /**
	     *banner图
	     */
	    @RequestMapping(value = "/bannerList")
	    @ResponseBody
	    public String[] formAdd() {
	    	Banners banners = bannersService.selectByPrimaryKey(1);
	    	String[] split  = null;
	    	if(banners != null && banners.getImg() != null) {
	    	  split = banners.getImg().split(",");
	    	}
	        return split;
	    }
    
	    /**
		   *科室查询
		 */
	    @RequestMapping(value = "/sectionList")
	    @ResponseBody
	    public  Map<String,List<Section>>  sectionList() {
            Map<String,List<Section>> map =  new HashMap<String,List<Section>>();
	    	List<Section> sectionlist2  = null;
	    	Section  se = new  Section();
	    	se.setType(1);
		    List<Section> sectionlist = sectionService.selectByExample(se);
		    if(sectionlist.size() > 0 ) {
		    	//科室详情
		    	Section  section = new  Section();
		    	section.setPid(sectionlist.get(0).getId());
		    	section.setType(2);
		    	sectionlist2 = sectionService.selectByExample(section);
	        }
		    map.put("sectionlist",sectionlist);
		    map.put("sectionlist2",sectionlist2);  
	        return map;
	    }
    
	    
	
	    
	    /**
		 *科室下级查询
		 */
	    @RequestMapping(value = "/sectionXiaList")
	    @ResponseBody
	    public  List<Section>  sectionXiaList(Integer id) {
	    	Section  se = new  Section();
	    	se.setPid(id);
	    	se.setType(2);
		    List<Section> sectionlist = sectionService.selectByExample(se);
	        return sectionlist;
	    }
	    
	    /**
		 *科室下级查询
		 */
	    @RequestMapping(value = "/patientPai")
	    @ResponseBody
	    public Integer  patientPai(Integer id) {
	    	Patient pa = new Patient();
	    	pa.setPid(id);
			  PatientExample se  = new PatientExample();
			  PatientExample.Criteria criteria = se.createCriteria();
		        if(pa != null){
				   if(pa.getPid() != null) {
					   criteria.andPidEqualTo(pa.getPid());
				   }
		        }
	      
		     List<Patient> selectByExample = patientService.selectByExample(se);
	    	if(selectByExample.size() >0 ) {
	    		List<Messages> lmlist = messagesService.selectByExample(null);
	    		int j = 0 ;
	    		for (Messages me : lmlist) {
					if(me.getUid() == id) {
						   return j;
					}
	    			j++;
				}
	    	}
	        return -1;
	    }
	    
	    
	    
	    /**
		 *查询科室
		 */
	    @RequestMapping(value = "/sectioNameList")
	    @ResponseBody
	    public  List<Section>  sectioNameList(String name) {
	    	Section  se = new  Section();
	    	se.setName(name);
	    	se.setType(2);
		    List<Section> sectionlist = sectionService.selectByExample(se);
		    if(sectionlist.size() > 0) {
		    	//查询全部科室
		    	se.setName(null);
		    	se.setPid(sectionlist.get(0).getPid());
		    	se.setType(2);
		    	sectionlist = sectionService.selectByExample(se);
		    }
	        return sectionlist;
	    }
	    /**
	     *  坐诊时间yuyue
	     */
	    @RequestMapping("/doctorTimePage")
	    public String doctorTimePage(Integer id,Model model) {
	       if(id !=  null) {
	    	   Doctor doctor = doctorService.selectByPrimaryKey(id);
	    	   model.addAttribute("doctor",doctor);
	       }
	        return  "patient/doctorTime";
	    }

	    /**
		 *医生列表查询
		 */
	    @RequestMapping(value = "/doctorList")
	    @ResponseBody
	    public  List<Doctor>  doctorList(Integer sid) {
	       Doctor doctor = new Doctor();
	       doctor.setSid(sid);
	       List<Doctor> selectDoctor = doctorService.selectDoctor(doctor);
	       return selectDoctor;
	    }  
	    
    
      /**
  		 *医生列表查询
  		 */
  	    @RequestMapping(value = "/doctorLike")
  	    @ResponseBody
  	    public  List<Doctor>  doctorLike(String name) {
  	       Doctor doctor = new Doctor();
  	       doctor.setName(name);
  	       
  	       List<Doctor> selectDoctor = doctorService.selectDoctor(doctor);
  	       return selectDoctor;
  	    }  
    
	    
	    /**
		 *科室查询
		 */
	    @RequestMapping(value = "/doctorIdList")
	    @ResponseBody
	    public  Section  doctorIdList(Integer sid) {
	       Section selectByPrimaryKey = sectionService.selectByPrimaryKey(sid);
	       
	       return selectByPrimaryKey;
	    }  
	    
	    
    
       /**
  		 *医生列表查询
     * @throws ParseException 
  		 */
  	    @RequestMapping(value = "/doctortimeSelect")
  	    @ResponseBody
  	    public  List<Doctor>  doctortimeSelect(@RequestParam("datetimei")String datetimei,@RequestParam("id")Integer id) throws ParseException {
  	       Doctor doctor = new Doctor();
  	       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  	       doctor.setSid(id);
  	       doctor.setBegindate(simpleDateFormat.parse(datetimei));
  	       List<Doctor> selectDoctor = doctorService.selectTime(doctor);
  	       return selectDoctor;
  	    }  
  	  

       /**
 		 *医生列表查询
         * @throws ParseException 
 	     */
 	    @RequestMapping(value = "/doctorGeRenList")
 	    @ResponseBody
 	    public  Doctor  doctorGeRenList(Integer id) throws ParseException {
 	       Doctor doctor = doctorService.selectByPrimaryKey(id);
 	       return doctor;
 	    }  
  	    
 	    /**
		   *时间格式转换
		 */
	    @RequestMapping(value = "/doctorYuyueTime")
	    @ResponseBody
	    public  Map<String,String>  doctorYuyueTime(Integer id) {
            Map<String,String> map =  new HashMap<String,String>();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); 
	 	    Doctor doctor = doctorService.selectByPrimaryKey(id);
	 	    map.put("begin",sdf.format(doctor.getBegintime()));
		    map.put("end",sdf.format(doctor.getEndtime()));  
	        return  map;
	    }
	    
	    /**
			   *时间格式转换
	     * @throws ParseException 
			 */
		    @RequestMapping(value = "/timeZhuan")
		    @ResponseBody
		    public  String  timeZhuan(String time) throws ParseException {
		    	
		    	  Date parse = new Date();
		    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		    	  if(time != null) {
		    		  parse = sdf.parse(time);
		    	  }
		     	 
		          return   sdf.format(parse);
		    }
		    
	    
	    
	     
	    /**
		   *添加患者信息
		 */
	    @RequestMapping(value = "/loginByPatient")
	    public  String  loginByPatient(@RequestBody Patient patient) {
	        return  "loginByPatient";
	    }
	    
	    /**
	     *添加患者信息
	     */
	    @RequestMapping(value = "/patientSave")
	    public  String  patientSave(Patient patient) {
	    	patientService.insertSelective(patient);
	    	return  "loginByPatient";
	    }
	    
	    /**
	     * 判断患者账号
	     */
	    @RequestMapping("/panzhanghao")
	    @ResponseBody
	    public Map<String,String> panzhanghao(Model model, String zhanghao) {
	    	 Map<String, String> map =  new HashMap<String, String>();
			  PatientExample se  = new  PatientExample();
			  PatientExample.Criteria criteria = se.createCriteria();
			  criteria.andUsernameEqualTo(zhanghao);
           List<Patient> selectByExample = patientService.selectByExample(se);
           if(selectByExample.size() > 0){
               map.put("pan","err");
           }else{
               map.put("pan","ok");
           }
          return    map;
	    }
	    /**
	     *  患者注册界面
	    */
	   @RequestMapping("/patientAddPage")
	   public String  patientAddPage(Model model) {
		 	  return    "patientRegister";
	   }
	   
	    /**
		   *患者信息列表
		 */
	    @RequestMapping(value = "/patientList")
	    @ResponseBody
	    public  List<Patient>   patientList(Integer pid,HttpServletRequest request) {
	    	Patient pa = new Patient();
	    	pa.setPid(pid);
	    	List<Patient> selectPatient = patientService.selectPatient(pa);
	    	
	        return  selectPatient;
	    }
	    /**
         *患者信息列表
      */
     @RequestMapping("/patientList2")
     public String messageList2(Model model, Patient patient, @RequestParam(value="page",defaultValue="1")Integer page,HttpServletRequest request) {
     	if(patient == null) {
     		patient = new Patient();
     	}
        HttpSession session = request.getSession();
        Patient       patient1   =  (Patient) session.getAttribute("PATIENT");
         if(patient1 == null){
        	  return  "redirect:/login/font/index";
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
//         messages.setTime(new Date());
         messages.setType(1);
         messages.setUid(patient1.getPid());   
         PageInfo<Messages> pageInfo = messagesService.selectMessagesList(messages, 1, size);
         model.addAttribute("doctorList",pageInfo.getList());
         model.addAttribute("pageInfo",pageInfo);
         model.addAttribute("patient",patient);
         return    "patient/patientList";
     }
	    /**
		   *患者信息列表
		 */
	    @RequestMapping(value = "/patienDel")
	    @ResponseBody
	    public  List<Patient>   patienDel(Integer id) {
	    	if(id != null) {
	    		patientService.deleteByPrimaryKey(id);
	    	}
	    	List<Patient> selectByExample = patientService.selectByExample(null);
	        return  selectByExample;
	    }
	    
	    /**
		   *患者信息查看
		 */
	    @RequestMapping(value = "/patientUpatePage")
	    @ResponseBody
	    public  Patient  patientUpatePage(Integer id) {
	    	 Patient patient = null;
	         if(id != null) {
	         patient = patientService.selectByPrimaryKey(id);
	        	 
	         }
	        return  patient;
	    }
	    
	    
	    /**
		   *患者信息修改
		 */
	    @RequestMapping(value = "/patientUpdate")
	    @ResponseBody
	    public  Patient  patientUpdate(@RequestBody Patient patient) {
	    	patientService.updateByPrimaryKeySelective(patient);
	        return  null;
	    }
	    
	    
	    
	    /**
		   *预约信息
	     * @throws ParseException 
		 */
	    @RequestMapping(value = "/messagesSave")
	    public  String  messagesSave(Messages patient,HttpServletRequest request) throws ParseException {
	    	 HttpSession session =  request.getSession();
	         Patient       patient1   =  (Patient) session.getAttribute("PATIENT");
	    	    Messages  hui = null;
	    	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	    Date shijian = simpleDateFormat.parse(patient.getSname());
	  	        patient.setTime(shijian);
	  	        patient.setType(1);//待预约
	  	       
	  	        Doctor doctor = doctorService.selectByPrimaryKey(patient.getDid());//医生
	  	        if(doctor != null) {
	  	        	patient.setDname(doctor.getName());
	  	        	if(doctor.getYipeoples() == null) {
	  	        		doctor.setYipeoples(0);
	  	        	}
	  	        	doctor.setYipeoples(doctor.getYipeoples()+1);
	  	        	doctorService.updateByPrimaryKeySelective(doctor);
	  	        	
	  	        }
	  	        Section section = sectionService.selectByPrimaryKey(patient.getSid());//科室
	  	        if(section != null) {
	  	        	patient.setSname(section.getName());
	  	        }
	  	        
	  	        Patient pa = patientService.selectByPrimaryKey(patient1.getId()); //患者
	  	        if(pa != null) {
	  	        	patient.setUid(pa.getPid());
	  	        	patient.setUserid(pa.getId());
	  	        	patient.setPhone(pa.getPhone()); 
	  	        	patient.setUsername(pa.getUsername());
	  	        	patient.setAge(pa.getAge());
	  	        	int countByExample = messagesService.countByExample(null);
	  	        	patient.setBianhao(countByExample+1);
	  	        	//排序
	  	        	Messages message = new Messages();
//	  	        	message.setUid(patient.getUid());
	  		    	message.setTime(patient.getTime());
	  		    	message.setDid(patient.getDid());
	  		    	message.setType(-1);
	  		    	List<Messages>  list  = messagesService.selectMessages(message);
	  		    	if(list.size() <= 0) {
	  		    		patient.setPai(1);
	  		    	}else {
	  		    		patient.setPai(list.size()+1);
	  		    	}
	  	        }
		    	messagesService.insertSelective(patient);
		    	if(patient.getId() != null) {
		    		hui	= messagesService.selectByPrimaryKey(patient.getId());
		    		Messages xin = new Messages();
		    		xin.setDid(hui.getDid());
		    		xin.setType(1);
		    		xin.setTime(shijian);
		    		List<Messages> selectMessagesPai = messagesService.selectMessagesPai(xin);
		    		hui.setAge(selectMessagesPai.size());
		    		
		    	}
		    	 return "redirect:/api/doctorList1";
	    }
	    
	    
	    
	    /**
		   *取消预约
	     * @throws ParseException 
		 */
	    @RequestMapping(value = "/messagesQuXiao")
	    public  String  messagesQuXiao(Integer id) throws ParseException {
	    	Messages ma  = new  Messages();
	    	ma.setId(id);
	    	ma.setType(2); //取消预约
	    	messagesService.updateByPrimaryKeySelective(ma);
	    	Messages mes = messagesService.selectByPrimaryKey(id);
	    	Messages messages  =  new  Messages();
	    	messages.setType(1);
	    	messages.setUid(mes.getUid());
	    	messages.setTime(new Date());
	    	List<Messages>  list  = messagesService.selectMessages(messages);
	    	return "redirect:/api/patientList2";
	    }
	    
	    /**
		   *预约信息列表
	     * @throws ParseException 
		 */
	    @RequestMapping(value = "/messagesUidList")
	    @ResponseBody
	    public  List<Messages>  messagesUidList(@RequestBody Messages message) throws ParseException {
	    	List<Messages>  list = null;
	    	if(message.getType() != null && message.getType() == 1) {
	    			message.setTime(new Date());
	    		    list  = messagesService.selectMessagesPai(message);
	    	}else {
	    		  list  = messagesService.selectMessagesTWO(message);  
	    	}
	    	  Messages me  = new  Messages();
		      me.setType(1);
		      me.setTime(new Date());
			  for (int i = 0; i < list.size(); i++) {
				  me.setDid(list.get(i).getDid());
				  List<Messages> lin = messagesService.selectMessagesPai(me);
				  list.get(i).setAge(lin.size());
				  
	           }
		    return  list;
	    }
	    /**
		   *预约信息列表
	     * @throws ParseException 
		 */
	    @RequestMapping(value = "/messagesList")
	    @ResponseBody
	    public  List<Messages>  messagesList(@RequestParam("type")Integer type,@RequestParam("uid")Integer uid) throws ParseException {
	    	Messages message = new Messages();
	    	List<Messages>  list = null;
	    	message.setType(type);
	    	message.setUid(uid);
	    	if(type != null && type == 1) {
	    		  message.setTime(new Date());
	    		  list  = messagesService.selectMessagesPai(message);  
	    		  Messages me  = new  Messages();
    		      me.setType(1);
    		      me.setTime(new Date());
	    		  for (int i = 0; i < list.size(); i++) {
    				  me.setDid(list.get(i).getDid());
    				  List<Messages> lin = messagesService.selectMessagesPai(me);
    				   list.get(i).setAge(lin.size());
		           }
	    	}else {
	    		  list  = messagesService.selectMessagesTWO(message);  
	    	}
	      
	    	
	    	
		    return  list;
	    }
	    
	    /**
		   *预约信息列表
	     * @throws ParseException 
		 */
	    @RequestMapping(value = "/messagesLists")
	    @ResponseBody
	    public  List<Messages>  messagesLists(Integer uid) throws ParseException {
	    	Messages message = new Messages();
	    	message.setUid(uid);
	    	List<Messages>  list  = messagesService.selectMessagesTWO(message);
	    	  Messages me  = new  Messages();
		      me.setType(1);
		      me.setTime(new Date());
			  for (int i = 0; i < list.size(); i++) {
				  if(list.get(i).getType() == 1) {
					   me.setDid(list.get(i).getDid());
					   List<Messages> lin = messagesService.selectMessagesPai(me);
					   list.get(i).setAge(lin.size()); 
				  }
	           }
	    	return  list;
	    }
	    
	    
	    
	    
	    /** 
	    * @throws ParseException 
		 */
	    @RequestMapping(value = "/doctortouList")
	    @ResponseBody
	    public  List<Doctor>  doctortouList() {
	      	PageInfo<Doctor> pageInfo  =  doctorService.selectDoctorList(null,1,4);
	
		    return   pageInfo.getList();
	    }
	    
	    
	    /** 
	    * @throws ParseException 
		 */
	    @RequestMapping(value = "/datatimeGua")
	    @ResponseBody
	    public  Integer  datatimeGua(@RequestParam("datetime")String datetime,@RequestParam("did")Integer did) throws ParseException {
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	    Date parse = sdf.parse(datetime);
        	Messages message = new Messages();
	    	message.setTime(parse);
	    	message.setDid(did);
	    	message.setType(-1);
	    	List<Messages>  list  = messagesService.selectMessages(message);
		    return  list.size();
	    }
	    
}
