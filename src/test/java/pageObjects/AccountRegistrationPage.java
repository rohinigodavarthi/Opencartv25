package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//WebElements
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPrivacy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()=\"Your Account Has Been Created!\"]")
	WebElement msgConfirmation;
	
	
	//Action methods
	
	public void setFirstName(String Fname)
	{
		txtFirstName.sendKeys(Fname);
	}
	
	public void setLastName(String Lname)
	{
		txtLastName.sendKeys(Lname);
	}
	
	public void setEmail(String Email)
	{
		txtEmail.sendKeys(Email);
	}
	
	public void setTelephone(String Telephone)
	{
		txtTelephone.sendKeys(Telephone);
	}
	
	public void setPassword(String Password)
	{
		txtPassword.sendKeys(Password);
	}
	public void setConfirmPassword(String ConfirmPassword)
	{
		txtConfirmPassword.sendKeys(ConfirmPassword);
	}
	
	public void checkPrivacy()
	{
		chkdPrivacy.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public String getConfirmation()
	{
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

}
