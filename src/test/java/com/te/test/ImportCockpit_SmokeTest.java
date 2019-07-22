package com.te.test;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
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
import com.te.hybris.pages.IC;
import com.te.util.GenericUtils;

public class ImportCockpit_SmokeTest extends GenericUtils {
	
	IC ic;
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void reportSettings(){
		Date date = new Date();
		String filename = System.getProperty("user.dir")+"\\ExtentReports\\ImportCockpit_SmokeTestResults"+date.getTime()+".html";
		
		htmlreports = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlreports);
		
		htmlreports.config().setReportName("Hybris_Smoke Test Automation Results");
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
			test.log(Status.FAIL, result.getName()+ "     "+ " is Failed "+ result.getThrowable());
		}
		if(result.getStatus()==result.SUCCESS){
			test.log(Status.PASS, result.getName()+ " Passed ");
		}
		if(result.getStatus()==result.SKIP){
			test.log(Status.SKIP, result.getName()+ " Skipped ");
		}
	}
	
	@BeforeTest
	public void setup(){
		//configure();
		ic = PageFactory.initElements(driver, IC.class);
	}
	
	@Parameters({ "Environment" })
	@Test(priority=0)
	public void IC_Login(String Environment){
		
		test=extent.createTest("IC_Login");
		test.log(Status.INFO, "Test Case Execution started");

		launchApp(CONFIG.getProperty("IC_URL"),Environment);
		extent.setSystemInfo("Environment", Environment);
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Username));
		ic.Username.clear();
		threadSleepWait(3000);
		ic.Username.sendKeys(CONFIG.getProperty("username"));
		ic.Password.clear();
		threadSleepWait(3000);
		ic.Password.sendKeys(CONFIG.getProperty("password"));
		ic.Login.click();
	}
	
	@Test(priority=1,dependsOnMethods="IC_Login")
	public void massSampleExport(){
		
		test=extent.createTest("massSampleExport");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.MassPromoteExport));
		ic.MassPromoteExport.click();
		threadSleepWait(12000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteriaDropdown));
		ic.ExportCriteriaDropdown.click();
		threadSleepWait(2000);
		ic.ExportCriteria_ByEndNode.click();
		threadSleepWait(2000);
		ic.EndNodeDropDown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNode_ACContactors));
		ic.EndNode_ACContactors.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.StartJob));
		ic.StartJob.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Outputfile_DownloadIcon));
		ic.Outputfile_DownloadIcon.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Download_Button));
		ic.Download_Button.click();
		threadSleepWait(5000);
		System.out.println("MassSampleExport completed");
		
		//Code for selecting the EndNode Dropdown options 
		/*for (int j = 0; j < driver.findElements(By.xpath("//body/div[3]//tr")).size(); j++) {
			String xp="//body/div[3]//tr["+j+"]/td[2]";
			System.out.println(driver.findElement(By.xpath(xp)).getText());
			
		}*/
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
