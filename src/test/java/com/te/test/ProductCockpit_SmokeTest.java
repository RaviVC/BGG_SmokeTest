package com.te.test;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.te.hybris.pages.PC;
import com.te.util.GenericUtils;

public class ProductCockpit_SmokeTest extends GenericUtils {
	PC pcp;
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void reportSettings(){
		Date date = new Date();
		String filename = System.getProperty("user.dir")+"\\ExtentReports\\ProductCockpit_SmokeTestResults"+date.getTime()+".html";
		htmlreports = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlreports);
		
		htmlreports.config().setReportName("ProductCockpit_Smoke Test Automation Results");
		htmlreports.config().setTheme(Theme.STANDARD);
		htmlreports.config().setTestViewChartLocation(ChartLocation.TOP);
		
		extent.setSystemInfo("UserName", "TE328511");
		extent.setSystemInfo("HostName", "INQ69NBO5B4JG32");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Selenium", "2.5.3");
		extent.setSystemInfo("Java", "1.8");
	}
	
	@AfterClass
	public void afterClass(){
		extent.flush();
	}
	
	@AfterMethod
	public void reportTearDown(ITestResult result){
		if(result.getStatus()==result.FAILURE){
			test.log(Status.FAIL, result.getName()+"     " + " is Failed " + result.getThrowable());
		}
		if(result.getStatus()==result.SUCCESS){
			test.log(Status.PASS, result.getName()+ "Passed");
		}
		if(result.getStatus()==result.SKIP){
			test.log(Status.SKIP, result.getName()+ "Skipped");
		}
	}
	
	@BeforeTest
	public void setup(){
		//configure();
		pcp = PageFactory.initElements(driver, PC.class);	
	}
	
	@Parameters({ "Environment" })
	@Test(priority=0)
	public void PC_loginTest(String Environment){
		
		test=extent.createTest("PC_loginTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		launchApp(CONFIG.getProperty("PC_URL"),Environment);
		extent.setSystemInfo("Environment", Environment);
		explicitWait().until(ExpectedConditions.elementToBeClickable(pcp.Username));
		pcp.Username.clear();
		threadSleepWait(3000);
		pcp.Username.sendKeys(CONFIG.getProperty("username"));
		pcp.Password.clear();
		threadSleepWait(3000);
		pcp.Password.sendKeys(CONFIG.getProperty("password"));
		pcp.Login.click();
		try {
			explicitWait().until(ExpectedConditions.titleIs("hybris Product Cockpit"));
		} catch (Exception e) {
			Reporter.log(driver.findElement(By.xpath("//span[@class='loginErrorLabel z-label']")).getText(),true);
			Assert.assertEquals(false, true,"Invalid Credentials");
		}
	}
	
	@Test(priority=1,dependsOnMethods="PC_loginTest")
	public void PC_SearchTest(){
		
		test=extent.createTest("PC_SearchTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pcp.searchTextBox));
		pcp.searchPartNum("31264");
		threadSleepWait(3000);
		explicitWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[div[span[text()='Description [en]']]]/div[2]")));	
		Actions act= new Actions(driver);
		act.doubleClick(pcp.searchResultClick).perform();
		threadSleepWait(2000);
		System.out.println("clicked");
		explicitWait().until(ExpectedConditions.visibilityOf(pcp.ImageGroup));
	}
	
	@Test(priority=2,dependsOnMethods="PC_SearchTest")
	public void PC_ImageGroupTest(){
		
		test=extent.createTest("PC_ImageGroupTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.visibilityOf(pcp.ImageGroup));
		if(pcp.ImageGroupChildElement.isDisplayed()){
			Assert.assertEquals(pcp.ImageGroupChildElement.isDisplayed(),true,"image group tab is not visible");
		}
		else{
			pcp.ImageGroup.click();
			Assert.assertEquals(pcp.ImageGroupChildElement.isDisplayed(),true,"image group tab is not visible");
		}
	}
	
	@Test(priority=3,dependsOnMethods="PC_SearchTest")
	public void PC_CPRAttributeTest(){	
		
		test=extent.createTest("PC_CPRAttributeTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		if(pcp.LegacyStatus.isDisplayed()){
			Assert.assertEquals(pcp.LegacyStatus.isDisplayed(),true,"image group tab is not visible");
		}
		else{
			pcp.CPRATTRIBUTES.click();	
			Assert.assertEquals(pcp.LegacyStatus.isDisplayed(),true,"PC_CPRAttributeTest legacy status is not visible");
		}
	}
	
	@Test(priority=4,dependsOnMethods="PC_SearchTest")
	public void PC_ComplianceAttrTest(){	
		
		test=extent.createTest("PC_ComplianceAttrTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(pcp.EU_ELV_Compliance.isDisplayed(),true,"image group tab is not visible");
		Assert.assertEquals(pcp.EU_RoHS_Compliance.isDisplayed(),true,"image group tab is not visible");
		
	}
	
	@Test(priority=5,dependsOnMethods="PC_SearchTest")
	public void PC_SamplesTest(){
		
		test=extent.createTest("PC_SamplesTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		System.out.println("Test started");
		Assert.assertEquals(pcp.SAMPLES_indicator.isDisplayed(),true,"Samples indicator is not visible");
		System.out.println("Test ended");
	}
	
	@Test(priority=6,dependsOnMethods="PC_SearchTest")
	public void PC_SamplesIndicatorVerifyPencilButtonTest(){
		
		test=extent.createTest("PC_SamplesIndicatorVerifyPencilButtonTest");
		test.log(Status.INFO, "Test Case Execution started");
		System.out.println("SamplesIndicatorVerifyPencilButtonTest Test started");
		pcp.searchPartNum("31264");
		System.out.println("part num searched");
		explicitWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[div[span[text()='Description [en]']]]/div[2]")));
		threadSleepWait(6000);
		Actions act= new Actions(driver);
		act.doubleClick(pcp.searchResultClick).perform();
		System.out.println("clicked");
		explicitWait().until(ExpectedConditions.visibilityOf(pcp.ImageGroup));	
		Assert.assertEquals(pcp.SAMPLES_indicatorFieldValue.isDisplayed(),true,"Samples indicator filed value is not true");
		Assert.assertEquals(pcp.SAMPLES_indicatorPencilButton.isDisplayed(),true,"Samples indicator pencil button is not visible");
		System.out.println("SamplesIndicatorVerifyPencilButtonTest Test ended");
	}
	
	@Test(priority=7)
	public void PC_AdvancedSearch(){
		
		test=extent.createTest("PC_AdvancedSearch");
		test.log(Status.INFO, "Test Case Execution started");
		
		threadSleepWait(6000);
		explicitWait().until(ExpectedConditions.visibilityOf(pcp.PC_AdvancedSearch_link));
		pcp.PC_AdvancedSearch_link.click();
		System.out.println("clicked");
		threadSleepWait(6000);
		explicitWait().until(ExpectedConditions.visibilityOf(pcp.PC_AdvancedSearch_TCPN_Textbox));
		pcp.PC_AdvancedSearch_TCPN_Textbox.click();
		threadSleepWait(2000);
		pcp.PC_AdvancedSearch_containsTextbox.sendKeys("1532251-7");
		threadSleepWait(2000);
		pcp.PC_AdvancedSearch_contains_apply.click();
		threadSleepWait(6000);
		
		pcp.PC_ProductCode_Textbox.click();
		pcp.PC_AdvancedSearch_containsTextbox.sendKeys("K429");
		threadSleepWait(2000);
		pcp.PC_AdvancedSearch_contains_apply.click();
		threadSleepWait(6000);
		Actions sct = new Actions(driver);
		sct.moveToElement(pcp.PC_ProductCode_Textbox).build().perform();
		System.out.println("action executed");
		driver.findElement(By.xpath("//div[@class='advanceSearchContainer']//div[@class='advSearchBottomToolbar']//span[@title='Search']//img")).click();
		System.out.println("done");
		threadSleepWait(10000);
	}

}
