package com.hos.hospital.utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
public class ExcelInput {
	  public static void main(String[] args) throws IOException {
		/*
		 * InputStream in = new FileInputStream("resource.xlsx"); List<String[]> list =
		 * jieExcel(in,"xlsx"); for(String[] v : list){
		 * System.out.println("-------导出的数据---------");
		 * System.out.println(Arrays.toString(v)); }
		 */
		  int indexOf = "你好啊.jsp".indexOf(".");
		  String substring = "你好啊.jsp".substring(indexOf+1);
		  System.out.println("-------导出的数据"+indexOf);
		  System.out.println("-------新数据"+substring);
	  }
	  
	/**
	 * 解析excel文件
	 * inputStream输入流来解析excel文件
	 * 文件的后缀名 suffix
	 * 行数                hangshu
	 * @throws IOException 
	 */
	 public static  List<String[]>  jieExcel(InputStream  in,String suffix) throws IOException{		
		 	//  1.定义excel对象变量
			Workbook wb  = null;
			//2.判断后缀名
			if("xls".equals(suffix)){// 1.2003版xls
				wb =  new HSSFWorkbook(in);
			}else if("xlsx".equals(suffix)){//2.2007版
				wb  = new XSSFWorkbook(in);
			}
			/*3.获取表，一张excel，可以分为若干个表，都是workbook对象
	         *sheet是一个表格
	         *row是行
	         *cell是列,每个单元格
	         */
	        Sheet sheet = wb.getSheetAt(0);
	        Row   row   = null;
	        Cell  cell  = null;
	        int   zui = sheet.getLastRowNum(); //获取最后一行行号
	        System.out.println("最后一行行号"+zui);
	        List<String[]>  hang = new ArrayList<String[]>();
	        //获取行数据					        
	        for(int  i = 1;  i<zui+1; i++ ){
               row = sheet.getRow(i);//获取行数据
               //获取第一列和最后一列的下标
	           short     firstCellNum = row.getFirstCellNum();
	           short     lastCellNum  = row.getLastCellNum();
	           System.out.println("第一列下标"+firstCellNum);
	           System.out.println("最后一列下标"+lastCellNum);
	           String[]  lie          = new String[lastCellNum];
			   if(lastCellNum != 0){
			       //获取列数据
				   for(int j = firstCellNum;j < lastCellNum;j++ ){
			    	  Cell cell2 = row.getCell(j);
			    	  if(cell2 != null){
			    		  lie[j]  =  getCellValue(cell2);
			    	  }
			       }
				   hang.add(lie); 
			   }
			   System.out.println("****************循环**********");    
			 }
		   return  hang;
       }
	     
	   private static String getCellValue(Cell cell) {
	        String  value = null;
	        switch (cell.getCellType()) {
				 case HSSFCell.CELL_TYPE_STRING://字符类型
					  value =  cell.getRichStringCellValue().toString();
					  System.out.println("字符:"+value);
				 break;
				 case HSSFCell.CELL_TYPE_NUMERIC://数字类型包含  数字 日期
                     if(HSSFDateUtil.isCellDateFormatted(cell)){
                    	   SimpleDateFormat     sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
						   Date dateCellValue = cell.getDateCellValue();
						   value         = sdf.format(dateCellValue); 
						   System.out.println("日期串:"+value);
                     }else{
                    	double  shuzi = cell.getNumericCellValue();
                    	//数学格式化工具
                    	DecimalFormat format = new DecimalFormat();
                    	//列样式
                    	String style = cell.getCellStyle().getDataFormatString();
                    	if(style.equals("General")){
    						format.applyPattern("#");//正则表达式
    					}
                    	 value = format.format(shuzi);
                    	 System.out.println("数字:"+value);
                     }
			     break;
				 case HSSFCell.CELL_TYPE_BLANK:System.out.println("类型空");break;
				 default: System.out.println("空");break;
			}
		  return value;
	  }  
 }










