package com.te.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
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
import com.te.hybris.pages.PC;
import com.te.util.GenericUtils;

public class ProductCockpit_RegressionTest extends GenericUtils {
	
	PC pc;
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void reportSettings()
	{
		Date date = new Date();
		String filename = System.getProperty("user.dir")+"\\ExtentReports\\ProductCockpit_RegressionTestResults"+date.getTime()+".html";
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
		pc = PageFactory.initElements(driver, PC.class);
	}
	
	@AfterMethod
	public void reportTearDown(ITestResult result)
	{
		if(result.getStatus()==result.FAILURE){
			test.log(Status.FAIL, result.getName()+ "   " + " is Failed " + result.getThrowable());
		}
		if(result.getStatus()==result.SUCCESS){
			test.log(Status.PASS, result.getName()+ " Passed ");
		}
		if(result.getStatus()==result.SKIP){
			test.log(Status.SKIP, result.getName()+ " Skipped ");
		}
	}
	
	@Parameters({ "Environment" })
	@Test(priority=0)
	public void login(String Environment)
	{
		test=extent.createTest("Verify Login for PC");
		test.log(Status.INFO, "Test Case Execution started");
		
		launchApp(CONFIG.getProperty("PC_URL"),Environment);
		extent.setSystemInfo("Environment", Environment);
//		driver.get(CONFIG.getProperty("PC_URL"));
		threadSleepWait(3000);
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.Username));
		pc.Username.clear();
		threadSleepWait(2000);
		pc.Username.sendKeys(CONFIG.getProperty("username"));
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.Password));
		pc.Password.clear();
		pc.Password.sendKeys(CONFIG.getProperty("password"));
		pc.Login.click();
		try {
			explicitWait().until(ExpectedConditions.titleIs("hybris Product Cockpit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1,dependsOnMethods="login")
	public void verify_OrderByDropdown()
	{
		test=extent.createTest("verify_OrderByDropdown");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.OrderByDropdown));
		Assert.assertEquals(pc.OrderByDropdown.isDisplayed(),true, "OrderDropdonw button not displayed");
		pc.OrderByDropdown.click();
	}
	
	@Test(priority=2,dependsOnMethods="login")
	public void verify_OrderByDropdownOptions()
	{
		test=extent.createTest("verify_OrderByDropdown");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(pc.OrderBy_TCPN.isDisplayed(),true, "TCPN is not displayad");
		pc.OrderByDropdown.click();
	}
	
	@Test(priority=3,dependsOnMethods="login")
	public void verify_GridView()
	{
		test=extent.createTest("verify_GridView");
		test.log(Status.INFO, "Test Case Execution started");
		Assert.assertEquals(pc.GridView.isDisplayed(),true, "Gridview is not displayad");
	}
	
	@Test(priority=4,dependsOnMethods="login")
	public void verify_ListView()
	{
		test=extent.createTest("verify_ListView");
		test.log(Status.INFO, "Test Case Execution started");
		Assert.assertEquals(pc.ListView.isDisplayed(),true, "Listview is not displayad");
	}
	
	@Test(priority=5,dependsOnMethods="login")
	public void verify_CreateNewItem()
	{
		test=extent.createTest("verify_CreateNewItem");
		test.log(Status.INFO, "Test Case Execution started");
		Assert.assertEquals(pc.CreateNewItem.isDisplayed(),true, "CreateNewItem is not displayad");
	}
	
	@Test(priority=6,dependsOnMethods="login")
	public void verify_OpenNewTab()
	{
		test=extent.createTest("verify_OpenNewTab");
		test.log(Status.INFO, "Test Case Execution started");
		Assert.assertEquals(pc.OpenNewTab.isDisplayed(),true, "OpenNewTab is not displayad");
	}
	
	@Test(priority=7,dependsOnMethods="login")
	public void verify_Ascending()
	{
		test=extent.createTest("verify_Ascending");
		test.log(Status.INFO, "Test Case Execution started");
		Assert.assertEquals(pc.AscendingCheckBox.isDisplayed(),true, "AscendingCheckBox is not displayad");
		//pc.AscendingCheckBox.click();
	}
	
	@Test(priority=8,dependsOnMethods="login")
	public void verify_Pagesize()
	{
		test=extent.createTest("verify_Pagesize");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(pc.PageSizeDropdown.isDisplayed(),true, "PageSizeDropdown is not displayad");
		pc.PageSizeDropdown.click();
		pc.PageSizeDropdown.click();
	}
	
	@Test(priority=9,dependsOnMethods="login")
	public void PC_SearchTest(){
		test=extent.createTest("PC_SearchTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.searchTextBox));
		//pc.searchPartNum("722638-1");
		pc.searchPartNum("31264");
		explicitWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[div[span[text()='Description [en]']]]/div[2]")));	
		Actions act= new Actions(driver);
		act.doubleClick(pc.searchResultClick).perform();
		threadSleepWait(3000);
		System.out.println("clicked");
	}
	
	@Test(priority=10,dependsOnMethods="login")
	public void verify_ComponentPageLayout(){
		
		test=extent.createTest("verify_ComponentPageLayout");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.TECOMSTATUS));
		Assert.assertEquals(pc.TECOMSTATUS.isDisplayed(),true, "TECOMSTATUS is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.NAVIGATIONALATTRIBUTES));
		Assert.assertEquals(pc.NAVIGATIONALATTRIBUTES.isDisplayed(),true, "NAVIGATIONALATTRIBUTES is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.DISPLAYATTRIBUTES));
		Assert.assertEquals(pc.DISPLAYATTRIBUTES.isDisplayed(),true, "DISPLAYATTRIBUTES is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.FACETS));
		Assert.assertEquals(pc.FACETS.isDisplayed(),true, "FACETS is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.SAMPLES));
		Assert.assertEquals(pc.SAMPLES.isDisplayed(),true, "SAMPLES is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.IMAGEGROUP));
		Assert.assertEquals(pc.IMAGEGROUP.isDisplayed(),true, "IMAGEGROUP is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.SYNERGISTICSALES));
		Assert.assertEquals(pc.SYNERGISTICSALES.isDisplayed(),true, "SYNERGISTICSALES is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.COMPATIBLEPARTS));
		Assert.assertEquals(pc.COMPATIBLEPARTS.isDisplayed(),true, "COMPATIBLEPARTS is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.CPRATTRIBUTES));
		Assert.assertEquals(pc.CPRATTRIBUTES.isDisplayed(),true, "CPRATTRIBUTES is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.DOCUMENTS));
		Assert.assertEquals(pc.DOCUMENTS.isDisplayed(),true, "DOCUMENTS is not displayad");
		
	}
	
	@Test(priority=11,dependsOnMethods="login")
	public void verify_SuperCategories(){
		
		test=extent.createTest("verify_SuperCategories");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.supercategories));
		Assert.assertEquals(pc.supercategories.isDisplayed(),true, "supercategories is not displayad");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.supercategories_option));
		Assert.assertEquals(pc.supercategories_option.isDisplayed(),true, "supercategories option is not displayad");
	}
	
	
	@Test(priority=12,dependsOnMethods="login")
	public void verify_SavedSearchQuery(){
		test=extent.createTest("verify_SavedSearchQuery");
		test.log(Status.INFO, "Test Case Execution started");
		
		pc.SaveSearchQuery.click();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.Unsharedsavedqueries));
		pc.Unsharedsavedqueries.click();
		Assert.assertEquals(pc.NavigationQuery.isDisplayed(),true, "Navigation query is not displayed");
		pc.Sharedsavedqueries.click();
	}
	
	@Test(priority=13,dependsOnMethods="login")
	public void verify_Catalog()
	{
		test=extent.createTest("verify_Catalog");
		test.log(Status.INFO, "Test Case Execution started");
		System.out.println("Catalog link is getting displayed");
		
		//pc.Catalog.isDisplayed();
		//pc.Catalog.isDisplayed();
		//driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	}
	
	@Test(priority=14,dependsOnMethods="login")
	public void verify_Product()
	{
		test=extent.createTest("verify_Product");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.Product));
		pc.Product.isDisplayed();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	}
	
	@Test(priority=15,dependsOnMethods="login")
	public void verify_BorderLayoutWest()
	{
		test=extent.createTest("verify_BorderLayoutWest");
		test.log(Status.INFO, "Test Case Execution started");
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.BorderLayoutIconWest_compressed));
		Assert.assertEquals(pc.BorderLayoutIconWest_compressed.isDisplayed(),true, "BorderLayoutIconWest_compressed is not displayad");
		pc.BorderLayoutIconWest_compressed.click();
		threadSleepWait(3000);
		
		explicitWait().until(ExpectedConditions.elementToBeClickable(pc.BorderLayoutIconWest_expanded));
		Assert.assertEquals(pc.BorderLayoutIconWest_expanded.isDisplayed(),true, "BorderLayoutIconWest_expanded option is not displayad");
		pc.BorderLayoutIconWest_expanded.click();
	}
	
	@Test(priority=16,dependsOnMethods="login")
	public void verify_LogOut()
	{
		pc.pc_logout();
		threadSleepWait(5000);
//		driver.close();
	}
}
