package com.te.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.diagnostics.logging.Logger;
import com.sun.jna.platform.FileUtils;
import com.te.hybris.pages.HMC;
import com.te.util.GenericUtils;


public class Hybris_RegressionTest extends GenericUtils {
	
	HMC hmc;
	Select sel;
	String TCPN_Results_partNumber;
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	
//	@Parameters({ "IP_ADDRESS" })
//	@BeforeSuite
//	public void beforeSuite(String ipaddress){
//		configure(ipaddress);
//	}
	@BeforeSuite
	public void beforeSuite(){
		configure();
	}
	
	@BeforeClass
	public void reportSettings()
	{
		Date date = new Date();
		String filename = System.getProperty("user.dir")+"\\ExtentReports\\HMC_RegressionTestResults"+date.getTime()+".html";
		
		htmlreports = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlreports);
		
		htmlreports.config().setReportName("Hybris_Regression Automation Test Results");
		htmlreports.config().setTheme(Theme.STANDARD);
		htmlreports.config().setTestViewChartLocation(ChartLocation.TOP);
		
		extent.setSystemInfo("UserName", "TE328511");
		extent.setSystemInfo("HostName", "INQ69NBO5B4JG32");
		extent.setSystemInfo("OS", "Windows10");
//		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Selenium", "2.5.3");
		extent.setSystemInfo("Java", "1.8");
	}
	
	@AfterClass
	public void afterClass(){
		extent.flush();
	}
	
	
	@BeforeTest
	public void beforeTest(){
		
		hmc = PageFactory.initElements(driver, HMC.class);
	}
	
	@AfterMethod
	public void reportTearDown(ITestResult result)
	{
		if(result.getStatus()==result.FAILURE){
			test.log(Status.FAIL, result.getName()+  "     " + "       is Failed ->>     "    +    result.getThrowable());	
		} 
		if(result.getStatus()==result.SUCCESS){
			test.log(Status.PASS, result.getName()+ "Passed");
		}
		if(result.getStatus()==result.SKIP){
			test.log(Status.SKIP, result.getName()+ "Skipped");
		}
	}
	
	
	@Parameters({ "Environment" })
	@Test(priority=0)
	public void login(String Environment)
	{
		test=extent.createTest("Verify Login for HMC");
		test.log(Status.INFO, "Test Case Execution started");
		
		launchApp(CONFIG.getProperty("HMC_URL"),Environment);
		extent.setSystemInfo("Environment", Environment);
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Username));
		hmc.Username.clear();
		threadSleepWait(2000);
		hmc.Username.sendKeys(CONFIG.getProperty("username"));
		hmc.Password.clear();
		hmc.Password.sendKeys(CONFIG.getProperty("password"));
		hmc.Login.click();
		
	}
	
	@Test(priority=1,dependsOnMethods="login")
	public void Search_TCPN(){
		test=extent.createTest("Verify Search_TCPN");
		test.log(Status.INFO, "Test Case Execution started");
		
			hmc.Catalog.click();
			hmc.Parts.click();
			try {
				Assert.assertEquals(hmc.searchPartNum("HD36-24-19SE-059"), true);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			hmc.TCPN_Results.click();
		
		System.out.println("Test ended");
		
	}
	
	@Test(priority=2,dependsOnMethods="login")
	public void verify_UniversalAttributesTab()
	{
		test=extent.createTest("verify_UniversalAttributesTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.UniversalTab.click();
		Assert.assertEquals(hmc.CompatibleParts.isDisplayed(),true, "CompatibleParts is not displayed");
		Select sel1 = new Select(hmc.EligibleforCompatiblePartsDropdown);
		System.out.println(sel1.getFirstSelectedOption().getText());
		Assert.assertEquals(sel1.getFirstSelectedOption().getText().trim(), "Yes");
		
		Assert.assertEquals(hmc.DerivedFrom.isDisplayed(),true, "DerivedFrom is not displayed");
		String DerivedFromPart = hmc.DerivedFromPart.getAttribute("value");
		System.out.println(DerivedFromPart);
		Assert.assertEquals(DerivedFromPart, "CAT-H3930-CH8172");
		
		Select sel2 = new Select(hmc.LegacyStatusOption);
		System.out.println(sel2.getFirstSelectedOption().getText());
		Assert.assertEquals(sel2.getFirstSelectedOption().getText().trim(), "Active");
		
		System.out.println(hmc.Orderable.isSelected());
		String orderable = hmc.Orderable.getAttribute("value");
		System.out.println(orderable);
		Assert.assertEquals(hmc.Orderable.isSelected(),true, "Orderable is not checked");
	}
	
	@Test(priority=3,dependsOnMethods="login")
	public void verify_LocalAttributesTab()
	{
		test=extent.createTest("Verify verify_LocalAttributesTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.LocalAttributesTab.click();
		Assert.assertEquals(hmc.ConnectorSystem.isDisplayed(),true, "ConnectorSystem is not displayed");
		Assert.assertEquals(hmc.NumberOfPositions.isDisplayed(),true, "NumberOfPositions is not displayed");
		Assert.assertEquals(hmc.Sealable.isDisplayed(),true, "Sealable is not displayed");
	}
	
	@Test(priority=4,dependsOnMethods="login")
	public void verify_CategorySystemTab()
	{
		test=extent.createTest("verify_CategorySystemTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CategorySystemTab.click();
		Assert.assertEquals(hmc.Facets.isDisplayed(),true, "Facets is not displayed");
	}
	
	@Test(priority=5,dependsOnMethods="login")
	public void verify_CommerceTab()
	{
		test=extent.createTest("verify_CommerceTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CommerceTab.click();
		Assert.assertEquals(hmc.ProductRestrictions.isDisplayed(),true, "ProductRestrictions is not displayed");
		Assert.assertEquals(hmc.ECCNData.isDisplayed(),true, "ECCNData is not displayed");
	}
	
	@Test(priority=6,dependsOnMethods="login")
	public void verify_PricesTab()
	{
		test=extent.createTest("verify_PricesTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.PricesTab.click();
		Assert.assertEquals(hmc.PriceQuantity.isDisplayed(),true, "PriceQuantity is not displayed");
		Assert.assertEquals(hmc.Prices.isDisplayed(),true, "Prices is not displayed");
	}
	
	@Test(priority=7,dependsOnMethods="login")
	public void verify_ImagesTab()
	{
		test=extent.createTest("verify_ImagesTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ImagesTab.click();
		Assert.assertEquals(hmc.ImagesGroup.isDisplayed(),true, "ImagesGroup is not displayed");
	}
	
	@Test(priority=8,dependsOnMethods="login")
	public void verify_SamplesTab()
	{
		test=extent.createTest("verify_SamplesTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.SamplesTab.click();
		Assert.assertEquals(hmc.GlobalSampleIndicator.isDisplayed(),true, "GlobalSampleIndicator is not displayed");
		Assert.assertEquals(hmc.SamplesIndicator.isDisplayed(),true, "SamplesIndicator is not displayed");
	}
	
	@Test(priority=9,dependsOnMethods="login")
	public void verify_ExtendedAttributesTab()
	{
		test=extent.createTest("verify_ExtendedAttributesTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ExtendedAttributesTab.click();
		Assert.assertEquals(hmc.IDandUNIT.isDisplayed(),true, "IDandUNIT is not displayed");
	}
	
	@Test(priority=10,dependsOnMethods="login")
	public void verify_DocumentsTab()
	{
		test=extent.createTest("verify_DocumentsTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.DocumentsTab.click();
	}
	
	@Test(priority=11,dependsOnMethods="login")
	public void verify_StockTab()
	{
		test=extent.createTest("verify_StockTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.StockTab.click();
		Assert.assertEquals(hmc.StockLevels.isDisplayed(),true, "StockLevels is not displayed");
		Assert.assertEquals(hmc.StockMetaData.isDisplayed(),true, "StockMetaData is not displayed");
	}
	
	@Test(priority=12,dependsOnMethods="login")
	public void verify_FutureStockTab()
	{
		test=extent.createTest("verify_FutureStockTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.FutureStockTab.click();
		Assert.assertEquals(hmc.FutureStockTab.isDisplayed(),true, "FutureStockTab is not displayed");
	}
	
	@Test(priority=13,dependsOnMethods="login")
	public void verify_UnclassifyArchiveTab()
	{
		test=extent.createTest("verify_UnclassifyArchiveTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.UnclassifyArchiveTab.click();
		Assert.assertEquals(hmc.UnclassifyPart.isDisplayed(),true, "UnclassifyPart is not displayed");
	}
	
	@Test(priority=14,dependsOnMethods="login")
	public void verify_CommunityTab()
	{
		test=extent.createTest("verify_CommunityTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CommunityTab.click();
		Assert.assertEquals(hmc.AverageRating.isDisplayed(),true, "AverageRating is not displayed");
	}
	
	@Test(priority=15,dependsOnMethods="login")
	public void verify_AdministrationTab()
	{
		test=extent.createTest("verify_AdministrationTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.AdministratorTab.click();
		Assert.assertEquals(hmc.LastChanges.isDisplayed(),true, "LastChanges is not displayed");
		Assert.assertEquals(hmc.ItemChanges.isDisplayed(),true, "ItemChanges is not displayed");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		explicitWait().until(ExpectedConditions.visibilityOf(hmc.TCPN));
		hmc.TCPN.clear();
		threadSleepWait(3000);
		sel = new Select(hmc.CatalogVersionDropDown);
		sel.selectByValue("-1");
	}
	
	/*@Test(priority=16,dependsOnMethods="login")
	public void verify_PromoteStatus_NotEligible()
	{
		test=extent.createTest("verify_PromoteStatus_NotEligible");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.promotestatusDropdown));
		select(hmc.promotestatusDropdown, "NOTELIGIBLE");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for NOTELIGIBLE is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=17,dependsOnMethods="login")
	public void verify_PromoteStatus_NotReviewed()
	{
		test=extent.createTest("verify_PromoteStatus_NotReviewed");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.promotestatusDropdown, "NOTREVIEWED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for NOTREVIEWED is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=18,dependsOnMethods="login")
	public void verify_PromoteStatus_Promoted()
	{
		test=extent.createTest("verify_CategorySystemTab");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.promotestatusDropdown, "PROMOTED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for PROMOTED is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=19,dependsOnMethods="login")
	public void verify_PromoteStatus_NotPromoted()
	{
		test=extent.createTest("verify_PromoteStatus_NotPromoted");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.promotestatusDropdown, "NOTPROMOTED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for NOTPROMOTED is not displayed");
		}else{
			driver.navigate().back();
		}
		sel = new Select(hmc.promotestatusDropdown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=20,dependsOnMethods="login")
	public void verify_ReleaseStatus_Approved()
	{
		test=extent.createTest("verify_ReleaseStatus_Approved");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.releasestatusDropdown, "APPROVED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for APPROVED is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=21,dependsOnMethods="login")
	public void verify_ReleaseStatus_Inprocess()
	{
		test=extent.createTest("verify_ReleaseStatus_Inprocess");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.releasestatusDropdown, "INPROCESS");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for INPROCESS is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=22,dependsOnMethods="login")
	public void verify_ReleaseStatus_Ready()
	{
		test=extent.createTest("verify_ReleaseStatus_Ready");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.releasestatusDropdown, "READY");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for READY is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=23,dependsOnMethods="login")
	public void verify_ReleaseStatus_NotApplicable()
	{
		test=extent.createTest("verify_ReleaseStatus_NotApplicable");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.releasestatusDropdown, "NOTAPPLICABLE");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for NOTAPPLICABLE is not displayed");
		}else{
			driver.navigate().back();
		}
		sel = new Select(hmc.releasestatusDropdown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=24,dependsOnMethods="login")
	public void verify_LegacyStaus_Preliminary()
	{
		test=extent.createTest("verify_LegacyStaus_Preliminary");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.legacystatusDropdown, "Preliminary");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		//explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TCPN_Results));
		//threadSleepWait(20000);
		//hmc.refresh.click();
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for Preliminary is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=25,dependsOnMethods="login")
	public void verify_LegacyStaus_Active()
	{
		test=extent.createTest("verify_LegacyStaus_Active");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.legacystatusDropdown, "Active");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		//explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TCPN_Results));
		//threadSleepWait(20000);
		//hmc.refresh.click();
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for Active is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=26,dependsOnMethods="login")
	public void verify_LegacyStaus_Obsolete()
	{
		test=extent.createTest("verify_LegacyStaus_Obsolete");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.legacystatusDropdown, "Obsolete");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		//explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TCPN_Results));
		//threadSleepWait(20000);
		//hmc.refresh.click();
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for Obsolete is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=27,dependsOnMethods="login")
	public void verify_LegacyStaus_Restricted()
	{
		test=extent.createTest("verify_LegacyStaus_Restricted");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.legacystatusDropdown, "Restricted");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		//explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TCPN_Results));
		//threadSleepWait(20000);
		//hmc.refresh.click();
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for Restricted is not displayed");
		}else{
			driver.navigate().back();
		}
	}
	
	@Test(priority=28,dependsOnMethods="login")
	public void verify_LegacyStaus_FieldReplacement()
	{
		test=extent.createTest("verify_LegacyStaus_FieldReplacement");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.legacystatusDropdown, "Field Replacement");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		//explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TCPN_Results));
		//threadSleepWait(20000);
		//hmc.refresh.click();
		if(hmc.TCPN_Results.isDisplayed()){
			Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true, "TCPN_Results for Field Replacement is not displayed");
		}else{
			driver.navigate().back();
		}
		sel = new Select(hmc.legacystatusDropdown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=29,dependsOnMethods="login")
	public void verify_TEMasterCatalogStaged()
	{
		test=extent.createTest("verify_TEMasterCatalogStaged");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.CatalogVersionDropDown, "TEMasterCatalog - Staged");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		sel = new Select(hmc.CatalogVersionDropDown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=30,dependsOnMethods="login")
	public void verify_PromotedApprovedActive()
	{
		test=extent.createTest("verify_PromotedApprovedActive");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.promotestatusDropdown, "PROMOTED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.releasestatusDropdown));
		select(hmc.releasestatusDropdown, "APPROVED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.legacystatusDropdown));
		select(hmc.legacystatusDropdown, "Active");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		
		sel = new Select(hmc.promotestatusDropdown);
		sel.selectByValue("-1");
		
		sel = new Select(hmc.releasestatusDropdown);
		sel.selectByValue("-1");
		
		sel = new Select(hmc.legacystatusDropdown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=31,dependsOnMethods="login")
	public void verify_NotReviewedInProcess()
	{
		test=extent.createTest("verify_NotReviewedInProcess");
		test.log(Status.INFO, "Test Case Execution started");
		
		select(hmc.promotestatusDropdown, "NOTREVIEWED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.releasestatusDropdown));
		select(hmc.releasestatusDropdown, "INPROCESS");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		
		sel = new Select(hmc.promotestatusDropdown);
		sel.selectByValue("-1");
		
		sel = new Select(hmc.releasestatusDropdown);
		sel.selectByValue("-1");
	}*/
	
	@Test(priority=32,dependsOnMethods="login")
	public void verify_ChangePromoteStatustoPromoted()
	{
		test=extent.createTest("verify_ChangePromoteStatustoPromoted");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.searchPartNum("5-406726-2"), true,"No Results for this part number");
		hmc.TCPN_Results.click();
		select(hmc.TCPN_promotestatusDropdown, "PROMOTED");
		hmc.Save.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.AdministratorTab));
		hmc.AdministratorTab.click();
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TimeModifiedField));
		System.out.println(hmc.TimeModifiedField.getAttribute("value"));
		Assert.assertEquals(hmc.TimeModifiedField.getAttribute("value"), getDate());
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		hmc.TCPN.clear();
		sel = new Select(hmc.CatalogVersionDropDown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=33,dependsOnMethods="login")
	public void verify_ChangePromoteStatustoNotEligible()
	{
		test=extent.createTest("verify_ChangePromoteStatustoNotEligible");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.searchPartNum("5-406726-2"), true,"No Results for this part number");
		hmc.TCPN_Results.click();
		select(hmc.TCPN_promotestatusDropdown, "NOTELIGIBLE");
		hmc.Save.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.AdministratorTab));
		hmc.AdministratorTab.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TimeModifiedField));
		System.out.println(hmc.TimeModifiedField.getAttribute("value"));
		Assert.assertEquals(hmc.TimeModifiedField.getAttribute("value"), getDate());
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		
		hmc.TCPN.clear();
		sel = new Select(hmc.CatalogVersionDropDown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=34,dependsOnMethods="login")
	public void verify_ChangeReleaseStatustoApproved()
	{
		test=extent.createTest("verify_ChangeReleaseStatustoApproved");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.searchPartNum("5-406726-2"), true,"No Results for this part number");
		hmc.TCPN_Results.click();
		select(hmc.TCPN_releasestatusDropdown, "APPROVED");
		hmc.Save.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.AdministratorTab));
		hmc.AdministratorTab.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TimeModifiedField));
		System.out.println(hmc.TimeModifiedField.getAttribute("value"));
		Assert.assertEquals(hmc.TimeModifiedField.getAttribute("value"), getDate());
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		
		hmc.TCPN.clear();
		sel = new Select(hmc.CatalogVersionDropDown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=35,dependsOnMethods="login")
	public void verify_ChangeReleaseStatustoNotApplicable()
	{
		test=extent.createTest("verify_ChangeReleaseStatustoNotApplicable");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.searchPartNum("5-406726-2"), true,"No Results for this part number");
		hmc.TCPN_Results.click();
		select(hmc.TCPN_releasestatusDropdown, "NOTAPPLICABLE");
		hmc.Save.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.AdministratorTab));
		hmc.AdministratorTab.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.TimeModifiedField));
		System.out.println(hmc.TimeModifiedField.getAttribute("value"));
		Assert.assertEquals(hmc.TimeModifiedField.getAttribute("value"), getDate());
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		
		hmc.TCPN.clear();
		sel = new Select(hmc.CatalogVersionDropDown);
		sel.selectByValue("-1");
	}
	
	@Test(priority=36,dependsOnMethods="login")
	public void verify_ReplacementPartsSearch()
	{
		test=extent.createTest("verify_ReplacementPartsSearch");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ReplacementPartsSearch.click();
		switchWindow("Replacement Part search - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=37,dependsOnMethods="login")
	public void verify_PartAliases()
	{
		test=extent.createTest("verify_PartAliases");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.PartAliasesSearch));
		hmc.PartAliasesSearch.click();
		switchWindow("Part Alias search - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=38,dependsOnMethods="login")
	public void verify_MarketingBrand()
	{
		test=extent.createTest("verify_MarketingBrand");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.MarketingBrandSearch));
		hmc.MarketingBrandSearch.click();
		switchWindow("Marketing Brand search - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=39,dependsOnMethods="login")
	public void verify_SuperCategories()
	{
		test=extent.createTest("verify_SuperCategories");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.SuperCategoriesSearch));
		hmc.SuperCategoriesSearch.click();
		switchWindow("Category search - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=40,dependsOnMethods="login")
	public void verify_ProductCode()
	{
		test=extent.createTest("verify_ProductCode");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.ProductCodeSearch));
		hmc.ProductCodeSearch.click();
		switchWindow("Product Code search - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=41,dependsOnMethods="login")
	public void verify_DerivedFrom()
	{
		test=extent.createTest("verify_DerivedFrom");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.DerivedFromSearch));
		hmc.DerivedFromSearch.click();
		switchWindow("Part search - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=42,dependsOnMethods="login")
	public void verify_ApplicationFacet()
	{
		test=extent.createTest("verify_ApplicationFacet");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.Applications_Facet.click();
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.BU.isDisplayed(), true,"BU Field is displayed");
		assertEquals(hmc.BU_Contact.isDisplayed(), true,"BU contact Field is displayed");
	}
	
	@Test(priority=43,dependsOnMethods="login")
	public void verify_IndustriesFacet()
	{
		test=extent.createTest("verify_IndustriesFacet");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.IndustriesFacet.click();
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.BU.isDisplayed(), true,"BU Field is displayed");
		assertEquals(hmc.BU_Contact.isDisplayed(), true,"BU contact Field is displayed");
	}
	
	@Test(priority=44,dependsOnMethods="login")
	public void verify_SeriesFacet()
	{
		test=extent.createTest("verify_SeriesFacet");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.SeriesFacet.click();
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.BU.isDisplayed(), true,"BU Field is displayed");
		assertEquals(hmc.BU_Contact.isDisplayed(), true,"BU contact Field is displayed");
	}
	
	@Test(priority=45,dependsOnMethods="login")
	public void verify_ProductFamiliesFacet()
	{
		test=extent.createTest("verify_ProductFamiliesFacet");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ProductFamiliesFacet.click();
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.BU.isDisplayed(), true,"BU Field is displayed");
		assertEquals(hmc.BU_Contact.isDisplayed(), true,"BU contact Field is displayed");
	}
	
	@Test(priority=46,dependsOnMethods="login")
	public void verify_StandardFacet()
	{
		test=extent.createTest("verify_StandardFacet");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.StandardFacet.click();
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.BU.isDisplayed(), true,"BU Field is displayed");
		assertEquals(hmc.BU_Contact.isDisplayed(), true,"BU contact Field is displayed");
	}
	
	@Test(priority=47,dependsOnMethods="login")
	public void verify_TEDocumentLink()
	{
		test=extent.createTest("verify_TEDocumentLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.TEDocumentLink.click();
	}
	
	@Test(priority=48,dependsOnMethods="login")
	public void verify_ImageGroupLink()
	{
		test=extent.createTest("verify_ImageGroupLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ImageGroupLink.click();
	}
	
	@Test(priority=49,dependsOnMethods="login")
	public void verify_LegacyImagesLink()
	{
		test=extent.createTest("verify_LegacyImagesLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.LegacyImagesLink.click();
	}
	
	@Test(priority=50,dependsOnMethods="login")
	public void verify_ProductGroupsLink()
	{
		test=extent.createTest("verify_ProductGroupsLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ProductGroupsLink.click();
	}
	
	@Test(priority=51,dependsOnMethods="login")
	public void verify_ECCNDataLink()
	{
		test=extent.createTest("verify_ECCNDataLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ECCNDataLink.click();
	}
	
	@Test(priority=52,dependsOnMethods="login")
	public void verify_UnitsLink()
	{
		test=extent.createTest("verify_UnitsLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.UnitsLink.click();
	}
	
	@Test(priority=53,dependsOnMethods="login")
	public void verify_CatalogManagementToolsFolder()
	{
		test=extent.createTest("verify_CatalogManagementToolsFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CatalogManagementToolsFolderLink.click();
		hmc.CatelogOverviewLink.click();
		hmc.CatelogVersionDiffLink.click();
		hmc.DuplicateIdentifiersLink.click();
		hmc.CatalogManagementToolsFolderLink.click();
	}
	
	@Test(priority=54,dependsOnMethods="login")
	public void verify_ClassificationSystems()
	{
		test=extent.createTest("verify_ClassificationSystems");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.MultithreadedSynchronizationLink.click();
	}
	
	@Test(priority=55,dependsOnMethods="login")
	public void verify_UserFolderLink()
	{
		test=extent.createTest("verify_UserFolderLink");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.UserFolderLink.click();
		assertEquals(hmc.EmployeesLink.isDisplayed(), true,"EmployeesLink is not displayed");
	}
	
	@Test(priority=56,dependsOnMethods="login")
	public void verify_InternationalizationFolderLink()
	{
		test=extent.createTest("verify_InternationalizationFolderLink");
		test.log(Status.INFO, "Test Case Execution started");

		hmc.InternationalizationFolder.click();
		assertEquals(hmc.LanguagesLink.isDisplayed(), true,"LanguagesLink is not displayed");
		assertEquals(hmc.CurrenciesLink.isDisplayed(), true,"CurrenciesLink is not displayed");
		assertEquals(hmc.CountriesLink.isDisplayed(), true,"CountriesLink is not displayed");
		assertEquals(hmc.RegionsLink.isDisplayed(), true,"RegionsLink is not displayed");
		hmc.InternationalizationFolder.click();
	}
	
	@Test(priority=57,dependsOnMethods="login")
	public void verify_ICSFolder()
	{
		test=extent.createTest("verify_ICSFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ICSFolderLink.click();
		assertEquals(hmc.ICSPermissionsLink.isDisplayed(), true,"ICSPermissionsLink is not displayed");
		assertEquals(hmc.ICSContactLink.isDisplayed(), true,"ICSContactLink is not displayed");
		assertEquals(hmc.ProductLineLink.isDisplayed(), true,"ProductCodeLink is not displayed");
		assertEquals(hmc.ProductCodeLink.isDisplayed(), true,"RegionsLink is not displayed");
		assertEquals(hmc.CustomInterceptorsLink.isDisplayed(), true,"CustomInterceptorsLink is not displayed");
		hmc.ICSFolderLink.click();
	}
	
	@Test(priority=58,dependsOnMethods="login")
	public void verify_ImportCockpitFolder()
	{
		test=extent.createTest("verify_ImportCockpitFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ImportCockpitFolder.click();
		assertEquals(hmc.ImportCronJobsLink.isDisplayed(), true,"ImportCronJobsLink is not displayed");
		assertEquals(hmc.ImportInputMediasLink.isDisplayed(), true,"ImportInputMediasLink is not displayed");
		assertEquals(hmc.MappingsLink.isDisplayed(), true,"MappingsLink is not displayed");
		assertEquals(hmc.ImpExImportMedias.isDisplayed(), true,"ImpExImportMedias is not displayed");
		hmc.ImportCockpitFolder.click();
	}
	
	@Test(priority=59,dependsOnMethods="login")
	public void verify_ItemExportFolder()
	{
		test=extent.createTest("verify_ItemExportFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ItemExportFolder.click();
		assertEquals(hmc.GenericItemExportJobLink.isDisplayed(), true,"GenericItemExportJobLink is not displayed");
		assertEquals(hmc.ColumnDefinitionLink.isDisplayed(), true,"ColumnDefinitionLink is not displayed");
		assertEquals(hmc.ExportConfigurationLink.isDisplayed(), true,"ExportConfigurationLink is not displayed");
		hmc.ItemExportFolder.click();
	}
	
	@Test(priority=60,dependsOnMethods="login")
	public void verify_UserNameDisplayed()
	{
		test=extent.createTest("verify_UserNameDisplayed");
		test.log(Status.INFO, "Test Case Execution started");
		
		assertEquals(hmc.DPSUSER2.isDisplayed(), true,"DPSUSER2 is not displayed");
	}
	
	@Test(priority=61,dependsOnMethods="login")
	public void verify_SystemFolder()
	{
		test=extent.createTest("verify_SystemFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.SystemFolder.click();
		hmc.CronJobsLink.click();
		//hmc.ItemChangesLink.click();
		hmc.SystemFolder.click();
	}
	
	@Test(priority=62,dependsOnMethods="login")
	public void verify_DeeplinkURLsFolder()
	{
		test=extent.createTest("verify_DeeplinkURLsFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.DeepLinkUrls.click();
		assertEquals(hmc.DeepLinkURLs.isDisplayed(), true,"DeepLinkURLs is not displayed");
		assertEquals(hmc.DeepLinkURLRules.isDisplayed(), true,"DeepLinkURLRules is not displayed");
		hmc.DeepLinkUrls.click();
	}
	
	@Test(priority=63,dependsOnMethods="login")
	public void verify_CockpitFolder()
	{
		test=extent.createTest("verify_CockpitFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CockpitFolder.click();
		assertEquals(hmc.ItemTemplatesLink.isDisplayed(), true,"ItemTemplatesLink is not displayed");
		assertEquals(hmc.UIComponenetConfigurationsLink.isDisplayed(), true,"UIComponenetConfigurationsLink is not displayed");
		assertEquals(hmc.UICompoenentAccessRightsLink.isDisplayed(), true,"UICompoenentAccessRightsLink is not displayed");
		hmc.CockpitFolder.click();
	}
	
	@Test(priority=64,dependsOnMethods="login")
	public void verify_ScriptingFolder()
	{
		test=extent.createTest("verify_ScriptingFolder");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ScriptingFolder.click();
		assertEquals(hmc.ScriptsLink.isDisplayed(), true,"ScriptsLink is not displayed");
		assertEquals(hmc.ScriptingJobsLink.isDisplayed(), true,"ScriptingJobsLink is not displayed");
		assertEquals(hmc.ScriptingTasksLink.isDisplayed(), true,"ScriptingTasksLink is not displayed");
		assertEquals(hmc.DynamicProcessDefinitionLink.isDisplayed(), true,"DynamicProcessDefinitionLink is not displayed");
		hmc.ScriptingFolder.click();
	}
	
	@Test(priority=65,dependsOnMethods="login")
	public void verify_AttributeDropdown()
	{
		test=extent.createTest("verify_AttributeDropdown");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.Parts.click();
		hmc.AttributeDropdown.click();
		threadSleepWait(2000);
		hmc.AttributeDropdown.click();
	}
	
	@Test(priority=66,dependsOnMethods="login")
	public void verify_CatalogVersionFilterDropdown()
	{
		test=extent.createTest("verify_CatalogVersionFilterDropdown");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CatalogVersionFilterDropdown.click();
		threadSleepWait(2000);
		hmc.CatalogVersionFilterDropdown.click();
	}
	
	@Test(priority=67,dependsOnMethods="login")
	public void verify_LanguageDropdown()
	{
		test=extent.createTest("verify_LanguageDropdown");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.LanguageDropdown.click();
		threadSleepWait(2000);
		hmc.LanguageDropdown.click();
	}
	
	@Test(priority=68,dependsOnMethods="login")
	public void verify_SettingsIcon()
	{
		test=extent.createTest("verify_SettingsIcon");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.SettingsIcon.click();
		switchWindow("Editor: User Profile - hybris Management Console (hMC)");
		driver.close();
		switchWindow("Explorer: DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
					  
	}
	
	@Test(priority=69,dependsOnMethods="login")
	public void verify_TCPNPartNumberonTEApplication()
	{
		test=extent.createTest("verify_TCPNPartNumberonTEApplication");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.promotestatusDropdown));
		select(hmc.promotestatusDropdown, "PROMOTED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.releasestatusDropdown));
		select(hmc.releasestatusDropdown, "APPROVED");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.legacystatusDropdown));
		select(hmc.legacystatusDropdown, "Active");
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.Search));
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		String TCPN_Results_partNumber = hmc.TCPNResults_PartNumber.getText();
		
		sel = new Select(hmc.promotestatusDropdown);
		sel.selectByValue("-1");
		
		sel = new Select(hmc.releasestatusDropdown);
		sel.selectByValue("-1");
		
		sel = new Select(hmc.legacystatusDropdown);
		sel.selectByValue("-1");
		
		WebDriver Appdriver = new ChromeDriver();
		Appdriver.get("https://www-qa.te.com/usa-en/home.html");
		Appdriver.findElement(By.xpath("//input[@class='search-resizing-input']")).sendKeys(TCPN_Results_partNumber);
		Appdriver.findElement(By.xpath("//input[@type='submit']")).click();
		Appdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		WebElement active = Appdriver.findElement(By.xpath("//a[@class='active-tag ng-isolate-scope']//span"));
		assertEquals(active.isDisplayed(), true,"ACTIVE is not displayed");
		Appdriver.close();
	}
	
	@Test(priority=70,dependsOnMethods="login")
	public void verify_ReplacementPartNumberonTEApplication()
	{
		test=extent.createTest("verify_ReplacementPartNumberonTEApplication");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.TCPN.sendKeys("145154-1");
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		hmc.Categories_OpenEditor.click();
		String UA_ReplacementPartNumber_Hybris = hmc.UA_ReplacementPartNumber.getAttribute("value");
		System.out.println("Replacement part number is "+ UA_ReplacementPartNumber_Hybris);
		
		WebDriver Appdriver = new ChromeDriver();
		Appdriver.get("https://www-qa.te.com/usa-en/home.html");
		Appdriver.findElement(By.xpath("//input[@class='search-resizing-input']")).sendKeys("145154-1");
		Appdriver.findElement(By.xpath("//input[@type='submit']")).click();
		Appdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String UA_ReplacementPartNumber_Application = Appdriver.findElement(By.xpath("//*[@id='te-body']/div/section[3]/div[1]/div[1]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[2]/div/span/a")).getText();
		System.out.println("App replacementt part number is" + UA_ReplacementPartNumber_Application );
		assertEquals(UA_ReplacementPartNumber_Hybris, UA_ReplacementPartNumber_Application);
		Appdriver.close();
	}
	
	@Test(priority=71,dependsOnMethods="login")
	public void verify_ModelPartNumber()
	{
		test=extent.createTest("verify_ModelPartNumber");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		explicitWait().until(ExpectedConditions.visibilityOf(hmc.TCPN));
		hmc.TCPN.clear();
		threadSleepWait(3000);
		
		hmc.TCPN.sendKeys("CAT-AM7801-T618");
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		hmc.Categories_OpenEditor.click();
		hmc.UA_ModelPartIndicator.isEnabled();
		String UA_DerivedPartNumber_Hybris = hmc.UA_DerivedPartNumber.getText();
		System.out.println("Derived part number is"+ UA_DerivedPartNumber_Hybris);
		
		WebDriver Appdriver = new ChromeDriver();
		Appdriver.get("https://www-qa.te.com/usa-en/home.html");
		Appdriver.findElement(By.xpath("//input[@class='search-resizing-input']")).sendKeys(UA_DerivedPartNumber_Hybris);
		Appdriver.findElement(By.xpath("//input[@type='submit']")).click();
		Appdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Appdriver.close();
	}
	
	@Test(priority=72,dependsOnMethods="login")
	public void verify_CompatiblePartNumber()
	{
		test=extent.createTest("verify_CompatiblePartNumber");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		explicitWait().until(ExpectedConditions.visibilityOf(hmc.TCPN));
		hmc.TCPN.clear();
		threadSleepWait(3000);
		
		hmc.TCPN.sendKeys("770854-1");
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.UA_EligibleForCompatibleParts.isDisplayed(), true,"UA_EligibleForCompatibleParts is not displayed");
		
		WebDriver Appdriver = new ChromeDriver();
		Appdriver.get("https://www-qa.te.com/usa-en/product-58529-1.html?c=539310&compatible=770854-1");
		Appdriver.close();
	}
	
	@Test(priority=73,dependsOnMethods="login")
	public void verify_ActivePartPriceCheck()
	{
		test=extent.createTest("verify_ActivePartPriceCheck");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		explicitWait().until(ExpectedConditions.visibilityOf(hmc.TCPN));
		hmc.TCPN.clear();
		threadSleepWait(3000);
		
		hmc.TCPN.sendKeys("58529-1");
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		hmc.Categories_OpenEditor.click();
		hmc.PricesTab.click();
		
		System.out.println(hmc.PriceValue.getAttribute("value"));
		
		WebDriver Appdriver = new ChromeDriver();
		Appdriver.get("https://www-qa.te.com/usa-en/product-58529-1.html?c=539310&compatible=770854-1");
		Appdriver.close();
	}
	
	@Test(priority=74,dependsOnMethods="login")
	public void verify_RestrictedPart()
	{
		test=extent.createTest("verify_RestrictedPart");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		explicitWait().until(ExpectedConditions.visibilityOf(hmc.TCPN));
		hmc.TCPN.clear();
		threadSleepWait(3000);
		
		hmc.TCPN.sendKeys("1-2208397-1");
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		hmc.Categories_OpenEditor.click();
		hmc.UA_SalesIntent.isDisplayed();
		System.out.println(hmc.UA_SalesIntent.getText());
		
		WebDriver Appdriver = new ChromeDriver();
		Appdriver.get("https://www-qa.te.com/usa-en/home.html");
		Appdriver.close();
	}
	
	@Test(priority=75,dependsOnMethods="login")
	public void verify_CreatePart()
	{
		test=extent.createTest("verify_CreatePart");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		explicitWait().until(ExpectedConditions.visibilityOf(hmc.TCPN));
		hmc.TCPN.clear();
		threadSleepWait(3000);
		
		hmc.ToolBar_New.click();
		hmc.ToolBar_DropdownPart.click();
	}
	
	@Test(priority=76,dependsOnMethods="login")
	public void verify_SavedQueries()
	{
		test=extent.createTest("verify_SavedQueries");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.search_Link));
		hmc.search_Link.click();
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.SavedQueries));
		hmc.SavedQueries.click();
		threadSleepWait(3000);
		hmc.SavedQueries.click();
	}
	
	@Test(priority=77,dependsOnMethods="login")
	public void verify_BackForwardButton()
	{
		test=extent.createTest("verify_BackForwardButton");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.BackButton));
		hmc.BackButton.click();
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(hmc.ForwardButton));
		hmc.ForwardButton.click();
	}
	
	@Test(priority=78,dependsOnMethods="login")
	public void verify_CloseSession()
	{
		
		test=extent.createTest("verify_CloseSession");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CloseSession.click();
		threadSleepWait(5000);
		//driver.quit();
	}
	
	public static String getScreenShot(WebDriver driver){
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir"+"/Screenshot/"+System.currentTimeMillis());
		File destination = new File(path);
		try {
			org.apache.commons.io.FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed"+ e.getMessage());
		}
		return path;
	}
}
