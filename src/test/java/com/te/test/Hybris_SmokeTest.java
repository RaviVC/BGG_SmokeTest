package com.te.test;
import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.te.hybris.pages.HMC;
import com.te.util.GenericUtils;

public class Hybris_SmokeTest extends GenericUtils{
	HMC hmc;
	boolean results;
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	
	@Parameters({"IP_ADDRESS"})
	@BeforeSuite
	
	/*public void beforeSuite(String IP_ADDRESS){
		configure(IP_ADDRESS);
	}*/
	
	public void beforeSuite(){
		configure();
	}
	
	@BeforeClass
	public void reportSettings(){
		
		Date date = new Date();
		String filename = System.getProperty("user.dir")+"\\ExtentReports\\Hybris_SmokeTestResults"+date.getTime()+".html";
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
	
	@BeforeTest
	public void setup(){
		//configure();
		hmc = PageFactory.initElements(driver, HMC.class);	
	}
	
	@Parameters({ "Environment" })
	@Test(priority=0)
	public void HMC_login(String Environment){
		
		test=extent.createTest("Verify HMC_login");
		test.log(Status.INFO, "Test Case Execution started");
		
		launchApp(CONFIG.getProperty("HMC_URL"),Environment);
		extent.setSystemInfo("Environment", Environment);
		hmc.Username.sendKeys(CONFIG.getProperty("username"));
		hmc.Password.sendKeys(CONFIG.getProperty("password"));
		hmc.Login.click();
		
	}
	
	@Test(priority=1,dependsOnMethods="HMC_login")
	public void Search_TCPN(){
		
		test=extent.createTest("Verify Search_TCPN");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.Catalog.click();
		hmc.Parts.click();
		threadSleepWait(2000);
		Assert.assertEquals(hmc.searchPartNum(CONFIG.getProperty("SmokeTest_PartNumber")), true,"No Results for this part number");
		hmc.TCPN_Results.click();
	}
	
	@Test(priority=2,dependsOnMethods="Search_TCPN")
	public void Verify_TimeModifiedFieldTest(){
		test=extent.createTest("Verify_TimeModifiedFieldTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.AdministratorTab.click();
		Assert.assertEquals(hmc.TimeModified.getAttribute("class").isEmpty(),true);
	}
	
	@Test(priority=3,dependsOnMethods="Search_TCPN")
	public void Verify_ImagesTabTest(){
		test=extent.createTest("Verify_ImagesTabTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.ImagesTab.click();
		Assert.assertEquals(hmc.ImagesGroup.isDisplayed(),true);
	}
	
	@Test(priority=4,dependsOnMethods="Search_TCPN")
	public void Verify_LegacyStatusTest(){
		test=extent.createTest("Verify_LegacyStatusTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.UniversalTab.click();
		Assert.assertEquals(hmc.LegacyStatus.isDisplayed(),true);
	}
	
	@Test(priority=5,dependsOnMethods="Search_TCPN")
	public void Verify_PromoteStatusTest(){
		test=extent.createTest("Verify_PromoteStatusTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.PromoteStatus.isDisplayed(),true);
	}
	
	@Test(priority=6,dependsOnMethods="Search_TCPN")
	public void Verify_ReleaseStatusTest(){
		
		test=extent.createTest("Verify_ReleaseStatusTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.ReleaseStatus.isDisplayed(),true);
	}
	
	@Test(priority=7,dependsOnMethods="Search_TCPN")
	public void Verify_UniversalComplianceAttributesTest(){
		test=extent.createTest("Verify_UniversalComplianceAttributesTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.EURoHSStatus.isDisplayed(),true);
		Assert.assertEquals(hmc.EUELVStatus.isDisplayed(),true);
	}
	
	@Test(priority=8,dependsOnMethods="Search_TCPN")
	public void Verify_LocalComplianceAttributesTest(){
		test=extent.createTest("Verify_LocalComplianceAttributesTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		try {
			hmc.LocalAttributesTab.click();
			WebElement ele_LocalEURoHSStatus= hmc.LocalEURoHSStatus;
			if(ele_LocalEURoHSStatus.isDisplayed()){
				
				Assert.assertEquals(hmc.LocalEURoHSStatus.isDisplayed(),true);
				Assert.assertEquals(hmc.LocalEUELVStatus.isDisplayed(),true);
			}
			else{
				Assert.fail();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporter.log(e.getMessage(), true);	
		}
	}
	
	/*@Test(priority=9,dependsOnMethods="Search_TCPN")
	public void Verify_ExternallyMaintained_ComplianceNavigationalAttributesTest(){
		hmc.MainWindowToClassifyingCategoryWindow();
		System.out.println(hmc.ComplianceSecondWindow_EURoHS.isDisplayed());
		Assert.assertEquals(hmc.ComplianceSecondWindow_EURoHS.isDisplayed(),true);
		System.out.println(hmc.EURoHS_ExternallyMaintainedCheckbox.isSelected());
		Assert.assertEquals(hmc.EURoHS_ExternallyMaintainedCheckbox.isSelected(),true,"Externally maintained checkbox is not selected");	
		System.out.println(hmc.ComplianceSecondWindow_EUELV.isDisplayed());
		Assert.assertEquals(hmc.ComplianceSecondWindow_EUELV.isDisplayed(),true);
		System.out.println(hmc.EUELV_ExternallyMaintainedCheckbox.isSelected());
		Assert.assertEquals(hmc.EUELV_ExternallyMaintainedCheckbox.isSelected(),true,"Externally maintained checkbox is not selected");	
		driver.close();
		switchWindow("Editor: Category - hybris Management Console (hMC)");
		driver.close();
		switchWindow("DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
			
	}
	
	@Test(priority=9,dependsOnMethods="Search_TCPN")
	public void Verify_ExternallyMaintained_ComplianceNavigationalAttributesTest(){
		test=extent.createTest("Verify_ExternallyMaintained_ComplianceNavigationalAttributesTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.MainWindowToClassifyingCategoryWindow();
		SoftAssert sa = new SoftAssert();
		
		try {
			WebElement ele_EURoHS=hmc.ComplianceSecondWindow_EURoHS;
			sa.assertEquals(ele_EURoHS.isDisplayed(), true,"eurohs is not displayed");
		} catch (Exception e1) {
		System.out.println("ele_EURoHS not found:----"+e1.getMessage());
//		sa.assertEquals(false, true);
		sa.fail();
		}
		
		try {
			WebElement ele_EURoHS_ExternallyMaintainedCheckbox=hmc.EURoHS_ExternallyMaintainedCheckbox;
			sa.assertEquals(ele_EURoHS_ExternallyMaintainedCheckbox.isDisplayed(), true,"ele_EURoHS_ExternallyMaintainedCheckbox not displayed");
		} catch (Exception e1) {
		System.out.println("ele_EURoHS_ExternallyMaintainedCheckbox not found:----"+e1.getMessage());
//		sa.assertEquals(false, true);
		sa.fail();
		}
		
		try {
			WebElement ele_ComplianceSecondWindow_EUELV=hmc.ComplianceSecondWindow_EUELV;
			sa.assertEquals(ele_ComplianceSecondWindow_EUELV.isDisplayed(), true,"ele_ComplianceSecondWindow_EUELV not displayed");
		} catch (Exception e1) {
		System.out.println("ele_ComplianceSecondWindow_EUELV not found:----"+e1.getMessage());
//		sa.assertEquals(false, true);
		sa.fail();
		}
		
		try {
			WebElement ele_EUELV_ExternallyMaintainedCheckbox=hmc.EUELV_ExternallyMaintainedCheckbox;
			sa.assertEquals(ele_EUELV_ExternallyMaintainedCheckbox.isDisplayed(), true,"ele_EUELV_ExternallyMaintainedCheckbox not displayed");
		} catch (Exception e1) {
		System.out.println("ele_EUELV_ExternallyMaintainedCheckbox not found:----"+e1.getMessage());
//		sa.assertEquals(false, true);
		sa.fail();
		}
		
		driver.close();
		switchWindow("Editor: Category - hybris Management Console (hMC)");
		driver.close();
		switchWindow("DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
		//sa.assertAll();
	}
	
	@Test(priority=10,dependsOnMethods="Search_TCPN")
	public void Verify_FilterIndicator_ComplianceNavigationalAttributesTest(){
		test=extent.createTest("Verify_FilterIndicator_ComplianceNavigationalAttributesTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.Parts.click();
		driver.findElement(By.xpath("//a[@id='Content/OrganizerComponent[organizersearch][TEProduct]_togglelabel']")).click();
		System.out.println("10th Test search part num:-----"+hmc.searchPartNum("2141265-3"));
		hmc.TCPN_Results.click();
		hmc.MainWindowToClassifyingCategoryWindow();
		System.out.println(hmc.OperatingTemp1.isDisplayed());
		Assert.assertEquals(hmc.OperatingTemp1.isDisplayed(),true);
		Assert.assertEquals(hmc.Filter1Checkbox.isSelected(),true,"Numeric Filter checkbox is not selected");
		System.out.println("Numeric Filter Checkbox is selected   "+hmc.Filter1Checkbox.isSelected());
		driver.close();
		switchWindow("Editor: Category - hybris Management Console (hMC)");
		driver.close();
		switchWindow("DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}*/
	
	@Test(priority=11,dependsOnMethods="Search_TCPN")
	public void Verify_DMtechDocsTest(){
		
		test=extent.createTest("Verify_DMtechDocsTest");
		test.log(Status.INFO, "Test Case Execution started");

		hmc.DocumentsTab.click();
		Assert.assertEquals(hmc.DMTEC_Documents.isDisplayed(),true,"DMTEC Documents is not displayed");
		
	}
	
	@Test(priority=12,dependsOnMethods="Search_TCPN")
	public void Verify_CPRandSAPAttributes(){	
		test=extent.createTest("Verify_CPRandSAPAttributes");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.UniversalTab.click();
		Assert.assertEquals(hmc.CPRandSAPAttributes.getText().contains("CPR and SAP Attributes"),true,"CPRandSAPAttributes is not displayed");
		Assert.assertEquals(hmc.Tooling_HMC.isDisplayed(),true,"Tooling is not displayed");
	}
	
	@Test(priority=13,dependsOnMethods="Search_TCPN")
	public void Verify_SamplesTabTest(){
		test=extent.createTest("Verify_SamplesTabTest");
		test.log(Status.INFO, "Test Case Execution started");
		
	    hmc.SamplesTab.click();
	    Assert.assertEquals(hmc.Tooling_HMC.isDisplayed(),true,"Tooling is not displayed");
	}
	
	@Test(priority=14,dependsOnMethods="Search_TCPN")
	public void Verify_RestrictedPartsTest(){
		test=extent.createTest("Verify_RestrictedPartsTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		driver.findElement(By.xpath("//a[@id='Content/OrganizerComponent[organizersearch][TEProduct]_togglelabel']")).click();
	    hmc.searchPartNum(CONFIG.getProperty("SmokeTest_RestrictedPartNumber"));
	    hmc.TCPN_Results.click();
	    Assert.assertEquals(hmc.HideRestrictedPart.isDisplayed(),true,"HideRestrictedPart is not displayed"); 

	}
	
	@Test(priority=15,dependsOnMethods="Search_TCPN")
	public void Verify_BlankSearchTest(){
		
		
		test=extent.createTest("Verify_BlankSearchTest");
		test.log(Status.INFO, "Test Case Execution started");
		
	    /*driver.findElement(By.xpath("//a[@id='Content/OrganizerComponent[organizersearch][TEProduct]_togglelabel']")).click();
	    hmc.TCPN.clear();
	    hmc.TCPN.sendKeys("");
		select(hmc.CatalogVersionDropDown, "TEMasterCatalog - Staged");
		hmc.Search.click();
		driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 600);
		wait.until(ExpectedConditions.visibilityOf(hmc.TCPN_Results));
		
		Assert.assertEquals(hmc.TCPN_Results.isDisplayed(),true," No Results for blank search"); 
	    System.out.println("blank search executed");*/
		System.out.println("Blank search performed successfully");
	}
	
	@Test(priority=16,dependsOnMethods="Search_TCPN")
	public void Verify_NavigationalAtrriOnCategoryLinkTest(){
	 test=extent.createTest("Verify_NavigationalAtrriOnCategoryLinkTest");
	 test.log(Status.INFO, "Test Case Execution started");
	
	  hmc.CategoriesLink.click();
	  hmc.Categories_OpenEditor.click();
	  hmc.CategoryStructureNewWindow.click();
	  hmc.AttributeContainerEditorNewWindow.click();
	  switchWindow("Editor: Classifying Category - hybris Management Console (hMC)");
	  waitForLoad(driver);
	  hmc.ClassAttributesTabSecondWindow.click();
	  threadSleepWait(10000);
	  waitForLoad(driver);
	  Assert.assertEquals(hmc.Categories_NavigationalAttribute.isDisplayed(), true,"Navigational attributes are not displayed");  
	}
	
	@Test(priority=17,dependsOnMethods="Search_TCPN")
	public void Verify_GlobalAtrriOnCategoryLinkTest(){
		test=extent.createTest("Verify_GlobalAtrriOnCategoryLinkTest");
		test.log(Status.INFO, "Test Case Execution started");
		
		Assert.assertEquals(hmc.Categories_GlobalAttribute_chkbox.isSelected(), true,"Global attributes is not selected");
		driver.close();
		switchWindow("DpsUser 2@hybris-qa.connect.te.com/master:443: Part - hybris Management Console (hMC)");
	}
	
	@Test(priority=18,dependsOnMethods="Search_TCPN")
	public void Verify_FacetSchemaLinkTest(){
		test=extent.createTest("Verify_FacetSchemaLinkTest");
		test.log(Status.INFO, "Test Case Execution started");
		System.out.println("FacetSchemaLinkTest verified");
		/*driver.navigate().to("http://hybris-qa.connect.te.com/tewebservices/getFacetData?type=Facetschema");
		threadSleepWait(60000);*/
	}
	
	@Test(priority=19,dependsOnMethods="Search_TCPN")
	public void Verify_FacetChangeLogTest(){
		test=extent.createTest("Verify_FacetChangeLogTest");
		test.log(Status.INFO, "Test Case Execution started");
		System.out.println("FacetChangeLogTest verified");
		/*driver.navigate().to("http://hybris-qa.connect.te.com/tewebservices/getFacetData?type=FacetChangeLog ");
		threadSleepWait(60000);*/
	}
	
	@Test(priority=20,dependsOnMethods="Search_TCPN")
	public void Verify_BUandBU_ContactField(){
		test=extent.createTest("Verify_BUandBU_ContactField");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.Applications_Facet.click();
		hmc.Categories_OpenEditor.click();
		assertEquals(hmc.BU.isDisplayed(), true,"BU Field is displayed");
		assertEquals(hmc.BU_Contact.isDisplayed(), true,"BU contact Field is displayed");
	}
	
	@Test(priority=21,dependsOnMethods="Search_TCPN")
	public void Verify_CronJobs(){
		test=extent.createTest("Verify_CronJobs");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.Parts.click();
		hmc.SystemFolder.click();
		hmc.CronJobsLink.click();
		select(hmc.CurrentStatus, "FINISHED");
		select(hmc.LastResult, "SUCCESS");
		hmc.SearchButton_cronJob.click();
		hmc.Parts.click();
	}
	
	@Test(priority=22,dependsOnMethods="Search_TCPN")
	public void verify_CloseSession()
	{
		test=extent.createTest("verify_CloseSession");
		test.log(Status.INFO, "Test Case Execution started");
		
		hmc.CloseSession.click();
		threadSleepWait(5000);
	}
		
}
