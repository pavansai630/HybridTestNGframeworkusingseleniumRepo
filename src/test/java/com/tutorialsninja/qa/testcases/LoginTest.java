package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utils.Utilites;
import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;


public class LoginTest  extends base{
	
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	 public WebDriver driver;
	  
	  @BeforeMethod
	  public void setup() {
		  
		  driver=InitializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		  HomePage homePage=new HomePage(driver);
	      loginPage= homePage.navigateToLoginPage();
		 
	  }
	  
	  @AfterMethod
	public void  tearDown() {
		driver.quit();
		
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password) {
		
	    AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayedStatusofEditYourAccountInformtionOption(),"Edit your Account information is not Displayed");
	
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData(){
		
		Object[][] data=Utilites.getTestDataFromExcel("Login");
		return data;
	}
	
	
     @Test(priority=2)
	  public void  verifyLoginWithInvalidCredentials() {
		 
    	 loginPage.login(Utilites.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
		 Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not  displayed");	
		  
		  
	  }
	  
	  @Test(priority=3)
	  public void verifyLoginWithInvalidEmailAndValidPassword() {
		  
		  loginPage.login(Utilites.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		  Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not  displayed");	
		  
		  
	  }
	   @Test(priority=4)
	  public void verifyLoginWithValidEmailAndInvalidPassword(){
		   
		   loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		   Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not  displayed");	
				  
			
				  	
	  }
	   @Test(priority=5)
	   public void verifyLoginWithoutProvidingCredentials() {
		   
		   loginPage.clickonLoginButton();
		   Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not  displayed");	
			  
		     
			    
	   }
	
	  }
	 
	
 
