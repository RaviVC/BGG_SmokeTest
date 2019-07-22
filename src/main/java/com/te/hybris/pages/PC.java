package com.te.hybris.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.te.util.GenericUtils;


public class PC extends GenericUtils {
	
	@FindBy(how=How.XPATH,using="//input[@name='j_username']")
	
	public WebElement Username;
	
	@FindBy(how=How.XPATH,using="//input[@name='j_password']")
	
	public WebElement Password;
	
	@FindBy(how=How.XPATH,using="//td[@class='z-button-cm']")
	
	public WebElement Login;
	//td[input[@class='z-textbox']]
	
	@FindBy(how=How.XPATH,using="//table[@z.type='zul.box.Box' and @class='z-hbox']//td/input[@class='z-textbox']")
	public WebElement searchTextBox;
	
//	@FindBy(how=How.XPATH,using="//table[@class='searchbtn z-button']//img[@src='/productcockpit/cockpit/images/BUTTON_search.png']")
//	public WebElement searchButton;
	
//	@FindBy(how=How.XPATH,using="//table[@class='searchbtn z-button ']")
	//table[contains(@class,'searchbtn z-button')]
	
	@FindBy(how=How.XPATH,using="//table[contains(@class,'searchbtn z-button')]//tr[2]/td[2]/img")
	
	public WebElement searchButton;
	
	@FindBy(how=How.XPATH,using="//div[div[span[text()='Description [en]']]]/div[2]")
	
