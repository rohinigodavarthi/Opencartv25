package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//constructors
	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	//WebElements
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath ="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	//Action methods.
	
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void clickRegisterLink()
	{
		lnkRegister.click();
	}
	
	public void clickLoginLink()
	{
		lnkLogin.click();
	}
	
}
