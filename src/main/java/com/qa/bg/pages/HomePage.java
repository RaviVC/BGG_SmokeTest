package com.qa.bg.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.qa.bg.util.GenericUtils;

public class HomePage extends GenericUtils {

	
	@FindBy(how=How.XPATH,using="//input[@name='search']")
	public WebElement SearchEditField;
	
	@FindBy(how=How.XPATH,using="//input[@id='bg-search-btn']")
	public WebElement SearchButton;
}
