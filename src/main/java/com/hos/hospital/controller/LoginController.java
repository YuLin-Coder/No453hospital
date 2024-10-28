package com.hos.hospital.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.DoctorExample;
import com.hos.hospital.entity.Patient;
import com.hos.hospital.entity.Section;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.DoctorService;
import com.hos.hospital.service.PatientService;
import com.hos.hospital.service.SectionService;
import com.hos.hospital.utils.StringRandom;


/**
 * 登录控制层
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
     @Autowired
     private AdminService adminService;

     @Autowired
     private DoctorService doctorService;
     
     @Autowired
     private SectionService sectionService;

     
     
     @Autowired
     private   PatientService  patientService;
     
     
     @Value("${fileUrl}") //在配置文件中获取文件的保存路径
     private String filePath;

     
     
		/**
	          * 后台登陆界面
		 * @throws IOException 
	     */
	    @RequestMapping("/afterView")
	    public String afterLogin(Integer type,Model model) {
	        if(type == null) {
	            type = 1;
	        }
	        model.addAttribute("type",type);
	        return "login";
	    }

	    
	 /**
	         * 后台登陆界面
	    */
	   @RequestMapping("/index")
	   public String index(Integer type,Model model) {
	       if(type == null){
	           type = 1;
	       }
	       model.addAttribute("type",type);
	       return "login";
	   }
	   
		 /**
        * 后台登陆界面
   */
  @RequestMapping("/font/index")
  public String fontIndex(Integer type,Model model) {
      if(type == null){
          type = 3;
      }
      model.addAttribute("type",type);
      return "loginByPatient";
  }

