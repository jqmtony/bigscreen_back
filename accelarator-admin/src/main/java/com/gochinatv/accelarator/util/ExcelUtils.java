package com.gochinatv.accelarator.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExcelUtils {
        
	/**
	 * 导出excel公用
	 * @param data//要导出的数据集
	 * @param Excelcolumn//导出excel的列的名称
	 * @param width//宽度
	 * @param response//response对象
	 * @param excelName//导出excel的名称
	 * @return
	 */
	public static boolean getExcel(List<Object[]> data, String[] Excelcolumn,int[] width,HttpServletResponse response,String excelName){
		boolean flag = false; 
	    try{ 
	    	HSSFWorkbook wb = new HSSFWorkbook(); 
	        HSSFSheet sheet = wb.createSheet(excelName); //创建的excel的名称
	        HSSFCellStyle style = disFont(wb);
        	sheet.setDefaultColumnWidth(20);
        	HSSFRow row =sheet.createRow(0);
        	HSSFCell cell;
	        for(int x=0;x<Excelcolumn.length;x++){  // 输出列
	        	cell = row.createCell(x);
	        	cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString(Excelcolumn[x]));
				row.setHeightInPoints((short)20);
				sheet.setColumnWidth(x,width[x]*256);
	        } 
	        for (int i = 0; i < data.size(); i++) { //内容
	          row = sheet.createRow(i+1); 
	          Object [] obj =(Object [])data.get(i);
	          cell = row.createCell(0); 
	          cell.setCellValue(new HSSFRichTextString((i+1)+""));
		      for(int j=0;j<obj.length;j++){
		    	  cell = row.createCell((j+1)); 
		          cell.setCellValue(new HSSFRichTextString((obj[j]==null?"":obj[j])+""));
		      }
	        } 
	        	response.setContentType("application/vnd.ms-excel");
				String fileName = new String(excelName.getBytes("gbk"),"ISO8859-1");
				response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
				wb.write(response.getOutputStream());
				flag = true; 
	     } 
	     catch(Exception ex)
	     { 
	    	 ex.printStackTrace();
	         flag = false; 
	     } 
		return flag;
	}
	
	
	/**
	 * 导出excel公用
	 * @param data//要导出的数据集
	 * @param Excelcolumn//导出excel的列的名称
	 * @param response//response对象
	 * @param excelName//导出excel的名称
	 * @return
	 */
	public static boolean getExcel(List<Object[]> data, String[] Excelcolumn,HttpServletResponse response,String excelName){
		boolean flag = false; 
	    try{ 
	    	HSSFWorkbook wb = new HSSFWorkbook(); 
	        HSSFSheet sheet = wb.createSheet(excelName); //创建的excel的名称
	        HSSFCellStyle style = disFont(wb);
        	sheet.setDefaultColumnWidth(20);
        	HSSFRow row =sheet.createRow(0);
        	HSSFCell cell;
	        for(int x=0;x<Excelcolumn.length;x++){  // 输出列
	        	cell = row.createCell(x);
	        	cell.setCellStyle(style);
	        	row.setHeightInPoints((short)20);
				cell.setCellValue(new HSSFRichTextString(Excelcolumn[x]));
	        } 
	        for (int i = 0; i < data.size(); i++) { //内容
	          row = sheet.createRow(i+1); 
	          Object [] obj =(Object [])data.get(i);
	          cell = row.createCell(0); 
	          cell.setCellValue(new HSSFRichTextString((i+1)+""));
		      for(int j=0;j<obj.length;j++){
		    	  cell = row.createCell((j+1)); 
		          cell.setCellValue(new HSSFRichTextString((obj[j]==null?"":obj[j])+""));
		      }
	        } 
	        	response.setContentType("application/vnd.ms-excel");
				String fileName = new String(excelName.getBytes("gbk"),"ISO8859-1");
				response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
				wb.write(response.getOutputStream());
				flag = true; 
	     } 
	     catch(Exception ex)
	     { 
	    	 ex.printStackTrace();
	         flag = false; 
	     } 
		return flag;
	}

	/**
	 * 设置导出excel的格式样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle disFont(HSSFWorkbook wb){
		 HSSFCellStyle style = wb.createCellStyle();
		 style.setFillForegroundColor(HSSFColor.WHITE.index);
         style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         style.setBorderRight(HSSFCellStyle.BORDER_THIN);
         style.setBorderTop(HSSFCellStyle.BORDER_THIN);
         style.setAlignment(HSSFCellStyle.BORDER_MEDIUM);
         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
         style.setWrapText(true);
         HSSFFont font = wb.createFont();
         font.setColor(HSSFColor.DARK_BLUE.index);
         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         style.setFont(font);
         return style;
	}
	
}
