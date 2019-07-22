package com.te.hybris.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import com.te.util.GenericUtils;

public class HMC extends GenericUtils{
	
	boolean results;
	
	@FindBy(how=How.XPATH,using="//input[@id='Main_user']")
	public WebElement Username;
	
	@FindBy(how=How.XPATH,using="//input[@id='Main_password']")
	public WebElement Password;
	
	@FindBy(how=How.XPATH,using="//a[@title='Login']")
	public WebElement Login;
	
	@FindBy(how=How.XPATH,using="//a[text()='Marketing Tools (hMC)']")
	public WebElement CommerceLink;
	
	@FindBy(how=How.XPATH,using="//*[@id='Tree/GenericExplorerMenuTreeNode[catalog]_label']")
	public WebElement Catalog;
	
	@FindBy(how=How.XPATH,using="//*[@id='Tree/GenericLeafNode[TEProduct]_label']")
	public WebElement Parts;
	
	@FindBy(how=How.XPATH,using="//*[@id='Content/StringEditor[in Content/GenericCondition[TEProduct.tcpn]]_input']")
	public WebElement TCPN;
	
	@FindBy(how=How.XPATH,using="//*[@id='Content/OrganizerSearch[TEProduct]_searchbutton']")
	public WebElement Search;
	
	@FindBy(how=How.XPATH,using="//a[@title='Open Editor']")
	public WebElement TCPN_Results;
	
	@FindBy(how=How.XPATH,using="//table[@class='allInstancesSelectEditorChip']//select")
	public WebElement CatalogVersionDropDown;
	
	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Administration')]]")
	public WebElement AdministratorTab;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Time modified')]")
	public WebElement TimeModified;
	