/*	   public static void main(String[] args) {
		    String filename ="C:\\Users\\Administrator\\Pictures\\项目图片\\1156.jpg_wh1200.jpg";
		    int indexOf = filename.indexOf(".");
		    String substring = filename.substring(indexOf); 
		    System.out.println(substring);
	}*/
	   /**
	    * 医生图片上传
	    * @param mufile
	    * @param id
	    * @return
	    * @throws IOException
	    */
	   @RequestMapping(value ="/zixunAdd")
	   @ResponseBody
	   public  Map<String, Object>  zixunAdd(@RequestParam("mf")MultipartFile mufile,@RequestParam("id")Integer  id) throws IOException{
	       Map<String, Object> map =  new HashMap<String, Object>();
	       String random   =  StringRandom.getRandom();
	       String filename =  mufile.getOriginalFilename();
	       //随机字符+原图片名用作新的图片名
	       filename = random+".jpg";
	       try {
	           //文件保存路径  D:/xxxx/xxxx/
	           File file = new File(filePath+filename);
	           //判断父级文件是否存在
	           if (!file.getParentFile().exists()) {
	               file.getParentFile().mkdir();
	           }
	           mufile.transferTo(file);
	       } catch (IllegalStateException | IOException e) {
	           e.printStackTrace();
	       }
	       Doctor doctor = new Doctor(); 
	       if(id != -1){
	    	   doctor.setId(id);
	    	   doctor.setImg("/files/"+filename);
	       	doctorService.updateByPrimaryKeySelective(doctor);
	       }else {
	           //添加图片路径
	       	doctor.setImg("/files/"+filename);
	       	doctorService.insertSelective(doctor);
	           System.out.println("id:"+doctor.getId());
	           map.put("id",doctor.getId());
	       }
	       return map;
	   }
	   
	  
	   /**
	     * 判断管理员账号
	     */
	    @RequestMapping("/sectionxList")
	    @ResponseBody
	    public List<Section> sectionxList(Model model, Integer id) {
	    	  List<Section> selectByExample = null;
		      if(id != null) {
		    		  Section section = new Section();
			    	  section.setPid(id);
			    	  selectByExample = sectionService.selectByExample(section);
	    	   }
	    	   return    selectByExample;
	    }   
	   
	    /**
	     * 判断管理员账号
	     */
	    @RequestMapping("/mimaUpate")
	    @ResponseBody
	    public Map<String,String> passwordUpate(Model model, String zhanghao) {
	            Map<String, String> map =  new HashMap<String, String>();
	            Admin ad = new Admin();
	            ad.setUsername(zhanghao);
	            List<Admin> selectAdmin = adminService.selectAdmin(ad);
	            if(selectAdmin.size() > 0){
	                map.put("pan","err");
	            }else{
	                map.put("pan","ok");
	            }
	           return    map;
	    }

	    /**
	     * 判断医生账号
	     */
	    @RequestMapping("/panzhanghao")
	    @ResponseBody
	    public Map<String,String> panzhanghao(Model model, String zhanghao) {
	            Map<String, String> map =  new HashMap<String, String>();
	        	DoctorExample se  = new  DoctorExample();
	    		DoctorExample.Criteria criteria = se.createCriteria();
	    		criteria.andUsernameEqualTo(zhanghao);
	            List<Doctor> selectByExample = doctorService.selectByExample(se);
	            if(selectByExample.size() > 0){
	                map.put("pan","err");
	            }else{
	                map.put("pan","ok");
	            }
	           return    map;
	    }
	    
	   
	
	   /**
	      * 医生添加
	    * @param model
	    * @param zixun
	    * @return
	    */
	   @RequestMapping("/zixunInsert")
	   public String zixunInsert(Model model,Doctor doctor){
	       if(doctor.getId() !=  null){
	    	   if(doctor.getSid() != null) {
	    		   Section selectByPrimaryKey = sectionService.selectByPrimaryKey(doctor.getSid());
	    		   doctor.setSname(selectByPrimaryKey.getName());
	    	   }
	           doctorService.updateByPrimaryKeySelective(doctor);
	       }
	        model.addAttribute("type",1);
	        return "login";
	   }
		    
		
	    /**
	        *  管理员注册界面
	     */
	    @RequestMapping("/mimaPageUptate")
	    public String  mimaPageUptate(Integer type,Model model) {
	    	//1医生  2 管理员
	    	if(type == 1 ) {
	    		  return "doctorRegister";
	    	}
	  	  return "adminRegister";
	    }
	    
	    /**
	      *  医生注册界面
	     */
	    @RequestMapping("/doctorRegisterPage")
	    public String  doctorRegister(Model model) {
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
	     model.addAttribute("sectionlist", sectionlist);
	     model.addAttribute("sectionlist2", sectionlist2);
	  	  return "doctorRegister";
	    }
	    
	    
	    
	    /**
	       * 管理员注册
	     */
	   @RequestMapping("/admin_Register")
	   public String admin_Register(Admin admin,Model model) {
		   int insertSelective = adminService.insertSelective(admin);
		   model.addAttribute("type",2);
	       return "login";
	   }
		
	   
	   
	   
	   
	   
	   /**
	     * 登陆验证
	     * @return
	     */
	    @RequestMapping("/verificatio")
	    public String verificatio(String username, String password, Integer type, HttpServletRequest request,Model model) {
	        HttpSession session = request.getSession();
	        session.setAttribute("type",type);
	        //类型为1是医院 2是管理员
	         if(type == 1){
	        	 Doctor doctor = new  Doctor();
	        	 doctor.setUsername(username);
	        	 doctor.setPasswoed(password);
	        	 List<Doctor> doctorlist = doctorService.selectDoctor(doctor);
	             if(doctorlist.size() <= 0){
	                 model.addAttribute("message","密码错误");
	                 model.addAttribute("type",type);
	                 return "login";
	             }
	             session.setAttribute("DOCTOR",doctorlist.get(0));
	             return "redirect:/doctor/index";
	         }
	         if(type == 3){
	        	 Patient patient = new  Patient();
	        	 patient.setUsername(username);
	        	 patient.setPassword(password);
	        	 List<Patient>  list = patientService.selectPatient(patient);
	   	    	if(list.size() <= 0) {
	   	    	 model.addAttribute("message","密码错误");
        		 model.addAttribute("type",type);
        		 return "loginByPatient";
	   	    	}
	        	 session.setAttribute("PATIENT",list.get(0));
	        	 return "redirect:/api/doctorList1";
	         }
	         
	         
	        Admin admin = new Admin();
	        admin.setUsername(username);
	        admin.setPassword(password);
	        List<Admin> adminlist  = adminService.selectAdmin(admin);
	        if(adminlist.size() <= 0){
	            model.addAttribute("message","密码错误");
	            model.addAttribute("type",type);
	            return "login";
	        }
	        session.setAttribute("ADMIN",adminlist.get(0));
	        return "redirect:/admin/index";
	    }
	   
	    /**
	     * 退出登录
	     * @param request
	     * @return
	     */
	    @RequestMapping("/sessionInvalidate")
	    public String boot(HttpServletRequest request,Model model) {
	        HttpSession session = request.getSession();
	        Integer type = (Integer) session.getAttribute("type");
	        if(type == null){
	            type=1;
	        }
	        
	        if(type == 3){
	        	model.addAttribute("type",type);
		        session.invalidate();   //session销毁
	        	 return "loginByPatient";
	        }
	        
	        model.addAttribute("type",type);
	        session.invalidate();   //session销毁
	        return "login";
	    }
	   
		    

