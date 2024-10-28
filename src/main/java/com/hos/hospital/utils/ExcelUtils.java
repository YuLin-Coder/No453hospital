package com.hos.hospital.utils;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.AQUA;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.User;

public class ExcelUtils {
	//把数据导出成
    public static void main(String[] args) throws IOException  {
    	//创建文件字节输出流
	   // getExcel("xlsx","C:\\Users\\Administrator\\Desktop\\exportFile.xlsx", new String[]{"姓名","年龄","日期"},null);
	
		
    }

   public static  Workbook  getExcel(String type,String[] sh,List<Doctor> list ) throws IOException {
    	//创建Excel对象
    	 Workbook  wb = null;
    	 if(type.equals("xls")){
    			wb =  new HSSFWorkbook();//xls为2003版
    	 }else if(type.equals("xlsx")){
    			wb  = new XSSFWorkbook(); //xlsx为2007版
    	 }
    	 //创建表格，Sheet为表格对象
    	 Sheet createSheet = wb.createSheet("sheet1");
    	 createSheet.setColumnWidth(1,3000);
		 createSheet.setColumnWidth(2,6000);
		 createSheet.setColumnWidth(3,6000); 
    	 //创建表格的第一行，Row为行对象
    	 Row createRow = createSheet.createRow(0);
    	 //设置每个单元格的样式
    	 CellStyle createCellStyle = wb.createCellStyle();
    	 createCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
    	 Font createFont = wb.createFont();//字体对象
    	 createFont.setColor(HSSFColor.VIOLET.index);
    	 createFont.setFontName("微软雅黑");
    	 createCellStyle.setFont(createFont);	 
    	 //创建第头行的数据样式
    	 for (int i = 0; i < sh.length; i++) {
    		 //通过索引获取第一行的Row对象，在创建Cell单元格，并赋值
    		 Cell createCell = createRow.createCell(i);//通过行对象创建小单元格对象
    		 createCell.setCellValue(sh[i]);//赋值	
    		 createCell.setCellStyle(createCellStyle); //单元格样式
    	 }
    	 //创建单元格样式对象
    	 CellStyle createCellStyle2 = wb.createCellStyle();
    	 createCellStyle2.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);//水平居中
         //---字体对象-----
    	 Font createFont2 = wb.createFont();
    	 createFont2.setColor(HSSFColor.BLUE.index);
    	 createFont2.setFontName("宋体");
    	 createCellStyle2.setFont(createFont2);
         //日期转换工具
 	     SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 for(int i = 1;i<list.size();i++){
    		 //创建行对象
    		 Row  createRow2 = createSheet.createRow(i); 
    		 //createRow2.setHeight((short)1500);设置行高
    		 //创建单元格，赋值
    	     Cell createCel1 = createRow2.createCell(0);
    	     createCel1.setCellValue(list.get(i).getName());
    	     createCel1.setCellStyle(createCellStyle2);
    	     
    	     Cell createCel2 = createRow2.createCell(1);
    	     createCel2.setCellValue(list.get(i).getSid());
    	     createCel2.setCellStyle(createCellStyle2);
    	     
    	     Cell createCel3 = createRow2.createCell(2);
    	     createCel3.setCellValue(list.get(i).getSname());
    	     createCel3.setCellStyle(createCellStyle2);
     		 
     	     Cell createCel4 = createRow2.createCell(3);
     	     if(list.get(i).getBegindate() != null) {
 	    	     String format = dateFormat.format(list.get(i).getBegindate());
	 	    	 createCel4.setCellValue(format);
	 	    	 createCel4.setCellStyle(createCellStyle2);
     	     }
     		 short lie  = 3;
     	     short hangrow = (short) i;
    	 }
		 //把文件输入流中 
    	//wb.write(outputStream);
    	 return wb;
		
    }

    
    
}