	@FindBy(how=How.XPATH,using="//span[@id='Content/EditorTab[TEProduct.tab.teproduct.multimedia]_span']")
	public WebElement ImagesTab;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Image Group:')]")
	public WebElement ImagesGroup;
	
	
	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Universal attributes')]]")
	public WebElement UniversalTab;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Legacy Status:')]")
	public WebElement LegacyStatus;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Promote Status:')]")
	public WebElement PromoteStatus;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Release Status:')]")
	public WebElement ReleaseStatus;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'EU RoHS Compliance:')]")
	public WebElement EURoHSStatus;
		
	@FindBy(how=How.XPATH,using="//div[contains(text(),'EU ELV Compliance:')]")
	public WebElement EUELVStatus;
	
	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Local attributes')]]")
	public WebElement LocalAttributesTab;
	
	//@FindBy(how=How.XPATH,using="//a[contains(text(),'EU RoHS Compliance:')]")
	@FindBy(how=How.XPATH,using="//a[contains(text(),'Operating Temperature Hybris-V6-extra (Max):')]")
	public WebElement LocalEURoHSStatus;
		
	@FindBy(how=How.XPATH,using="//a[contains(text(),'EU ELV Compliance:')]")
	public WebElement LocalEUELVStatus;
	
	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Category System')]]")
	public WebElement CategorySystemTab;

	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Documents')]]")
	public WebElement DocumentsTab;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Samples')]")
	public WebElement SamplesTab;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'DMTEC Documents:')]")
	public WebElement DMTEC_Documents;
	

	@FindBy(how=How.XPATH,using="//div[contains(text(),'Tooling')]")
	public WebElement Tooling_HMC;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'CPR and SAP Attributes')]")
	public WebElement CPRandSAPAttributes;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Hide Restricted Part:')]")
	public WebElement HideRestrictedPart;
	
	@FindBy(how=How.XPATH,using="//tbody[tr[td[div[contains(text(),'Product class')]]]]//a[@title='Open Editor']")
	public WebElement EndNode;
	
	
	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Category Structure')]]")
	public WebElement CategoryStructureNewWindow;
	
	
	@FindBy(how=How.XPATH,using="//tbody[tr[td[div[contains(text(),'Attribute Container:')]]]]//a")
	public WebElement AttributeContainerEditorNewWindow;
	
	@FindBy(how=How.XPATH,using="//a[span[contains(text(),'Class Attributes')]]")
	public WebElement ClassAttributesTabSecondWindow;
	
	
	//@FindBy(how=How.XPATH,using="//input[contains(@value,'EU RoHS Compliance')]")
	@FindBy(how=How.XPATH,using="//input[contains(@value,'Operating Temperature Range')]")
	public WebElement ComplianceSecondWindow_EURoHS;
	//input[@type='checkbox' and contains(@id,'Application Tooling')]
	//table[@class='listtable selecttable']//tr[contains(@class,'edit selected')]//td[8]//input[@type='checkbox']
	
	@FindBy(how=How.XPATH,using="//tr[@id='8802123350626']//td[8]//input[@type='checkbox']")
	public WebElement EURoHS_ExternallyMaintainedCheckbox;
	
	@FindBy(how=How.XPATH,using="//input[contains(@value,'EU ELV Compliance')]")
	public WebElement ComplianceSecondWindow_EUELV;
	
	@FindBy(how=How.XPATH,using="//tr[@id='8802122728034']//td[8]//input[@type='checkbox']")
	public WebElement EUELV_ExternallyMaintainedCheckbox;
	
	
	@FindBy(how=How.XPATH,using="//tr[@id='8800418497122']//input[contains(@value,'Operating Temperature')]")
	public WebElement OperatingTemp1;
	
	@FindBy(how=How.XPATH,using="//tr[@id='8800418497122']//td[13]//input[@type='checkbox']")
	public WebElement Filter1Checkbox;
	
	
	@FindBy(how=How.XPATH,using="//a[contains(text(),'Categories')]")
	public WebElement CategoriesLink;
	
	
	@FindBy(how=How.XPATH,using="//table[@class='listtable selecttable']//tr[2]//a[@title='Open Editor']")
	public WebElement Categories_OpenEditor;
	
	
	@FindBy(how=How.XPATH,using="//td[@id='outerTD']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td[2]/div")
	public WebElement Categories_NavigationalAttribute;
	
	
	@FindBy(how=How.XPATH,using="//tr[@id='8802123678306']/td[7]//input[@type='checkbox']")
	public WebElement Categories_GlobalAttribute_chkbox;
	
	
	//********************************  RAVI CODE **********************************************
	@FindBy(how=How.XPATH,using="//a[contains(text(),'Applications')]")
	public WebElement Applications_Facet;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'BU:')]")
	public WebElement BU;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'BU Contact:')]")
	public WebElement BU_Contact;
	
	
	public boolean searchPartNum(String partNum){
		
		TCPN.clear();
		TCPN.sendKeys(partNum);
		threadSleepWait(3000);
//		Select obj = new Select(CatalogVersionDropDown);
//      obj.selectByVisibleText(text);
		select(CatalogVersionDropDown, "TEMasterCatalog - Staged");
//		select(CatalogVersionDropDown, "2");
		Search.click();
		
		try {
			TCPN_Results.isDisplayed();
			results=true;
		} catch (Exception e) {
			results=false;
		}
		return results;
	}
	
	public void MainWindowToClassifyingCategoryWindow(){
		CategorySystemTab.click();
		System.out.println("Clicked on Category System and the title is :---->"+driver.getTitle());
		EndNode.click();	
		switchWindow("Editor: Category - hybris Management Console (hMC)");
		System.out.println(driver.getTitle());
		CategoryStructureNewWindow.click();	
		AttributeContainerEditorNewWindow.click();
		switchWindow("Editor: Classifying Category - hybris Management Console (hMC)");
		System.out.println(driver.getTitle());
	    waitForLoad(driver);
		ClassAttributesTabSecondWindow.click();
		waitForLoad(driver);
    }
	
	
	
	
	//********************************  REGRESSION **********************************************
	
	
		@FindBy(how=How.XPATH,using="//span[contains(text(),'Commerce')]")
		public WebElement CommerceTab;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'Prices')]")
		public WebElement PricesTab;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'Extended Attributes')]")
		public WebElement ExtendedAttributesTab;
		
		@FindBy(how=How.XPATH,using="//span[@id='Content/EditorTab[TEProduct.tab.teproduct.testock]_span']")
		public WebElement StockTab;
		
		@FindBy(how=How.XPATH,using="//span[@id='Content/EditorTab[TEProduct.tab.product.futurestock]_span']")
		public WebElement FutureStockTab;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'Unclassify/archive')]")
		public WebElement UnclassifyArchiveTab;
		
		@FindBy(how=How.XPATH,using="//span[contains(text(),'Community')]")
		public WebElement CommunityTab;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Eligible for Compatible Parts:')]")
		public WebElement CompatibleParts;
		
		@FindBy(how=How.XPATH,using="//div[table[tbody[tr[td[div[contains(text(),'Eligible for Compatible Parts:')]]]]]]//select")
		public WebElement EligibleforCompatiblePartsDropdown;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Derived From:')]")
		public WebElement DerivedFrom;
		
		@FindBy(how=How.XPATH,using="//div[table[tbody[tr[td[div[contains(text(),'Derived From:')]]]]]]//td[5]/div//input")
		public WebElement DerivedFromPart;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Connector System:')]")
		public WebElement ConnectorSystem;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Number of Positions:')]")
		public WebElement NumberOfPositions;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Sealable:')]")
		public WebElement Sealable;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Facets')]")
		public WebElement Facets;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'ECCN Data:')]")
		public WebElement ECCNData;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'TE Attribute Modifications:')]")
		public WebElement TEAttributeModifications;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Product Restrictions:')]")
		public WebElement ProductRestrictions;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Price quantity:')]")
		public WebElement PriceQuantity;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Prices:')]")
		public WebElement Prices;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Image Group:')]")
		public WebElement ImageGroup;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Global Sample Indicator:')]")
		public WebElement GlobalSampleIndicator;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Samples Indicator:')]")
		public WebElement SamplesIndicator;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'IDs & Units')]")
		public WebElement IDandUNIT;
		
		@FindBy(how=How.XPATH,using="//td[contains(text(),'Stock Levels')]")
		public WebElement StockLevels;
		
		@FindBy(how=How.XPATH,using="//td[contains(text(),'Stock Meta Data')]")
		public WebElement StockMetaData;
		
		@FindBy(how=How.XPATH,using="//td[contains(text(),'Future Stock')]")
		public WebElement FurureStock;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Unclassify Part')]")
		public WebElement UnclassifyPart;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Average rating:')]")
		public WebElement AverageRating;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Last changes')]")
		public WebElement LastChanges;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Item changes:')]")
		public WebElement ItemChanges;
		
		@FindBy(how=How.XPATH,using="//div[table[tbody[tr[td[div[contains(text(),'Legacy Status:')]]]]]]//select")
		public WebElement LegacyStatusOption;
		
		@FindBy(how=How.XPATH,using="//input[@id='Content/BooleanEditor[in Content/Attribute[TEProduct.orderable]]_checkbox']")
		public WebElement Orderable;
		
		@FindBy(how=How.XPATH,using="//a[@id='Content/OrganizerComponent[organizersearch][TEProduct]_togglelabel']")
		public WebElement search_Link;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Promote Status')]]]//td[4]//select")
		public WebElement promotestatusDropdown;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Release Status')]]]//td[4]//select")
		public WebElement releasestatusDropdown;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Legacy Status')]]]//td[4]//select")
		public WebElement legacystatusDropdown;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Promote Status:')]]]//select")
		public WebElement TCPN_promotestatusDropdown;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Release Status:')]]]//select")
		public WebElement TCPN_releasestatusDropdown;
		
		@FindBy(how=How.XPATH,using="//td[@class='oicToolbar']//a[@title='Save item (Alt+S)']")
		public WebElement Save;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Time modified:')]]]//td[5]//td[@class='dateInputField']/input")
		public WebElement TimeModifiedField;
		
		@FindBy(how=How.XPATH,using="//img[@id='Content/ClassificationOrganizerList[TEProduct][refresh]_img']")
		public WebElement refresh;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[6]//div[@id='Content/ItemDisplay[NOTELIGIBLE]_div2']")
		public WebElement TCPNresults_NOTELIGIBLE;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[6]//div[@id='Content/ItemDisplay[NOTREVIEWED]_div2']")
		public WebElement TCPNresults_NOTREVIEWED;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[6]//div[@id='Content/ItemDisplay[PROMOTED]_div2']")
		public WebElement TCPNresults_PROMOTED;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[6]//div[@id='Content/ItemDisplay[NOTPROMOTED]_div2']")
		public WebElement TCPNresults_NOTPROMOTED;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//td[8]//div[@id='Content/ItemDisplay[APPROVED][170]_div2']")
		public WebElement TCPNresults_APPROVED;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//td[8]//div[@id='Content/ItemDisplay[INPROCESS][170]_div2']")
		public WebElement TCPNresults_INPROCESS;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//td[8]//div[@id='Content/ItemDisplay[READY][170]_div2']")
		public WebElement TCPNresults_READY;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//td[8]//div[@id='Content/ItemDisplay[NOTAPPLICABLE][170]_div2']")
		public WebElement TCPNresults_NOTAPPLICABLE;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[9]//div[@id='Content/ItemDisplay[Preliminary][27]_div2']")
		public WebElement TCPNresults_Preliminary;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[9]//div[@id='Content/ItemDisplay[Active][261]_div2']")
		public WebElement TCPNresults_Active;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[9]//div[@id='Content/ItemDisplay[Obsolete][41]_div2']")
		public WebElement TCPNresults_Obsolete;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[9]//div[@id='Content/ItemDisplay[Obsolete][41]_div2']")
		public WebElement TCPNresults_Restricted;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[9]//div[@id='Content/ItemDisplay[Field Replacement][50]_div2']")
		public WebElement TCPNresults_FieldReplacement;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Replacement Parts')]]]//table//img")
		public WebElement ReplacementPartsSearch;
		
		@FindBy(how=How.XPATH,using="//tr[@class='flexibleSearch']//td//table//tr[9]//table//span/img")
		public WebElement PartAliasesSearch;
		
		@FindBy(how=How.XPATH,using="//tr[@class='flexibleSearch']//td//tr[10]//td[4]//div//table//img")
		public WebElement MarketingBrandSearch;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'SuperCategories')]]]//table//img")
		public WebElement SuperCategoriesSearch;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Product Code')]]]//table//img")
		public WebElement ProductCodeSearch;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Derived From')]]]//table//img")
		public WebElement DerivedFromSearch;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Industries')]")
		public WebElement IndustriesFacet;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Series')]")
		public WebElement SeriesFacet;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Product Families')]")
		public WebElement ProductFamiliesFacet;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Standard')]")
		public WebElement StandardFacet;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'TE Document')]")
		public WebElement TEDocumentLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Image Group')]")
		public WebElement ImageGroupLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Legacy Images')]")
		public WebElement LegacyImagesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Product Groups')]")
		public WebElement ProductGroupsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ECCN Data')]")
		public WebElement ECCNDataLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Units')]")
		public WebElement UnitsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Catalog Management Tools')]")
		public WebElement CatalogManagementToolsFolderLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Catalog Overview')]")
		public WebElement CatelogOverviewLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Catalog Version Diff.')]")
		public WebElement CatelogVersionDiffLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Duplicate Identifiers')]")
		public WebElement DuplicateIdentifiersLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Multithreaded Synchronization')]")
		public WebElement MultithreadedSynchronizationLink;
		
		@FindBy(how=How.XPATH,using="//a[@id='Tree/GenericExplorerMenuTreeNode[user]_label']")
		public WebElement UserFolderLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Employees')]")
		public WebElement EmployeesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Internationalization')]")
		public WebElement InternationalizationFolder;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Languages')]")
		public WebElement LanguagesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Currencies')]")
		public WebElement CurrenciesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Countries')]")
		public WebElement CountriesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Regions')]")
		public WebElement RegionsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ICS')]")
		public WebElement ICSFolderLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ICS Permissions')]")
		public WebElement ICSPermissionsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ICS Contact')]")
		public WebElement ICSContactLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Product Line')]")
		public WebElement ProductLineLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Product Code')]")
		public WebElement ProductCodeLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Custom Interceptors')]")
		public WebElement CustomInterceptorsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Import Cockpit')]")
		public WebElement ImportCockpitFolder;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Import CronJobs')]")
		public WebElement ImportCronJobsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Import Input Medias')]")
		public WebElement ImportInputMediasLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Mappings')]")
		public WebElement MappingsLink;
		
		@FindBy(how=How.XPATH,using=" //a[contains(text(),'ImpEx Import Medias')]")
		public WebElement ImpExImportMedias;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'itemexport')]")
		public WebElement ItemExportFolder;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Generic Item Export Job')]")
		public WebElement GenericItemExportJobLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ColumnDefinition')]")
		public WebElement ColumnDefinitionLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Export Configuration')]")
		public WebElement ExportConfigurationLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'DpsUser 2')]")
		public WebElement DPSUSER2;
		
		@FindBy(how=How.XPATH,using="//a[@id='Tree/GenericExplorerMenuTreeNode[system]_label']")
		public WebElement SystemFolder;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'CronJobs')]")
		public WebElement CronJobsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Item Changes')]")
		public WebElement ItemChangesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Deeplink Urls')]")
		public WebElement DeepLinkUrls;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Deeplink URLs')]")
		public WebElement DeepLinkURLs;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Deeplink URL rules')]")
		public WebElement DeepLinkURLRules;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Cockpit')]")
		public WebElement CockpitFolder;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Item Templates')]")
		public WebElement ItemTemplatesLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'UI Component Configurations')]")
		public WebElement UIComponenetConfigurationsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'UI Component Access Rights')]")
		public WebElement UICompoenentAccessRightsLink;
		
		@FindBy(how=How.XPATH,using="//a[@id='Tree/GenericExplorerMenuTreeNode[group.scripting]_label']")
		public WebElement ScriptingFolder;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Scripts')]")
		public WebElement ScriptsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ScriptingJobs')]")
		public WebElement ScriptingJobsLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'ScriptingTasks')]")
		public WebElement ScriptingTasksLink;
		
		@FindBy(how=How.XPATH,using="//a[contains(text(),'Dynamic Process Definition')]")
		public WebElement DynamicProcessDefinitionLink;
		
		@FindBy(how=How.XPATH,using="//select[@id='Content/ClassificationAttributeSelectToolbarAction[attributeselect][TEProduct]_select']")
		public WebElement AttributeDropdown;
		
		@FindBy(how=How.XPATH,using="//select[@id='Toolbar/CatalogToolbarAction[catalogtoolbarchip.label]_select']")
		public WebElement CatalogVersionFilterDropdown;
		
		@FindBy(how=How.XPATH,using="//select[@id='Explorer[languageselector]_select']")
		public WebElement LanguageDropdown;
		
		@FindBy(how=How.XPATH,using="//img[@id='Explorer[userprofile]_img']")
		public WebElement SettingsIcon;
		
		@FindBy(how=How.XPATH,using="//input[@class='search-resizing-input']")
		public WebElement App_Search;
		
		@FindBy(how=How.XPATH,using="//input[@type='submit']")
		public WebElement App_SearchIcon;
		
		@FindBy(how=How.XPATH,using="//div[@id='resultlist_Content/ClassificationOrganizerList[TEProduct]']//table//tr[2]//td[5]//div/div")
		public WebElement TCPNResults_PartNumber;
		
		@FindBy(how=How.XPATH,using="//tbody[tr[td[div[contains(text(),'Replacement Parts:')]]]]//td[5]//tr[2]//td[3]//input")
		public WebElement UA_ReplacementPartNumber;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Model Part Indicator:')]]]//td[5]//input[@type='checkbox']")
		public WebElement UA_ModelPartIndicator;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Derived Parts of Model Part:')]]]//td[5]//div//tr[2]//td[4]/div/div")
		public WebElement UA_DerivedPartNumber;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Eligible for Compatible Parts:')]")
		public WebElement UA_EligibleForCompatibleParts;
		
		@FindBy(how=How.XPATH,using="//table[@class='genericItemListChip']//tr[2]//td[7]//input")
		public WebElement PriceValue;
		
		@FindBy(how=How.XPATH,using="//div[@id='Toolbar/CreateNewToolbarAction[New]_button']")
		public WebElement ToolBar_New;
		
		@FindBy(how=How.XPATH,using="//div[@class='dropdown-main']//tbody/tr[8]//td[2]")
		public WebElement ToolBar_DropdownPart;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Sales Intent:')]]]//td[5]//select//option[@value='3']")
		public WebElement UA_SalesIntent;
		
		@FindBy(how=How.XPATH,using="//div[contains(text(),'Saved Queries')]")
		public WebElement SavedQueries;
		
		@FindBy(how=How.XPATH,using="//img[@id='Explorer[backbutton]_img']")
		public WebElement BackButton;
		
		@FindBy(how=How.XPATH,using="//img[@id='Explorer[forwardbutton]_img']")
		public WebElement ForwardButton;
		
		@FindBy(how=How.XPATH,using="//img[@id='Toolbar/ImageToolbarAction[closesession]_img']")
		public WebElement CloseSession;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Current status')]]]//td[4]//select")
		public WebElement CurrentStatus;
		
		@FindBy(how=How.XPATH,using="//tr[td[div[contains(text(),'Last result')]]]//td[4]//select")
		public WebElement LastResult;
		
		@FindBy(how=How.XPATH,using="//span[@id='Content/OrganizerSearch[CronJob]_searchbutton']")
		public WebElement SearchButton_cronJob;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
