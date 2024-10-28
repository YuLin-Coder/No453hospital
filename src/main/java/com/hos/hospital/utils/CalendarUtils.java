package com.hos.hospital.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
	static  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
     public static void main(String[] args) {
	   	   /*
			 *  //EEEE代表星期 
			 *  SimpleDateFormat dateFm = new SimpleDateFormat("EEEE"); 
			 *  String format = dateFm.format(date);
			 */
    	 String[] toString = getToString(new Date());
    	 System.out.println(Arrays.toString(toString));
	   }
    
	    //获取上周一集合
	    public static  Date[] geLastWeekMonday() {
			 Calendar cal = Calendar.getInstance();
			//cal.setTime(getThisWeekMonday(date));
			 cal.add(Calendar.DATE, -7);
			//设置周一为第一天
	         cal.setFirstDayOfWeek(Calendar.MONDAY);
		    //时间设置到周一，此时时间为周一的日期
	        // DAY_OF_WEEK表示星期，MONDAY表示星期一
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		    //获取上一周的日期
		    return dateArrs(cal.getTime(),7);
		}
	    
	    //获取数组
	    public static  Date[]  dateArrs(Date date,int len) {
		  	  Calendar cal = Calendar.getInstance();
		   	//定义date数组，长度为5
		     Date[] days =new Date[len];
	    	  cal.setTime(date);
	    	  days[0] = date;
	    	  for(int i=1;i < len;i++) {
	    		  cal.add(Calendar.DATE,1);
	    		  days[i] = cal.getTime();
	    	  }
			return days;
		}
	
	    //获取某周的全部日期
	    public static  String[]  getToString(Date date) {
	    	 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
	    	 Date[] dateArrs = getThisWeekMonday(date,7);
	    	 String[] weeks = {"周一","周二","周三","周四","周五","周六","周日"};
	    	 StringBuffer sb = new StringBuffer();
	    	 for(int i=0;i < dateArrs.length;i++) {
	    		 String format = dateFormat.format(dateArrs[i]);
	    		 weeks[i] =weeks[i]+" "+format;
	    		 sb.append(weeks[i]+",");
	    				 
	    	  }
	    	  String string = sb.toString();
			  return string.split(",");
		}
	    
	    
	    //获取本周一
		public static Date[] getThisWeekMonday(Date date,Integer shi) {
		       String format = dateFormat.format(date);
		        //今天是周几
		        //String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		       //日历类型
			     Calendar cal = Calendar.getInstance();
		    	 //设置周一为第一天
			  	 cal.setFirstDayOfWeek(Calendar.MONDAY);
		         cal.setTime(date);
		        //当前的日期
				 int in = cal.get(Calendar.DAY_OF_WEEK);
				//获取一星期的第一天
				 int yi = cal.getFirstDayOfWeek();
				 if(in == 1) {
					 in = 8;
				 }
			 	//System.err.println("周一"+yi+"----当前日期"+in);
				//周一等于周一索引 减去 当前索引
				cal.add(Calendar.DATE,yi-in);
				Date time = cal.getTime();//获取周一
				if(shi != null) {
					 in = 8;
				}
				return dateArrs(time,in-1);
		}
	
	
	    
    
}






