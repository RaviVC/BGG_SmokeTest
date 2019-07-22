package com.qa.bg.testScripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.qa.bg.pages.HomePage;
import com.qa.bg.util.GenericUtils;

public class Search extends GenericUtils{
	
	
	@Test 
	public void SearchDestination(){
		configure();
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		
		launchApp(CONFIG.getProperty("BGG_URL"), "stg");
		System.out.println("BGG Launched");
	}
}
