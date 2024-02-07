package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilites {
	
	public  static  final  int IMPLICIT_WAIT_TIME=10;
	public  static  final  int PAGE_WAIT_TIME=5;

public  static String generateEmailWithTimeStamp() {
		
		Date date=new Date();
	    String  timeStamp=date.toString().replace(" ", "_").replace(":", "_");	
	    return "motoori"+ timeStamp +"@gmail.com";
	 
	}

  public static Object[][] getTestDataFromExcel(String  sheetName) {
	  File  excelfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninjas\\qa\\testdata\\TutorialsninjaTestdata.xlsx");
	  XSSFWorkbook workbook=null;
	
	  try {
	  FileInputStream fisexcel=new FileInputStream(excelfile);
	   workbook=new XSSFWorkbook(fisexcel);
	  
	  }catch(Throwable e) {
		 e.printStackTrace(); 
	  }
	  
	  XSSFSheet  sheet=workbook.getSheet(sheetName);
	  
	  int rows=sheet.getLastRowNum();
	  int cols=sheet.getRow(0).getLastCellNum();
	  
	  Object[][] data = new Object[rows][cols];
	  
	  for(int i=0; i<rows; i++) {
		  
		  XSSFRow row=sheet.getRow(i+1);
		  
		  for(int j=0; j<cols; j++) {
			  
			  XSSFCell cell=row.getCell(j);
			  CellType celltype=cell.getCellType();
			  
			  switch(celltype) {
			  case STRING:
				  data[i][j]=cell.getStringCellValue();
			  break;
			  case NUMERIC:
				  data[i][j]=Integer.toString((int)cell.getNumericCellValue());
				 break;
			  case BOOLEAN:
				  data[i][j]=cell.getBooleanCellValue();
				  break;
				  
					 
				 
			  }
		  }
	  }
	  return data;
  }
  
  public static String captureScreenshot(WebDriver driver,String TestName) {

	  File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  String destinationSreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+TestName+".png";
	  try {
		FileHandler.copy(srcScreenshot, new File(destinationSreenshotPath));
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return destinationSreenshotPath;  
  }
   
}
