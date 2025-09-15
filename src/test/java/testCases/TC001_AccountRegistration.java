package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;  //Imported it because it resided in another package called testBase

public class TC001_AccountRegistration extends BaseClass {
	
	
	@Test(groups= {"Sanity","Master"})
	public void AccountRegistration()
	{
		logger.info("***** Starting TC0001_AccountRegistration *****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link..");
		hp.clickRegisterLink();
		logger.info("Clicked on Register Link..");
		
		AccountRegistrationPage reg = new AccountRegistrationPage(driver);
		logger.info("Providing User details..");
		reg.setFirstName(randomeString().toUpperCase());
		reg.setLastName(randomeString().toUpperCase());
		reg.setEmail(randomeString()+"@gmail.com");
		reg.setTelephone(randomeNumber());
		
		String pwd =randomeAlphaNumeric();
		
		reg.setPassword(pwd);
		reg.setConfirmPassword(pwd);
		reg.checkPrivacy();
		reg.clickContinue();
		
		String msg = reg.getConfirmation();
		logger.info("Validating expected message...");
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		}
		catch(Throwable e)
		{
			logger.error("Test failed: " + e.getMessage());
			logger.debug("debug logs....");
			Assert.fail("Test failed: " + e.getMessage());

		}			
		finally 
		{
			logger.info("***** Finished TC001_AccountRegistration *****");
		}
		
	}
	
	
}
