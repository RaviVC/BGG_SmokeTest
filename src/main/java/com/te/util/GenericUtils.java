package com.te.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import com.google.common.base.Predicate;

/**
* GenericUtils is a class which covers all generic operations which we perform
* on the web elements
* 
 */
public class GenericUtils {
                /*-------------------------------------------------------------------------
                * Static fields.
                *-----------------------------------------------------------------------*/
                public static WebDriver driver = null;
                public static WebDriverWait wait = null;
                public static Properties CONFIG = null;
                public static Properties OBJECTREPO=null;
                public static Properties GENERATEDUSER=null;
                public static Process warning;
                

                /**
                * This method returns a driver for a specific browser type
                * 
                 * @param Browser
                *            ex: IE|FF
                * @throws IOException 
                 * @throws InterruptedException 
                 * 
                 */
                public void configure() {
                
                                try {
                                                
                                                CONFIG = new Properties();
                                                FileInputStream fis = new FileInputStream("config\\config.properties");
                                                CONFIG.load(fis);
                                } catch (FileNotFoundException e1) {
                                                Reporter.log("Config file not found",true);
                                                e1.printStackTrace();
                                } catch (IOException e1) {                              
                                                e1.printStackTrace();
                                }
                                System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe"); 
                                driver = new ChromeDriver();
                                
                                /*DesiredCapabilities dc = DesiredCapabilities.chrome();
                                dc.setBrowserName("chrome");
                                dc.setPlatform(Platform.WIN10);
                                try {
                                	System.out.println(IP_ADDRESS);
									driver = new RemoteWebDriver(new URL("http://"+IP_ADDRESS+"/wd/hub"),dc);
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
                                
                                
                                
                                driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
                                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                                driver.manage().window().maximize();
                        
                }

                /**
                * This method handles the security warnings dialog boxes and to clear the
                * java cache by calling the appropriate AUTOIT scripts from the lib package
                * .
                * 
                 */
                /**
                * ------------------------------------------------------------------------
                * ------------------- For Clearing javacache on windows
                * 
                 * Its required to set the path of java home to ex: "C:\Program
                * Files\Java\jdk1.X.X\bin;" in System environment variable
                * ------------------
                * -------------------------------------------------------------------------
                */


                /*
                * This method is used to launch TP by fetching the url specified in the
                * properties file.
                */

                public void launchApp(String url, String Env){
                	String targeturl=null;
                	if(Env.equalsIgnoreCase("QA"))
                	{
                	 targeturl=	url.replace("hybris.", "hybris-qa.");
                	}
                	if(Env.equalsIgnoreCase("prvw"))
                	{
                	 targeturl=	url.replace("hybris.", "hybris-prvw.");
                	}
                	if(Env.equalsIgnoreCase("altq"))
                	{
                	 targeturl=	url.replace("hybris.", "hybris-altq.");
                	}
                	else if (Env.equalsIgnoreCase("Stage")) {
                		targeturl=	url.replace("hybris.", "hybris-stage.");
					}
                	else if (Env.equalsIgnoreCase("SUPT")) {
                		targeturl=	url.replace("hybris.", "hybris-supt.");
					}
                		System.out.println("uuuuuuu"+targeturl);
                	
                		driver.get(targeturl);
                }
                
               

                /**
                * 
                 * This method is used to Enter text in the specific web element
                * 
                 * @param locator
                *            is the xpath of the webelement.
                * @param value
                *            is the string to enter in the webelement.
                * 
                 */
                public void enterText(String locator, String value) {
                                                WebElement element = getObject(locator);
                                                element.clear();
                                                element.sendKeys(value);
                                                Reporter.log(element.getAttribute("name")+"Updated with: " + value,true);
                }

                /**
                * 
                 * 
                 * This method is used to perform Clicks on the Web element(Link or Button)
                * 
                 * @param locator
                *            is the xpath of the webelement
                * 
                 * 
                 */

                public  void click(String locator) {
                                WebElement element = getObject(locator);
                                element.click();
                }
               
                
                
                public static boolean switchWindow(String title){
                                Set<String> handles = driver.getWindowHandles();
                                if(!handles.isEmpty())
                                for (String win : handles) {
                                                if(driver.switchTo().window(win).getTitle().equals(title)){
                                                                return true;
                                                }else
                                                {
                                                                driver.switchTo().window(win);
                                                }
                                }              
                                return false;
                }
                
                public void elementStatus(WebElement element){
                                if (element.isDisplayed()) {
                                }
                                else{
                                                Reporter.log(element.getText()+" element is not Displayed",true);
                                }
                                                
                                if (element.isEnabled()) {

                                } 
                                else{
                                                Reporter.log(element.getText()+" element is not Enabled",true);
                                }
                }
                
                

                /**
                * 
                 * To wait for the specified milliseconds
                * 
                 * @param millisecs
                *            milliseconds to wait.
                * 
                 * 
                 */
                public static void threadSleepWait(long millisecs) {
                                try {
                                                Thread.sleep(millisecs);
                                } catch (InterruptedException e) {

                                                e.printStackTrace();
                                }
                }
                
                public void threadsleepdelay(long millisecs){
                	try {
						Thread.sleep(millisecs);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
                }

                /**
                * 
                 * To wait for the specified milliseconds
                * 
                 * @param millisecs
                *            milliseconds to wait.
                * @return
                * 
                 * 
                 */
                public static WebDriverWait explicitWait() {
                                return wait = new WebDriverWait(driver, 90);
                }
                

                /**
                * 
                 * This method is used to switch the driver to a frame based on the passed
                * parameter
                * 
                 * 
                 * @param locator
                *            xpath for the frame
                * 
                 * 
                 */
                public static void switchToFrame(String locator) {
                                driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
                                WebElement fr1 = driver.findElement(By.xpath(locator));
                                driver.switchTo().frame(fr1);
                }

                /**
                * 
                 * To check the specified text field is updated with output generated(used
                * for validation)
                * 
                 * @param locator
                *            of the webelement to be verified
                * 
                 * @return boolean true if value is updated else false
                */

                public boolean verifyOutputfield(String locator) {

                                wait = new WebDriverWait(driver, 60);
                                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
                                String fieldvalue = element.getAttribute("value");             
                                
                                if (!fieldvalue.isEmpty()) {

                                                return true;
                                } else {
                                                return false;
                                }
                }
                
                
                /**
                * 
                 * To check the status of the alert during user authentication
                * 
                 * @return boolean true if alert contains success else false.
                * 
                 */

                public boolean AlertStatus() {
                                wait = new WebDriverWait(driver, 120);
                                wait.until(ExpectedConditions.alertIsPresent());
                                Alert alert = driver.switchTo().alert();
                                String status = alert.getText();
                                alert.accept();
                                if (status.contains("SUCCESS")) {
                                                return true;
                                } else {
                                                Reporter.log(status);
                                                return false;
                                }
                }

                /**
                * 
                 * This method compares the actual title of a Web page with the passed
                * expected string.
                * 
                 * @param expected
                *            true if actual title is same as the expected title else false
                * @return boolean true if title matches else false
                * 
                 * 
                 */
                public boolean getTitle(String expected) {
                                String actual = driver.getTitle();
                                if (actual.trim().equalsIgnoreCase(expected)) {

                                                return true;
                                } else {
                                                return false;
                                }
                }
                
                public WebElement getObject(String xpathExpression){
                                WebElement element;
                                try {
                                                element=driver.findElement(By.xpath(xpathExpression));
                                                elementStatus(element);
                                                return element;
                                } catch (Exception e) {
                                               
                                                Reporter.log("Element not found for the expression"+xpathExpression,true);                                       
                                                e.printStackTrace();
                                }
                                return null;
                                                
                }
                
               
                

                /**
                * 
                 * This method is used to check the checkbox
                * 
                 * @param locator
                *            xpath for the checkbox to check.
                * 
                 */
                public void checkCheckBox(String locator) {
                                WebElement chkboxele = getObject(locator);
                                if (!chkboxele.isSelected()) {
                                                chkboxele.click();
                                                Reporter.log("Checked the checkbox: "+ chkboxele.getAttribute("name"),true);
                                } else {
                                                Reporter.log("Checkbox: " + chkboxele.getAttribute("name")+ " is already selected",true);
                                }
                }

                /**
                * 
                 * This method is used to uncheck the checkbox
                * 
                 * @param locator
                *            xpath for the checkbox to uncheck.
                * 
                 */

                public void uncheckCheckBox(String locator) {
                                WebElement chkbox = getObject(locator);
                                if (chkbox.isSelected()) {
                                                chkbox.click();
                                                Reporter.log("Unchecked the checkbox: "
                                                                                + chkbox.getAttribute("name"),true);
                                } else {
                                                Reporter.log("Checkbox: " + chkbox.getAttribute("name")
                                                                                + " is already unchecked",true);
                                }
                }

                
                
                

                public static String getRand() {

                                long currentTime = System.currentTimeMillis();

                                Random r = new Random(currentTime);
                                int Low = 1000;
                                int High = 9080;
                                int R = r.nextInt(High - Low) + Low;

                                return Integer.toString(R + 1);

                }
                
                
                public static Process executeAutoITScript(String path){
                                
                                try {
                                                warning=Runtime.getRuntime().exec(path);                        
                                } catch (IOException e1) {
                                                
                                }
                                return warning;
                                                               
                }
                
                public static void waitUntilScriptExecutes(Process p){
                                try {
                                                p.waitFor();
                                } catch (InterruptedException e) {
                                                e.printStackTrace();
                                }
                }
                
                public void waitForLoad(WebDriver driver) {
                   /* ExpectedCondition <Boolean> pageLoadCondition = new
                            ExpectedCondition <Boolean>() {
                                public Boolean apply(WebDriver driver) {
                                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                                }
                            };*/
                	 WebDriverWait wait = new WebDriverWait(driver, 60);

                	    Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

                	        public boolean apply(WebDriver input) {
                	            return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
                	        }

                	    };
                	    wait.until(pageLoaded);
                }

                public void select(WebElement locator, String value) {
                                Select obj = new Select(locator);
                                obj.selectByVisibleText(value);
//                                Reporter.log(" Selected the value: "+ obj.getFirstSelectedOption().getText(),true);
                }
                
              public String getDate(){
            	String pattern = "MM/dd/yyyy";
          		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
          		String date = simpleDateFormat.format(new Date());
//          		System.out.println(date);
          		return date;
              }
                
                
                
                
                //******************************	Ravi CODE **************************************
                
                public static boolean checkbox_check(WebElement checkbox){
                	boolean checkstatus;
                	checkstatus = checkbox.isSelected();
                	if(checkstatus == true)
                	{
                		Reporter.log("Check box already selected");
                	}
                	
                	else{
                		checkbox.click();
                	}
                	return checkstatus;
                }
                
                public static boolean checkbox_uncheck(WebElement checkbox){
                	boolean checkstatus;
                	checkstatus = checkbox.isSelected();
                	if(checkstatus == true){
                		checkbox.click();
                		Reporter.log("check box selected , uncheck it");
                	}
                	else{
                		Reporter.log("Checkbox is not selected");
                	}
					return checkstatus;
                }
                
                public static boolean radiobutton_select(WebElement radiobutton){
                	boolean checkstatus;
                	checkstatus = radiobutton.isSelected();
                	if(checkstatus == true){
                		Reporter.log("Radiobutton is already selected");
                	}else{
                		radiobutton.click();
                		Reporter.log("Selected the Radiobutton");
                	}
					return checkstatus;
                }
                
                public static boolean radiobutton_deselect(WebElement radiobutton){
                	boolean checkstatus;
                	checkstatus = radiobutton.isSelected();
                	if(checkstatus == true){
                		radiobutton.click();
                	}else{
                		Reporter.log("Radiobutton was already deselected");
                	}
					return checkstatus;
                }
                
                

}
