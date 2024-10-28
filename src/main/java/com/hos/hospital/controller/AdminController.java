package com.hos.hospital.controller;


import com.github.pagehelper.PageInfo;
import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.Banners;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.Section;
import com.hos.hospital.service.AdminService;
import com.hos.hospital.service.BannersService;
import com.hos.hospital.service.DoctorService;
import com.hos.hospital.service.MessagesService;
import com.hos.hospital.service.PatientService;
import com.hos.hospital.service.SectionService;
import com.hos.hospital.utils.ExcelInput;
import com.hos.hospital.utils.ExcelUtils;
import com.hos.hospital.utils.StringRandom;

import org.apache.ibatis.jdbc.Null;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 后端管理员控制层
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
  
    @Autowired
    private SectionService sectionService;
    
    @Autowired
    private BannersService  bannersService;   
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private  PatientService  patientService;
    
    @Autowired
    private   MessagesService  messagesService;
    
    
    private Integer size  = 6;//每页显示数量
    

    @Value("${fileUrl}") //在配置文件中获取文件的保存路径
    private String filePath;

    
    /**
     * 导入
     * @param file
     * @param response
     * @throws IOException 
     */
    
    @RequestMapping("/excelInput")
    public String  excelInput(MultipartFile file,HttpServletResponse response) throws IOException {
    	String sb = file.getOriginalFilename();
        List<String[]> jieExcel = ExcelInput.jieExcel(file.getInputStream(), sb.substring(sb.indexOf(".")+1));
	
		  for (String[] strings : jieExcel) {
			  System.out.println(Arrays.toString(strings));  
		  }
        return  "redirect:/admin/index";
    }
    
    /**
     	* 导出
     * 
     * @param file
     * @param response
     */
    @RequestMapping("/xiazai")
    public void  excelString(HttpServletRequest request,HttpServletResponse response) {
        try {
        	response.setCharacterEncoding("utf-8");
        	//content-type类型是告诉页面要响应内容的类型，以及字符编码，页面要以什么方式打开
        	response.setContentType("application/force-download");// 设置强制下载不打开
            //Content-Disposition是MIMI协议的扩展，浏览器以什么方式处理wenjian
            response.setHeader("Content-Disposition", "attachment; fileName=exportFile.xlsx");

        	String[] title = new String[]{"姓名","科室id","科室","日期"};
            List<Doctor> list = doctorService.selectByExample(null);
            Workbook   wo     = ExcelUtils.getExcel("xlsx",title,list);
            wo.write(response.getOutputStream());
    		//Files.copy(file, response.getOutputStream());   		
    	} catch (IOException e) {
    		System.out.println("发生异常");
    		e.printStackTrace();
    	}
    
    }
    

    
    
    @RequestMapping("/index")
    public String index(Model model) {
        int  doctor  = doctorService.countByExample(null); //医生总数
        int  section = sectionService.countByExample(null); //科室总数
        int  patient = patientService.countByExample(null); //患者总数
        int  messages = messagesService.countByExample(null); //预约总数
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
        return  "admin/index";
    }
 
     


	    /**
	     *  管理员修改密码界面
	     * @return
	     */
    @RequestMapping("/adminUptatePage")
    public String adminUptatePage(Model model) {
        return "admin/adminUptate";
    }
    /**
     *  修改密码 
     */
    @RequestMapping("/adminUptatePassword")
    public String adminUptatePassword(Model model,Admin admin,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin ad = (Admin) session.getAttribute("ADMIN");
        if(ad != null && admin.getPassword() != null){
                admin.setId(ad.getId());
                adminService.updateByPrimaryKeySelective(admin);
        }
        return  "redirect:/admin/index";
    }
    
    
    /**
     *  坐诊时间设置界面
     */
    @RequestMapping("/doctorTimePage")
    public String doctorTimePage(Integer id,Model model) {
       if(id !=  null) {
    	   Doctor doctor = doctorService.selectByPrimaryKey(id);
    	   model.addAttribute("doctor",doctor);
       }
        return  "admin/doctorTime";
    }
    
    /**
     *  坐诊时间设置界面
     * @throws ParseException 
     */
    @RequestMapping("/doctorTimeUpdate")
    public String doctorTimeUpdate(Integer id,Model model,String begindate,String enddate,String begintime,String endtime) throws ParseException {
       if(id !=  null) {
    	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	  SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm"); 
    	   Doctor   doctor = new Doctor();
    	   doctor.setId(id);
    	   doctor.setBegindate(simpleDateFormat.parse(begindate)); 
    	   doctor.setEnddate(simpleDateFormat.parse(enddate));
           doctor.setBegintime(simpleDateFormat2.parse(begintime));
    	   doctor.setEndtime(simpleDateFormat2.parse(endtime));    
    	   doctorService.updateByPrimaryKeySelective(doctor);
       }
        return "redirect:/admin/doctorList";
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
        return  "redirect:/admin/doctorList";
    }
    
    /**
     *  删除医生信息
     */
    @RequestMapping("/doctorDelect")
    public String doctorDelect(Integer id,Model model) {
       if(id !=  null) {
    	
    	   doctorService.deleteByPrimaryKey(id);
       }
        return  "redirect:/admin/doctorList";
    }
    
    
    /**
     *  医生注册界面
    */
   @RequestMapping("/doctorAddPage")
   public String  doctorAddPage(Model model) {
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
	 	  return    "admin/doctorAdd";
   }
   
    
    
    @RequestMapping("/admindoctorAdd")
    public String admindoctorAdd(Doctor doctor,Model model) {
    	  if(doctor.getId() !=  null){
    	 	   if(doctor.getSid() != null) {
    			   Section selectByPrimaryKey = sectionService.selectByPrimaryKey(doctor.getSid());
    			   doctor.setSname(selectByPrimaryKey.getName());
    		   }
    	       doctorService.updateByPrimaryKeySelective(doctor);
    	   }
        return  "redirect:/admin/doctorList";
    }
    
    
    /**
     * 医生列表
     */
    @RequestMapping("/doctorList")
    public String doctorList(Model model, Doctor doctor, @RequestParam(value="page",defaultValue="1")Integer page) {
    	if(doctor == null) {
    		doctor = new Doctor();
    	}
    	PageInfo<Doctor> pageInfo  =  doctorService.selectDoctorList(doctor,page,size);
    	
    	List<Doctor> list = pageInfo.getList();
        model.addAttribute("doctorList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("doctor",doctor);
        return    "admin/doctorList";
    }
    
    
    /**
     *  修改医生信息界面
     * @return
     */
	@RequestMapping("/doctorUptatePage")
	public String doctorUptatePage(Model model,Integer id) {
		if(id != null) {
			Doctor  doctor = doctorService.selectByPrimaryKey(id);
			List<Section> sectionlist2  = null;
			model.addAttribute("doctor",doctor);
			//科室
	    	Section  se = new  Section();
	    	se.setType(1);
		    List<Section> sectionlist = sectionService.selectByExample(se);
		    model.addAttribute("sectionlist", sectionlist);
	    	//科室详情
		 	Section  se1 = sectionService.selectByPrimaryKey(doctor.getSid());
			Section  section = new  Section();
			 if(se1 != null) {
			    	section.setPid(se1.getPid());
			    	section.setType(2);
			    	sectionlist2 = sectionService.selectByExample(section);
			 }else {
				 if(sectionlist.size() >0 ) {
	    			 section.setPid(sectionlist.get(0).getId());	
	    			 section.setType(2);
				     sectionlist2 = sectionService.selectByExample(section);
	    		 }
				 se1 = new   Section();
			 }
		    model.addAttribute("sectionlist2", sectionlist2);
		    model.addAttribute("se1", se1);
		    
		}
	    return "admin/doctorUptate";
	}
	    
    /**
     * 科室列表
     */
    @RequestMapping("/sectionList")
    public String sectionList(Model model, Section section, @RequestParam(value="page",defaultValue="1")Integer page) {
    	if(section == null) {
    		section = new Section();
    	}
    	section.setType(1);//1 科室
    	PageInfo<Section> pageInfo   = sectionService.selectSectionListt(section,page,size);
    	List<Section> list = pageInfo.getList();
    	List<Section> list2 = new ArrayList<Section>();
    	Section cs = new Section();
    	for (Section se : list) {
    		cs.setPid(se.getId());
    		List<Section> selectByExample = sectionService.selectByExample(cs);
    		se.setSlist(selectByExample);
    		list2.add(se);
		
    	}
    	
    	
    	
        model.addAttribute("sectionList",list2);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("section",section);
        return    "admin/sectionList";
    }
    
    /**
     * 科室详情下级列表
     */
    @RequestMapping("/sectionBelowList")
    public String sectionBelowList(Model model, Section section, Integer id) {
    	if(section == null) {
    		section = new Section();
    	}
    	section.setType(2);// 2 科室详情
    	section.setPid(id);
    	Section se = sectionService.selectByPrimaryKey(id);
    	List<Section> list  = sectionService.selectByExample(section);
        model.addAttribute("sectionList",list);
        model.addAttribute("section",section);
        model.addAttribute("se",se);
        return    "admin/sectionBelow";
    }
    
    
   /**
      * 跳转添加科室界面
    */
    @RequestMapping("/sectionAddPage")
    public String zuopinList() {
        return  "admin/sectionAdd";
    }

    /**
     * 跳转添加科室下级界面
   */
   @RequestMapping("/sectionAddBelowPage")
   public String zuopinList(Model model,Integer id) {
	   if(id != null) {
			  Section se = sectionService.selectByPrimaryKey(id);
		      model.addAttribute("se",se);
	   }
       return  "admin/sectionAddBelow";
   }
   
   
   
   /**
    * 跳转修改科室下级界面
  */
  @RequestMapping("/sectionBelowUptatePage")
  public String sectionBelowUptatePage(Model model,Integer id) {
	   if(id != null) {
			  Section se = sectionService.selectByPrimaryKey(id);
			  Section section = sectionService.selectByPrimaryKey(se.getPid());
		      model.addAttribute("se",se);
		      model.addAttribute("sname",section.getName());
	   }
      return  "admin/sectionBelowUptate";
  }
   /**
    * 跳转修改科室界面
  */
  @RequestMapping("/sectionUptatePage")
  public String sectionUptatePage(Model model,Integer id) {
	   if(id != null) {
			  Section se = sectionService.selectByPrimaryKey(id);
			  model.addAttribute("se",se);
	   }
      return  "admin/sectionUptate";
  }
  
    
    /**
     * 添加科室
     */
    @RequestMapping("/sectionAdd")
    @ResponseBody
    public Map<String,String> sectionAdd(String name) {
            Map<String, String> map =  new HashMap<String, String>();
            if(name != null ){
            	Section section = new Section();
            	section.setName(name);
            	section.setType(1);
            	sectionService.insertSelective(section);
                map.put("pan","ok");
            }else{
                map.put("pan","err");
            }
           return    map;
    }


    /**
     * 添加科室下级
     */
    @RequestMapping("/sectionAddBelow")
    public String sectionAddBelow(Section section) {
            	section.setType(2);
            	sectionService.insertSelective(section);
            	//"redirect:/admin/sectionBelowList?id="+section.getPid();
          return "redirect:/admin/sectionList";

    }
    
    /**
     * 修改科室
     */
    @RequestMapping("/sectionUptate")
    public String sectionUptate(Section section) {
          sectionService.updateByPrimaryKeySelective(section);
          return  "redirect:/admin/sectionList";

    }
    /**
     * 修改科室下级
     */
    @RequestMapping("/sectionBelowUptate")
    public String sectionBelowUptate(Section section) {
          sectionService.updateByPrimaryKeySelective(section);
          return "redirect:/admin/sectionBelowList?id="+section.getPid();

    }
    /**
     * 删除科室下级
     */
    @RequestMapping("/sectionBelowDelect")
    public String sectionBelowUptate(Integer id) {
	    	Section section = sectionService.selectByPrimaryKey(id);
	    	Integer pid =  section.getPid();
            sectionService.deleteByPrimaryKey(section.getId());
            return "redirect:/admin/sectionBelowList?id="+pid;

    }
    
    /**
     * 删除科室
     */
    @RequestMapping("/sectionDelect")
    public String sectionDelect(Integer id) {
    	    Section section  = new Section();
    	    section.setPid(id);
    	    section.setType(2);
	    	List<Section> list = sectionService.selectByExample(section);
            sectionService.deleteByPrimaryKey(id);
            for (Section section2 : list) {
            	sectionService.deleteByPrimaryKey(section2.getId());
			}
          return  "redirect:/admin/sectionList";
    }
    
    
	    @RequestMapping("/bannersPageUpdate")
	    public String bannersAdd(Model model,Integer id) {
	        Banners   banners   = null;
	        String[]  imgnames = null;
	        if(id == 1){
	            banners = bannersService.selectByPrimaryKey(1);
	            if(banners == null){
	                banners = new Banners();
	                banners.setId(1);
	                bannersService.insertSelective(banners);
	            }
	        }
	        if(banners.getImg() != null  && !"".equals(banners.getImg())){
	            imgnames  = banners.getImg().split(",");
	        }
	        model.addAttribute("imgnames",imgnames);
	        model.addAttribute("banners",banners);
	        return  "admin/bannersUpdate";
	    }

	   /**
	     *轮播图片删除
	     */
	    @RequestMapping(value ="/bannersDel")
	    @ResponseBody
	    public  Map<String, Object>  bannersDel(Integer id,String  src) throws IOException{
	        Map<String, Object>  map =  new HashMap<String, Object>();
	        StringBuffer sb  = new  StringBuffer();
	        if(id != null && src != null){
	            Banners banner = bannersService.selectByPrimaryKey(id);
	                if(banner.getImg() != null){
	                    String[] split = banner.getImg().split(",");
	                    for(int i = 0; i<split.length;i++){
	                        if(src.equals(split[i])){
	                            //String fp= filePath.substring(filePath.indexOf("/")+1);//文件的真实路径
	                            String path = src.substring(src.indexOf("s") + 2);   //获取文件名
	                            File file = new File(filePath +path);
	                            if(file.exists()){
	                                file.delete();
	                                map.put("massage","删除成功");
	                            }else{
	                                map.put("massage","删除失败");
	                            }
	                        }else{
	                            sb.append(split[i]+",");
	                        }
	                    }
	                }
	        }
	        Banners banners = new Banners();
	        banners.setId(id);
	        banners.setImg(sb.toString());
	        bannersService.updateByPrimaryKeySelective(banners);
	        return map;
	    }
    
	    /**
	      *banner图片上传
	     */
	    @RequestMapping(value ="/bannersAdd")
	    @ResponseBody
	    public  Map<String, Object>  bannersAdd(@RequestParam("mf")MultipartFile[] mufile,@RequestParam("id")Integer  id) throws IOException{
	        Map<String, Object> map =  new HashMap<String, Object>();
	        StringBuffer path       =  new StringBuffer();
	        //图片上传并保存上传的路径
	        for (int i = 0; i < mufile.length; i++) {
	            try {
	                String random   =  StringRandom.getRandom();
	                String filename =  mufile[i].getOriginalFilename();
	                //随机字符+原图片名用作新的图片名
	                filename = random+filename;
	                //文件保存路径  D:/Java/hospital  
	                File file = new File(filePath+filename);
	                //判断父级文件是否存在
	                if (!file.getParentFile().exists()) {
	                    file.getParentFile().mkdir();
	                }
	                path.append("/files/"+filename+",");
	                mufile[i].transferTo(file);
	            } catch (IllegalStateException | IOException e) {
	                e.printStackTrace();
	            }
	        }
	        Banners banners = new  Banners();
	        if(id != null){
	            //修改图片路径
	            Banners  sh  = bannersService.selectByPrimaryKey(id);
	            banners.setId(id);
	            if(sh.getImg() != null ){
	                banners.setImg(sh.getImg()+path.toString());
	            }else{
	                banners.setImg(path.toString());
	            }
	            bannersService.updateByPrimaryKeySelective(banners);
	        }
	        return map;
	    }

    
    
/*
    *//**
     * 管理员-非遗讲堂
     *//*
    @RequestMapping(value="/feiyi_videoList")
    public String feiyi_VideoList(Model model, Video video, @RequestParam(value="page",defaultValue="1")Integer page) {
        PageInfo<Video> pageInfo = videoService.selectPageList(video,page,size);
        model.addAttribute("videoList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        if(video.getTitle() != null){
            model.addAttribute("title",video.getTitle());
        }
        return "behind/admin/feiyi_videoList";
     }
    *//**
     *  非遗讲堂-删除
     *//*
    @RequestMapping("/videoDelete")
    public String videoDelete(Model model,Integer id) {
        if(id !=  null){
            //String fp= filePath.substring(filePath.indexOf("/")+1);//文件的真实路径
            Video video = videoService.selectByPrimaryKey(id);
            String urlsrls = video.getUrls();
            String name = urlsrls.substring(urlsrls.indexOf("s") + 2);  //获取文件名
            File file = new File(filePath +name);
            if(file.exists()){
                file.delete();
            }
            videoService.deleteByPrimaryKey(id);
        }
        return  "redirect:/admin/feiyi_videoList";
    }



    *//**
     * 管理员-人物列表
     *//*
    @RequestMapping("/personList")
    public String personList(Model model,Person person,@RequestParam(value="page",defaultValue="1")Integer page,String sou) {
        PageInfo<Person> pageInfo = personService.selectPageList(person,page,size);
        List<Person> list = pageInfo.getList();
        List<Person> list2 = new ArrayList<Person>();
        //默认显示第一张图片
        for(int i =0; i<list.size();i++){
            Person sh = list.get(i);
            String[] img = sh.getImg().split(",");
            if(img.length > 0){
                sh.setImg(img[0]);
                list.set(i,sh);
            }
        }
        if(sou != null && !"".equals(sou)){
            char sz = sou.charAt(0);
            //判断是否是大写
            if(Character.isUpperCase(sz)){
                sz = StringRandom.toLower(sz); //大写转小写
            }
            for(int i =0; i<list.size();i++){
                Person sh = list.get(i);
                if(sh.getName() != null){
                    char names = StringRandom.getPinYinHeadChar(sh.getName()); //名字的首字母
                    if(names == sz){
                        list2.add(sh);
                    }
                }
            }
            model.addAttribute("personList",list2);
        }else{
            model.addAttribute("personList",list);
        }
        model.addAttribute("sou",sou);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("person",person);
        return  "behind/admin/feiyi_personList";
    }

    *//**
     * 人物删除
     * @param model
     * @return
     *//*
    @RequestMapping("/personDelete")
    public String personDelete(Model model,Integer id) {
        if(id !=  null){
            Person person = personService.selectByPrimaryKey(id);
            //删除人物的图片
            //String fp= filePath.substring(filePath.indexOf("/")+1);//文件的真实路径
            String name = person.getImg().substring(person.getImg().indexOf("s") + 2);   //获取文件名
            File file = new File(filePath +name);
            if(file.exists()){
                file.delete();
            }
            personService.deleteByPrimaryKey(id);
        }
        return  "redirect:/admin/personList";
    }

    *//**
     * 管理员
     *//*
    @RequestMapping("/feiyisList")
    public String zuopinList(Model model,Feiyis feiyis,@RequestParam(value="page",defaultValue="1")Integer page,String sou) {
        feiyis.setState(0);//0为正常 1是管理员下架的
        PageInfo<Feiyis> pageInfo =  feiyisService.selectFeiyis(feiyis,page,size);
        model.addAttribute("feiyiList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("feiyis",feiyis);
        return  "behind/admin/feiyisList";
    }




    *//**
     * 非遗视界删除
     * @param model
     * @return
     *//*
    @RequestMapping("/feiyisDelete")
    public String feiyisDelete(Model model,Integer id,Integer type) {
        if (id != null) {
            Feiyis feiyis = feiyisService.selectByPrimaryKey(id);
            //删除图片
            // String fp= filePath.substring(filePath.indexOf("/")+1);//文件的真实路径
            if (feiyis.getImg() != null) {
                String name = feiyis.getImg().substring(feiyis.getImg().indexOf("s") + 2);//获取文件
                File file = new File(filePath + name);
                if (file.exists()) {
                    file.delete();
                }
            }
            feiyisService.deleteByPrimaryKey(id);
        }
        return "redirect:/admin/feiyisList?type=" + type;
    }










    *//**
     * 后台主页
     * @return
     *//*
    @RequestMapping("/index")
    public String index(Model model) {
        //图表信息
        int zixun =  zixunService.countByExample(null);
        int video =  videoService.countByExample(null);
        int person = personService.countByExample(null);
        int zuocount = feiyisService.countByExamples(1);
        int huocount = feiyisService.countByExamples(2);
        int zoucount = feiyisService.countByExamples(3);
        int facount = feiyisService.countByExamples(4);
        model.addAttribute("zixun",zixun);
        model.addAttribute("video",video);
        model.addAttribute("person",person);
        model.addAttribute("zuocount",zuocount);
        model.addAttribute("huocount",huocount);
        model.addAttribute("zoucount",zoucount);
        model.addAttribute("facount",facount);

        //总评论数
        int commentcount = commentService.countByExample(null);
        //用户数
        int usercount = usertService.countByExample(null);
        //商品数量
        int shopcount  = shopService.countByExample(null);
        //资讯数量
        int zixuncount  = zixunService.countByExample(null);
        model.addAttribute("commentcount",commentcount);
        model.addAttribute("usercount",usercount);
        model.addAttribute("shopcount",shopcount);
        model.addAttribute("zixuncount",zixuncount);
        return  "behind/admin/index";
    }





    *//**
     * 资讯列表
     * @param model
     * @return
     *//*
    @RequestMapping("/zixunList")
    public String zixunList(Model model, Zixun zixun, @RequestParam(value="page",defaultValue="1")Integer page, String sou) {
        if(zixun == null){
            zixun = new Zixun();
        }
        zixun.setState(0);// 0 是正常 1被下架的
        PageInfo<Zixun> pageInfo =  zixunService.selectZixunList(zixun,page,size);
        model.addAttribute("zixunList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("zixun",zixun);
        return    "behind/admin/zixunList";
    }


    *//**
     * 资讯下架
     * @param model
     * @return
     *//*
    @RequestMapping("/zixunUptate")
    public String zixunUptate(Model model,Integer id) {
       if(id != null){
           Zixun  zixun = new Zixun();
           zixun.setId(id);
           zixun.setState(1); //1是下架
           zixunService.updateByPrimaryKeySelective(zixun);
       }


        return    "redirect:/admin/zixunList";
    }



    *//**
     * 管理评论
     * @return
     *//*
    @RequestMapping("/commentList")
    public String commentList(Model model,Integer type) {
        if(type != null){
            Comment comment = new Comment();
            comment.setType(type);//商品评论
            comment.setReport(1);//1为举报的
            List<Comment> commentsList = commentService.selectComment(comment);
            model.addAttribute("commentsList",commentsList);
        }
        return  "behind/admin/commentList";
    }

    *//**
     * 评论删除
     * @return
     *//*
    @RequestMapping("/commentDel")
    public String commentDel(Model model,Integer id) {
        if(id != null){
            commentService.deleteByPrimaryKey(id);

        }
        return  "redirect:/admin/commentList";
    }

    *//**
     *审核
     * @return
     *//*
    @RequestMapping("/merchantList")
    public String merchantList(Model model,Integer id) {
        Merchant merchant = new Merchant();
        merchant.setState(0);
        List<Merchant> merchantlist =   merchantService.selectMerchant(merchant);
        model.addAttribute("merchantlist",merchantlist);
        return  "behind/admin/merchantList";
    }
    *//**
     *通过
     * @return
     *//*
    @RequestMapping("/merchanUpate")
    public String merchanUpate(Model model,Integer id) {
        Merchant merchant = new Merchant();
        if(id != null){
            merchant.setId(id);
            merchant.setState(1);
            merchantService.updateByPrimaryKeySelective(merchant);
        }
        return  "redirect:/admin/merchantList";

    }
    *//**
     *未通过
     * @return
     *//*
    @RequestMapping("/merchanDel")
    public String merchanDel(Model model,Integer id) {
        if(id != null){
            merchantService.deleteByPrimaryKey(id);
        }
        return  "redirect:/admin/merchantList";

    }

    *//**
     *用户列表
     * @return
     *//*
    @RequestMapping("/userList")
    public String userList(Model model) {
        List<User> userlist = usertService.selectFull(null);
        model.addAttribute("userlist",userlist);
        return  "behind/admin/userList";
    }


    *//**
     *用户删除
     * @return
     *//*
    @RequestMapping("/userDel")
    public String userDel(Model model,Integer id) {
         if(id != null){
             usertService.deleteByPrimaryKey(id);
         }

        return  "redirect:/admin/userList";
    }

    *//**
     *修改密码
     * @return
     *//*
    @RequestMapping("/adminUptatePassword")
    public String adminUptatePassword(Model model,Admin admin,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Admin ad = (Admin) session.getAttribute("ADMIN");
        if(ad != null && admin.getPassword() != null){
              admin.setId(ad.getId());
               adminService.updateByPrimaryKeySelective(admin);
        }
        return  "redirect:/admin/index";
    }
*/

}
