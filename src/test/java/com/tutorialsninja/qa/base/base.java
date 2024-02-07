package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialninja.qa.utils.Utilites;

public class base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public base() {
		
		 prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	
		dataProp =new Properties();
		File  dataPropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninjas\\qa\\testdata\\testdata.properties");
		
		try {
	    FileInputStream datafis=new  FileInputStream(dataPropFile);
		dataProp.load(datafis);
		}catch(Throwable e)	{
			e.printStackTrace();
		}
		try {
		FileInputStream fis=new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public WebDriver InitializeBrowserAndOpenApplicationURL(String BrowserName) {
	
		  if(BrowserName.equalsIgnoreCase("chrome")) {
			 
			  driver=new ChromeDriver();
		}else if(BrowserName.equalsIgnoreCase("firefox")) {
			
			driver=new FirefoxDriver();
			
		}else if(BrowserName.equalsIgnoreCase("edge")) {
			
			driver=new EdgeDriver();
		}else if(BrowserName.equalsIgnoreCase("safari")) {
			
			driver=new SafariDriver();
		}
		
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilites.IMPLICIT_WAIT_TIME));
		  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilites.PAGE_WAIT_TIME  ));
		  driver.get(prop.getProperty("url"));

		  return driver;
	}

}  
