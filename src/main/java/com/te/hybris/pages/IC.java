package com.te.hybris.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.te.util.GenericUtils;

public class IC extends GenericUtils {
	
	@FindBy(how=How.XPATH,using="//input[@name='j_username']")
	public WebElement Username;
	
	@FindBy(how=How.XPATH,using="//input[@name='j_password']")
	public WebElement Password;
	
	@FindBy(how=How.XPATH,using="//td[@class='z-button-cm']")
	public WebElement Login;
	
	@FindBy(how=How.XPATH,using="//td[contains(text(),'Create a new job')]")
	public WebElement CreateNewJob;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Mass Promote Export')]")
	public WebElement MassPromoteExport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Mass Promote Import')]")
	public WebElement MassPromoteImport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Part Image Export')]")
	public WebElement PartImageExport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Part Image Import')]")
	public WebElement PartImageImport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Global Facet Export')]")
	public WebElement GlobalFacetExport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Global Facet Import')]")
	public WebElement GlobalFacetImport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Mass Sample Export')]")
	public WebElement MassSampleExport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Mass Sample Import')]")
	public WebElement MassSampleImport;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Compatible Parts Export')]")
	public WebElement CompatiblePartsExport;
	
	@FindBy(how=How.XPATH,using="//tr[td[div[span[contains(text(),'Export Criteria: ')]]]]//td[3]/div[1]/div/span//img")
	public WebElement ExportCriteriaDropdown;
	
	@FindBy(how=How.XPATH,using="//td[contains(text(),'By-EndNode')]")
	public WebElement ExportCriteria_ByEndNode;
	//table[@id='Enum_FlexibleItemExportICCronjobcriteria$0!cave']//tr[@z.label='By-EndNode']
	
	@FindBy(how=How.XPATH,using="//tr[td[div[span[contains(text(),'End Node: ')]]]]/td[3]/div/table//tr//span/img")
	public WebElement EndNodeDropDown;
	
	@FindBy(how=How.XPATH,using="//tr[@z.label='AC Contactors-140474']")
	public WebElement EndNode_ACContactors;
	//div[@id='Combobox_6d12c727$0!pp']/table//tr[4]
	
	@FindBy(how=How.XPATH,using="//div[@class='columnLayoutComponent']/div/div[2]/div[8]/div/div[2]/div/div[1]//img[1]")
	public WebElement StartJob;
	//div[@id='Listview_MassPromoteExport-dpsuser2-1548753374436_Actions_row_0$1']//img[1]]
	
	@FindBy(how=How.XPATH,using="//table[@class='collectionEditorContainer z-vbox']//table//tr//td//div//td[4]/img")
	public WebElement Outputfile_DownloadIcon;
	
	@FindBy(how=How.XPATH,using="//td[contains(text(),'Download')]")
	public WebElement Download_Button;
	
	@FindBy(how=How.XPATH,using="//div[@class='z-window-highlighted-hm']/div//div")
	public WebElement EditMedia_CloseIcon;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Welcome DpsUser 2!')]")
	public WebElement WelcomeDPSUser2;
	
	@FindBy(how=How.XPATH,using="//div[@class='z-border-layout-icon z-west-exp']")
	public WebElement logoutarrowbutton;
	
	@FindBy(how=How.XPATH,using="//img[@class='menu_label_img']")
	public WebElement menuarrowbutton;
	
	@FindBy(how=How.XPATH,using="//a[contains(text(),' Logout')]")
	public WebElement menu_logout;
	
	//@FindBy(how=How.XPATH,using="//div[@id='EditorArea_Basic_XLS/XLSXfile_input$0']//span/table//tr[2]/td[2]")
	@FindBy(how=How.XPATH,using="//tr[td[div[span[contains(text(),'XLS/XLSX file: ')]]]]//table//td[@class='z-button-cm']")
	public WebElement uploadbutton;
	
	@FindBy(how=How.XPATH,using="//table[@id='upload-list']//input")
	public WebElement choosefile;
	
	@FindBy(how=How.XPATH,using="//div[@class='z-window-modal-icon z-window-modal-close']")
	public WebElement FileUpload_closebutton;
	
	
	public void ic_logout()
	{
		explicitWait().until(ExpectedConditions.elementToBeClickable(logoutarrowbutton));
		logoutarrowbutton.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(menuarrowbutton));
		menuarrowbutton.click();
		explicitWait().until(ExpectedConditions.elementToBeClickable(menu_logout));
		menu_logout.click();
	}
	
}
