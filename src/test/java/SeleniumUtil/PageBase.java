package SeleniumUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
public class PageBase 
{
	public static AndroidDriver<?> mobiledriver;
	public  AndroidDriver<?> lauchApplication(String platformName)
	{
		try 
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			//capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi");
			capabilities.setCapability("appPackage", "com.buuuk.st");
			capabilities.setCapability("appActivity","com.sph.straitstimes.views.activities.SplashActivity");
			capabilities.setCapability("newCommandTimeout", 2000);
			mobiledriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			mobiledriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
		} catch (Exception e) 
		{
			System.out.println("Exception occured in LauchApp");
		}
		return mobiledriver;
		
		
	}
	
	
	public String screenshot(String path_screenshot){
	    File srcFile=mobiledriver.getScreenshotAs(OutputType.FILE);
	    String filename=UUID.randomUUID().toString(); 
	    File targetFile=new File(path_screenshot + filename +".jpg");
	    try {
			FileUtils.copyFile(srcFile,targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return targetFile.getAbsolutePath();
	}
	
	protected void clickElement(String locatorType, String locator)
	{
		// here we can use Java reflection to identify the element instead of putting switch case
		switch (locatorType) 
		{
		case "id":
			mobiledriver.findElement(By.id(locator)).click();
			break;
		case "xpath":
			mobiledriver.findElement(By.xpath(locator)).click();
			break;

		}
		
	}
	
	
	protected void enterValue(String locatorType, String locator,String value)
	{
		// here we can use Java reflection to identify the element instead of putting switch case
		switch (locatorType) 
		{
		case "id":
			mobiledriver.findElement(By.id(locator)).sendKeys(value);
			break;
		case "xpath":
			mobiledriver.findElement(By.xpath(locator)).sendKeys(value);
			break;

		}
		
	}
	
	protected void clickElementifExist(String locatorType, String locator)
	{
		try
		{
		// here we can use Java reflection to identify the element instead of putting switch case
		switch (locatorType) 
		{
		case "id":
			mobiledriver.findElement(By.id(locator)).click();
			break;
		case "xpath":
			mobiledriver.findElement(By.xpath(locator)).click();
			break;

		}
		}
		catch(Exception ex)
		{
			
		}
		
	}
	

	protected void singleTapElement(String locatorType, String locator)
	{
		// here we can use Java reflection to identify the element instead of putting switch case
		WebElement element = null;
		switch (locatorType) 
		{
		case "id":
			element=mobiledriver.findElement(By.id(locator));
			break;
		case "xpath":
			element=mobiledriver.findElement(By.xpath(locator));
			break;

		}
		TouchActions action =new TouchActions(mobiledriver);
		action.singleTap(element);
		action.perform();
		
		
	}
	
	
	
	
	
	

}
