package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utils.Utilites;
import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;


public class RegisterTest  extends base {
	   RegisterPage registerPage;
	   AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
    
	 public WebDriver driver;
	  @BeforeMethod
	  public void setup() {
		  
		  driver=InitializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		  HomePage homePage=new HomePage(driver);
		  registerPage= homePage.navigateToRegisterPage();
		
		  
	  }
	  
	  
	@AfterMethod
	public void teraDown(){
		driver.quit();
	}
   
	@Test(priority=1)
	 public void verifyRegisterAnAccountWithManatoryfields() {
	         accountSuccessPage=registerPage.RegisterwithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilites.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		     Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullycreatedHeading")," Account Success page is not displayed");
		
	 }
	@Test(priority=2)
	 public void verifyRegisteringAccountByProvidingAllfields() {
		   accountSuccessPage=registerPage.RegisterwithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilites.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
		   Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullycreatedHeading")," Account Success page is not displayed");
	  
		 
	 }
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		  registerPage.RegisterwithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
		  Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding  duplicate email address is not displayed");
	
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingdetails() {
		
		   registerPage.clickOnContinueButton();
		   Assert.assertTrue(registerPage.displayStatusofWarningMessages(dataProp.getProperty("privacypolicyWarning"),dataProp.getProperty("firstNameWarning"),dataProp.getProperty("lastNameWarning"),dataProp.getProperty("emailWarning"),dataProp.getProperty("telephoneWarning"),dataProp.getProperty("PasswordWarning")));
		 	  
	}
	 }
	

