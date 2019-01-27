package stepdefinitions;

import org.junit.Assert;

import com.cucumber.listener.Reporter;

import SeleniumUtil.GenericSelenium;
import SeleniumUtil.PageBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class articleSteps extends GenericSelenium {
	
	@Given("^I Launch Mobile Application in (.*)$")
	public void i_Launch_Mobile_Application(String platformName) throws Throwable {
	    
		lauchApplication(platformName); // as of now script supports only andorid 
		String ScreenshotPath=screenshot("launchApplication");
	
		Reporter.addStepLog("Application is launched successfully");
		Reporter.addScreenCaptureFromPath(ScreenshotPath);
		
		acceptTermsAndCondition();
		slideWelcomeScreenTillEnd();
		chooseMenuOption("login");
		
		
	}

	

	@Given("^I login with credentials (.*) and (.*)$")
	public void i_login_with_credentials(String userName, String password) throws Throwable
	{
		boolean isLoginSuccess =login(userName,password);
		
		Assert.assertTrue("Failed to Login", isLoginSuccess);
		
	}

	

	@Then("^I click on artcile (.*)$")
	public void i_click_on_artcile(String articleType) throws Throwable {
	    
		chooseArticleType(articleType);
	   
	}

	@Then("^I should be see the first article details as expected in main screen and in artcle detail page$")
	public void i_should_be_see_the_first_article_details_as_expected_in_main_screen_and_in_artcle_detail_page() throws Throwable {
		
		String articleTitle=getText("xpath","//*[@resource-id='com.buuuk.st:id/article_title']");
		clickElement("xpath", "//*[@resource-id='com.buuuk.st:id/article_title']");
		clickElement("id", "com.buuuk.st:id/tv_title");
		String articleHeadline=getText("xpath","//*[@resource-id='com.buuuk.st:id/article_headline']");
		clickElement("id","com.buuuk.st:id/btn_back");
		Assert.assertEquals("Article Headline and Article title mismatch", articleHeadline, articleTitle);
		Assert.assertTrue("Article Image is missing",isElementPresent("xpath", "//*[@resource-id='com.buuuk.st:id/article_image']"));
	   
	}
	
	
	@Then("^I should be able to logout$")
	public void i_should_be_able_to_logout() throws Throwable {
		
        boolean isLogoutSuccess =logout();
		
		Assert.assertTrue("Failed to logout", isLogoutSuccess);
	   
	}
	
	



	

}
