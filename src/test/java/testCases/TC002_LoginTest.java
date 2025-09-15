package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"Regression","Master"})
	public void verify_LoginTest()
	{
		logger.info("***** Starting TC002_LoginTest *****");
		try {
		
		//Homepage	
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		logger.info("Navigated to Home Page..");
		
		//Loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(propobj.getProperty("email"));
		lp.setPassword(propobj.getProperty("password"));
		lp.clickLogin();
		logger.info("Navigated to Login Page..");
		
		//MyAccountpage
		MyAccountPage myaccount = new MyAccountPage(driver);
		boolean targetpage = myaccount.isMyAccountPageExist();
		Assert.assertTrue(targetpage);
		logger.info("Successful Login");
		}
		catch(Throwable e)
		{
			logger.error("Failed to login");
			Assert.fail();	
		}
		
		finally {
			logger.info("***** Finished TC002_LoginTest *****");
		}
		
	}

}