	public WebElement searchResultClick;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'IMAGE GROUP')]")
	
	public WebElement ImageGroup;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Image Group:')]")
	
	public WebElement ImageGroupChildElement;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'CPR ATTRIBUTES')]")
	
	public WebElement CPRATTRIBUTES;
	 
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Legacy Status: ')]")
	
	public WebElement LegacyStatus;
	 
	@FindBy(how=How.XPATH,using="//span[contains(text(),'EU RoHS Compliance: ')]")
	
	public WebElement EU_RoHS_Compliance; 
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'EU ELV Compliance: ')]")
	
	public WebElement EU_ELV_Compliance;
	
	
	@FindBy(how=How.XPATH,using="//a[@title='Advanced search']/img")
	
	public WebElement PC_AdvancedSearch_link;
	
	
	@FindBy(how=How.XPATH,using="//tr[td[div[span[@title='TEProduct.tcpn']]]]//td[3]/div/div/div")
	
	public WebElement PC_AdvancedSearch_TCPN_Textbox;
	
	
	@FindBy(how=How.XPATH,using="//tr[td[span[text()='contains']]]/td[3]/input")
	
	public WebElement PC_AdvancedSearch_containsTextbox;
	
	@FindBy(how=How.XPATH,using="//span[text()='Apply']")
	
	public WebElement PC_AdvancedSearch_contains_apply;
	
	
	@FindBy(how=How.XPATH,using="//tr[td[div[span[@title='TEProduct.productCode.code']]]]//td[3]/div/div/div")
	
	public WebElement PC_ProductCode_Textbox;
	
	//table[@class="advSearchBtn z-button"]//img[@src="/productcockpit/cockpit/images/BUTTON_search.png"]
	@FindBy(how=How.XPATH,using="//table[@class='advSearchBtn z-button']//img[@src='/productcockpit/cockpit/images/BUTTON_search.png']")
	
	public WebElement PC_AdvancedSearch_button;
	
	
	 
	@FindBy(how=How.XPATH,using="//span[contains(text(),'TE.COM STATUS')] ")
	
	public WebElement TECOM_STATUS;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Promote Status: ')]")
	
	public WebElement TECOM_PromoteStatus;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Release Status: ')]")
	
	public WebElement TECOM_ReleaseStatus;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'NAVIGATIONAL ATTRIBUTES')]")
	
	public WebElement NAVIGATIONAL_ATTRIBUTES;
	
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'SAMPLES')]")
	
	public WebElement SAMPLES;
	
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Samples Indicator: ')]")
	
	public WebElement SAMPLES_indicator;
	
	 
	@FindBy(how=How.XPATH,using="//input[@value='[ true]']")
	
	public WebElement SAMPLES_indicatorFieldValue;
	
	
	@FindBy(how=How.XPATH,using="//tr[td[div[span[contains(text(),'Samples Indicator: ')]]]]/td[3]//table[@class='referenceSelector_hbox z-hbox']//td[3]/a")
	
	public WebElement SAMPLES_indicatorPencilButton;
	
	
	public void searchPartNum(String partNumPC){

		Actions act= new Actions(driver);
		System.out.println(searchTextBox.getAttribute("id"));
        driver.findElement(By.xpath("//td[text()='Product']")).click();
		searchTextBox.clear();
		threadSleepWait(2000);
		driver.findElement(By.xpath("//td[text()='Product']")).click();
		driver.findElement(By.id(searchTextBox.getAttribute("id"))).sendKeys(partNumPC);
		threadSleepWait(5000);
		System.out.println("part num entered");
		act.moveToElement(searchButton).perform();
		act.click(searchButton).perform();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		System.out.println("clicked on search button");
	}
	
	//****************************** REGRESSION ****************************************
	
		@FindBy(how=How.XPATH,using="//span[@title='Order by']//span/img")
		public WebElement OrderByDropdown;
		
		@FindBy(how=How.XPATH,using="//span[@title='Order by']//input[@value='TCPN']")
		public WebElement OrderBy_TCPN;
		
		@FindBy(how=How.XPATH,using="//a[@class='browser_view_mode_switch mode_GRID z-toolbar-button ']")
		public WebElement GridView;
		
		@FindBy(how=How.XPATH,using="//a[@class='browser_view_mode_switch mode_LIST z-toolbar-button z-toolbar-button-disd']")
		public WebElement ListView;
		
		@FindBy(how=How.XPATH,using="//div[@class='toolbar_newitem_action']")
		public WebElement CreateNewItem;
		
		@FindBy(how=How.XPATH,using="//a[@class='plainBtn saveQueryBtn z-toolbar-button ']")
		public WebElement SaveSearchQuery;
		
		@FindBy(how=How.XPATH,using="//span[@title='Open new tab']")
		public WebElement OpenNewTab;
		
		@FindBy(how=How.XPATH,using="//span[label[contains(text(),'Ascending')]]/input")
		public WebElement AscendingCheckBox;
		
		@FindBy(how=How.XPATH,using="//div[@class='browserstatus']//table//tr//span//span/img")
		public WebElement PageSizeDropdown;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'TE.COM STATUS')]")
		public WebElement TECOMSTATUS;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'NAVIGATIONAL ATTRIBUTES')]")
		public WebElement NAVIGATIONALATTRIBUTES;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'DISPLAY ATTRIBUTES')]")
		public WebElement DISPLAYATTRIBUTES;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'FACETS')]")
		public WebElement FACETS;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'IMAGE GROUP')]")
		public WebElement IMAGEGROUP;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'SYNERGISTIC SALES')]")
		public WebElement SYNERGISTICSALES;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'COMPATIBLE PARTS')]")
		public WebElement COMPATIBLEPARTS;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'DOCUMENTS')]")
		public WebElement DOCUMENTS;
		
		@FindBy(how=How.XPATH,using="//span[@title='supercategories']")
		public WebElement supercategories;
		
		@FindBy(how=How.XPATH,using="//table[tbody[tr[td[div[span[contains(text(),'SuperCategories: ')]]]]]]//span/input")
		public WebElement supercategories_option;
		
		@FindBy(how=How.XPATH,using="//a[@title='Shared saved queries']")
		public WebElement Sharedsavedqueries;
		
		@FindBy(how=How.XPATH,using="//a[@title='Unshared saved queries']")
		public WebElement Unsharedsavedqueries;
		
		@FindBy(how=How.XPATH,using="//div[@class='navigation-query-label saved-query-list-item-navigation-query-label']/span")
		public WebElement NavigationQuery;
		
		@FindBy(how=How.XPATH,using="//div[@class='z-border-layout-icon z-west-colps']")
		public WebElement BorderLayoutIconWest_compressed;
		
		@FindBy(how=How.XPATH,using="//div[@class='z-border-layout-icon z-west-exp']")
		public WebElement BorderLayoutIconWest_expanded;
		
		@FindBy(how=How.XPATH,using="//span/table[@class='plainBtn z-button']//td[text()='Catalog']")
		public WebElement Catalog;
		
		@FindBy(how=How.XPATH,using="//span/table[@class='plainBtn z-button z-button-disd']//td[text()='Product']")
		public WebElement Product;
		
		@FindBy(how=How.XPATH,using="//img[@class='menu_label_img']")
		public WebElement menuarrowbutton;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),' Logout')]")
		public WebElement menu_logout;
		
		
		public void pc_logout()
		{
			explicitWait().until(ExpectedConditions.elementToBeClickable(menuarrowbutton));
			menuarrowbutton.click();
			explicitWait().until(ExpectedConditions.elementToBeClickable(menu_logout));
			menu_logout.click();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
