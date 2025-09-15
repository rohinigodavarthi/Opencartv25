package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass =DataProviders.class, groups="Datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("***** Starting TC003_LoginDDT *****");
		try {
		
		//Homepage	
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		logger.info("Navigated to Home Page..");
		
		//Loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		logger.info("Navigated to Login Page..");
		
		//MyAccountpage
		MyAccountPage myaccount = new MyAccountPage(driver);
		boolean targetpage = myaccount.isMyAccountPageExist();

		/*Data --Valid, Login success	TC Passed
		Data -- Valid, Login Failure	TC Failed
		Data--Invalid, Login Success	TC Failed
		Data--Invlaid, Login Failure	TC Passed*/
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				myaccount.linkLogout();
				Assert.assertTrue(true);		
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
				
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				myaccount.linkLogout();
				Assert.assertTrue(false);
				
			}
			
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Throwable e)
		{
			Assert.fail();
		}
		finally {
			logger.info("***** Finished TC003_LoginDDT *****");
		}
		
	}
	
	
}
