package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest  extends base{
	     HomePage homePage;
	    SearchPage searchPage;
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		
		driver=InitializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	     homePage=new HomePage(driver);
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();	
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct(){
		
		searchPage=homePage.SearchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid  Product HP is not displayed in the search results ");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		searchPage=homePage.SearchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"abcd","No product message in search results is not displayed");
			
	}
	
	@Test(priority=3,dependsOnMethods={"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		searchPage=homePage.clickonSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No product message in search results is not displayed");
	
		
	}

}