/*
    *//**
     * 跳转忘记密码界面
     *//*
    @RequestMapping("/mimaPageUptate")
    public String  passwordUpate() {
        return "behind/merchant/mibaoUptate";
    }

    *//**
     * 修改密码
     *//*
    @RequestMapping("/mimaUpate")
    @ResponseBody
    public Map<String,String> passwordUpate(Model model, String  mima, String  mibao, String zhanghao) {
            Map<String, String> map =  new HashMap<String, String>();
            Merchant me = new Merchant();
            me.setZhanghao(zhanghao);
            me.setMibao(mibao);
            List<Merchant> list = merchantService.selectMerchant(me);
            if(list.size() > 0){
                Merchant me2 = new Merchant();
                me2.setId(list.get(0).getId());
                me2.setMima(mima);
                merchantService.updateByPrimaryKeySelective(me2);
                map.put("pan","ok");
            }else{
                map.put("pan","err");
            }
           return    map;
    }





    *//**
     * 后台登陆界面
     * @return
     *//*
    @RequestMapping("/afterView")
    public String afterLogin(Integer type,Model model) {
        if(type == null){
            type = 1;
        }
        model.addAttribute("type",type);
        return "behind/login";
    }
    *//**
     * 登陆验证
     * @return
     *//*
    @RequestMapping("/verificatio")
    public String signin(String username, String password, Integer type, HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("type",type);
        //类型为1是商户后台 2是管理员
         if(type == 1){
             Merchant merchant = new Merchant();
             merchant.setZhanghao(username);
             merchant.setMima(password);
             merchant.setState(1);
             List<Merchant> merchants = merchantService.selectMerchant(merchant);
             if(merchants.size() <= 0){
                 model.addAttribute("message","密码错误");
                 model.addAttribute("type",type);
                 return "behind/login";
             }
             session.setAttribute("MERCHANT",merchants.get(0));
             return "redirect:/merchant/index";
         }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        List<Admin> adminlist  = adminService.selectAdmin(admin);
        if(adminlist.size() <= 0){
            model.addAttribute("message","密码错误");
            model.addAttribute("type",type);
            return "behind/login";
        }
        session.setAttribute("ADMIN",adminlist.get(0));
        return "redirect:/admin/index";
    }

    *//**
     * 退出登录
     * @param request
     * @return
     *//*
    @RequestMapping("/sessionInvalidate")
    public String boot(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        Integer type = (Integer) session.getAttribute("type");
        if(type == null){
            type=1;
        }
        model.addAttribute("type",type);
        session.invalidate();   //session销毁
        return "behind/login";
    }


    *//**
     *  管理员修改密码界面
     * @return
     *//*
    @RequestMapping("/adminUptatePage")
    public String adminUptatePage(Model model) {

        return "behind/admin/adminUptate";
    }


    *//**
     *  商户修改密码界面
     * @return
     *//*
    @RequestMapping("/merchantUptate")
    public String merchantUptate(Model model) {
        return "behind/merchant/merchantUptate";
    }
*/

}
