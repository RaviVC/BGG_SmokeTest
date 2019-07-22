package com.te.test;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
import com.te.hybris.pages.IC;
import com.te.util.GenericUtils;

public class ImportCockpit_RegressionTest extends GenericUtils {
	
	IC ic;
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	
	
	
	@BeforeClass
	public void reportSettings()
	{
		Date date = new Date();
		String filename = System.getProperty("user.dir")+"\\ExtentReports\\ImportCockpit_RegressionTestResults"+date.getTime()+".html";
		
		htmlreports = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlreports);
		
		htmlreports.config().setReportName("Hybris Regression");
		htmlreports.config().setTheme(Theme.STANDARD);
		htmlreports.config().setTestViewChartLocation(ChartLocation.TOP);
		
		extent.setSystemInfo("OS", "Windows");
//		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Selnium", "2.4.3");
		extent.setSystemInfo("Java", "1.8");
	}
	
	@AfterClass
	public void afterClass()
	{
		extent.flush();
	}
	
	@BeforeTest
	public void setup()
	{
		//configure();
		ic = PageFactory.initElements(driver, IC.class);
	}
	
	@AfterMethod()
	public void afterMethod(ITestResult result)
	{
		if(result.getStatus()==result.FAILURE){
			test.log(Status.FAIL, result.getName()+      "  "
					+ "       is Failed ->>     "    +    result.getThrowable());
			
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
	public void IC_login(String Environment)
	{
		test=extent.createTest("verify the IC Login");
		test.log(Status.INFO, "Test Case Execution started");
		
		launchApp(CONFIG.getProperty("IC_URL"),Environment);
		threadSleepWait(10000);
		extent.setSystemInfo("Environment", Environment);
//		driver.get(CONFIG.getProperty("IC_URL"));
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Username));
		ic.Username.clear();
		threadSleepWait(2000);
		ic.Username.sendKeys(CONFIG.getProperty("username"));
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Password));
		ic.Password.clear();
		ic.Password.sendKeys(CONFIG.getProperty("password"));
		ic.Login.click();
	}
	
	@Test(priority=1,dependsOnMethods="IC_login")
	public void PartImageExport()
	{
		test=extent.createTest("verify PartImageExport");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.PartImageExport));
		ic.PartImageExport.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteriaDropdown));
		ic.ExportCriteriaDropdown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteria_ByEndNode));
		ic.ExportCriteria_ByEndNode.click();
		threadSleepWait(2000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNodeDropDown));
		ic.EndNodeDropDown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNode_ACContactors));
		ic.EndNode_ACContactors.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.StartJob));
		ic.StartJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Outputfile_DownloadIcon));
		ic.Outputfile_DownloadIcon.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Download_Button));
		ic.Download_Button.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EditMedia_CloseIcon));
		ic.EditMedia_CloseIcon.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.WelcomeDPSUser2));
		ic.WelcomeDPSUser2.click();
	}
	
	
	@Test(priority=2,dependsOnMethods="IC_login")
	public void PartImageImport()
	{
		test=extent.createTest("verify PartImageImport");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.PartImageImport));
		ic.PartImageImport.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.uploadbutton));
		threadSleepWait(10000);
		ic.uploadbutton.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.FileUpload_closebutton));
		ic.FileUpload_closebutton.click();
		threadSleepWait(3000);
		
	}
	
	
	@Test(priority=3,dependsOnMethods="IC_login")
	public void GlobalFacetExport()
	{
		test=extent.createTest("Verify GlobalFacetExport");
		test.log(Status.INFO, "Test Case Execution started");
		
		ic.WelcomeDPSUser2.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.GlobalFacetExport));
		ic.GlobalFacetExport.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteriaDropdown));
		ic.ExportCriteriaDropdown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteria_ByEndNode));
		ic.ExportCriteria_ByEndNode.click();
		threadSleepWait(2000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNodeDropDown));
		ic.EndNodeDropDown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNode_ACContactors));
		ic.EndNode_ACContactors.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.StartJob));
		ic.StartJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Outputfile_DownloadIcon));
		ic.Outputfile_DownloadIcon.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Download_Button));
		//ic.Download_Button.click();
		driver.findElement(By.xpath("//td[contains(text(),'Download')]"));
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EditMedia_CloseIcon));
		ic.EditMedia_CloseIcon.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.WelcomeDPSUser2));
		ic.WelcomeDPSUser2.click();
		
	}
	
	@Test(priority=4,dependsOnMethods="IC_login")
	public void GlobalFacetImport()
	{
		
		test=extent.createTest("verify GlobalFacetImport");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.GlobalFacetImport));
		ic.GlobalFacetImport.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.uploadbutton));
		threadSleepWait(10000);
		ic.uploadbutton.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.FileUpload_closebutton));
		ic.FileUpload_closebutton.click();
		threadSleepWait(3000);
	}
	
	@Test(priority=5,dependsOnMethods="IC_login")
	public void MassSampleExport()
	{
		test=extent.createTest("verify MassSampleExport");
		test.log(Status.INFO, "Test Case Execution started");
		
		ic.WelcomeDPSUser2.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.MassSampleExport));
		ic.MassSampleExport.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteriaDropdown));
		ic.ExportCriteriaDropdown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteria_ByEndNode));
		ic.ExportCriteria_ByEndNode.click();
		threadSleepWait(2000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNodeDropDown));
		ic.EndNodeDropDown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNode_ACContactors));
		ic.EndNode_ACContactors.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.StartJob));
		ic.StartJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Outputfile_DownloadIcon));
		ic.Outputfile_DownloadIcon.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Download_Button));
		//ic.Download_Button.click();
		driver.findElement(By.xpath("//td[contains(text(),'Download')]"));
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EditMedia_CloseIcon));
		ic.EditMedia_CloseIcon.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.WelcomeDPSUser2));
		ic.WelcomeDPSUser2.click();
		
	}
	
	@Test(priority=6,dependsOnMethods="IC_login")
	public void MassSampleImport()
	{
		test=extent.createTest("verify MassSampleImport");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.MassSampleImport));
		ic.MassSampleImport.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.uploadbutton));
		threadSleepWait(10000);
		ic.uploadbutton.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.FileUpload_closebutton));
		ic.FileUpload_closebutton.click();
		threadSleepWait(3000);
		
	}
	
	@Test(priority=7,dependsOnMethods="IC_login")
	public void CompatiblePartsExport()
	{
		test=extent.createTest("verify CompatiblePartsExport");
		test.log(Status.INFO, "Test Case Execution started");
		
		ic.WelcomeDPSUser2.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CreateNewJob));
		ic.CreateNewJob.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.CompatiblePartsExport));
		ic.CompatiblePartsExport.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteriaDropdown));
		ic.ExportCriteriaDropdown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.ExportCriteria_ByEndNode));
		ic.ExportCriteria_ByEndNode.click();
		threadSleepWait(2000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNodeDropDown));
		ic.EndNodeDropDown.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EndNode_ACContactors));
		ic.EndNode_ACContactors.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.StartJob));
		ic.StartJob.click();
		threadSleepWait(10000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Outputfile_DownloadIcon));
		ic.Outputfile_DownloadIcon.click();
		threadSleepWait(5000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.Download_Button));
		ic.Download_Button.click();
		//driver.findElement(By.xpath("//td[contains(text(),'Download')]"));
		explicitWait().until(ExpectedConditions.elementToBeClickable(ic.EditMedia_CloseIcon));
		ic.EditMedia_CloseIcon.click();
		threadSleepWait(5000);
		ic.ic_logout();
	}
	
	@Test(priority=8,dependsOnMethods="IC_login")
	public void Logout()
	{
		test=extent.createTest("verify Logout");
		test.log(Status.INFO, "Test Case Execution started");
		
		ic.ic_logout();
		
	}
	
	/*@AfterSuite
	public void browserquit(){
		driver.quit();
	}*/
	
	
	
}
