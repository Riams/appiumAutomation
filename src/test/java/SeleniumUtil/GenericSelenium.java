package SeleniumUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class GenericSelenium extends PageBase 
{
	
	
	
	protected void acceptTermsAndCondition() 
	{
		clickElement("xpath","//*[@resource-id='com.buuuk.st:id/btn_tnc_ok']"); // We can maintain locators in Excel for simplicity than hard coding here.
	}
	
	protected void slideWelcomeScreenTillEnd()
	{
		List<WebElement> elmDots=mobiledriver.findElement(By.xpath("//*[@resource-id='com.buuuk.st:id/viewPagerCountDots']")).findElements(By.className("android.widget.ImageView"));
		int dotsCount=elmDots.size();
		//elmDots.get(dotsCount-1).click(); // Tried this but this approach is not working
		
		
		
		 // Get location of element you want to swipe
	    WebElement banner = mobiledriver.findElement(By.id("com.buuuk.st:id/welcome"));
	    Point bannerPoint = banner.getLocation();
	    // Get size of device screen
	    Dimension screenSize = mobiledriver.manage().window().getSize();
	    // Get start and end coordinates for horizontal swipe
	    int startX = Math.toIntExact(Math.round(screenSize.getWidth() * 0.8));
	    int endX = 0;

	    TouchAction action = new TouchAction(mobiledriver);
	    
	     for(int i=1;i<dotsCount;i++)
	     {
	    action
	            .press(PointOption.point(startX, bannerPoint.getY()))
	            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
	            .moveTo(PointOption.point(endX, bannerPoint.getY()))
	            .release();
	    mobiledriver.performTouchAction(action);
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	     
	     clickElement("id", "com.buuuk.st:id/get_started");
	     screenshot("MenuSlider");
	     
	}
	
	protected void chooseMenuOption(String menuOption)
	{
		try {
			Thread.sleep(2000); //not good appproach but need to find a way to sync
		    clickElement("id", "Navigate up");
		 	Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		 
		switch (menuOption)
		{
		case "login":
			 clickElement("id", "com.buuuk.st:id/tv_login");
			break;

		}
		
		screenshot("MenuOption"+menuOption);
		
	}
	
	protected boolean login(String username,String password) throws IOException
	{
		enterValue("id","com.buuuk.st:id/et_ldap_login_username",username);
		enterValue("id","com.buuuk.st:id/et_ldap_login_password",password);
		
		Reporter.addStepLog("Entered User Credentails");
		String ScreenshotPath=screenshot("Entered username and Password");
		Reporter.addScreenCaptureFromPath(ScreenshotPath);
		
		clickElement("id","com.buuuk.st:id/btn_ldap_login_continue");
		
		clickElement("id", "Navigate up");
		boolean loginSuccess=isElementPresent("id", "com.buuuk.st:id/tv_logout");
		
		clickElement("id", "com.buuuk.st:id/iv_home");
		return loginSuccess;
		
		
	}
	
	public void chooseArticleType(String articleType)
	{
		  List<WebElement> navigationTabs=(List<WebElement>) mobiledriver.findElements(By.xpath("//*[@resource-id='com.buuuk.st:id/tv_tab_title']"));
		    
		    for(WebElement navigationTab : navigationTabs)
		    {
		    	//System.out.println(navigationTab.getText());
		    	if(navigationTab.getText().equalsIgnoreCase(articleType))
		    	{
		    		navigationTab.click();
		    		break;
		    	}
		    }
		    
	}
	
	
	protected static boolean isElementPresent(String locatorType,String locator)
	{
       
        try{
             // here we can use Java reflection to identify the element instead of putting switch case
     		switch (locatorType) 
     		{
     		case "id":
     			mobiledriver.findElement(By.id(locator));
     			break;
     		case "xpath":
     			mobiledriver.findElement(By.xpath(locator));
     			break;

     		}
     		  return true;
        }
        catch (Exception e)
        {
        	return false;
        }

}
	
	
	protected String getText(String locatorType,String locator)
	{
		try{
            // here we can use Java reflection to identify the element instead of putting switch case
			WebElement element=null;
    		switch (locatorType) 
    		{
    		case "id":
    			element=mobiledriver.findElement(By.id(locator));
    			break;
    		case "xpath":
    			element=mobiledriver.findElement(By.xpath(locator));
    			break;

    		}
    		  return element.getText();
       }
		catch(Exception ex)
		{
			
		}
		return null;
	}

	protected boolean logout() throws IOException
	{
		
		
		clickElement("id", "Navigate up");
		clickElement("id", "com.buuuk.st:id/tv_logout");
		return isElementPresent("id", "com.buuuk.st:id/tv_login");
		
		
		
	}

}
