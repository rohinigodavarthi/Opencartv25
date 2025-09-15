package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver;
public Logger logger;
public Properties propobj;

	@BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{	
		//loading properties file
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		propobj = new Properties();
		propobj.load(file);

		logger = LogManager.getLogger(this.getClass());
		
		
		if(propobj.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			String huburl ="http://192.168.0.172:4444";
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else
			{
				System.out.println("No Matching OS..");
				return;
			}
			
			switch(br.toLowerCase())
			{
				case  "chrome" : capabilities.setBrowserName("chrome"); break;
				case   "edge"  : capabilities.setBrowserName("MicrosoftEdge"); break;
				case  "firefox" : capabilities.setBrowserName("firefox"); break;
				default: System.out.println("No Matching browser.."); return;
			}
			
			 driver = new RemoteWebDriver(new URL(huburl),capabilities);
		}
		
		if(propobj.getProperty("execution_env").equalsIgnoreCase("local"))
		{		
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default: System.out.println("Invalid Browser"); return;
			}	
		}
		driver.manage().deleteAllCookies();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(propobj.getProperty("appUrl")); //reading url from properties file
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master","Datadriven"})
	public void tearDown()
	{
	  driver.quit();	
	}
	
	public String randomeString()
	{
		@SuppressWarnings("deprecation")
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		@SuppressWarnings("deprecation")
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlphaNumeric()
	{
		@SuppressWarnings("deprecation")
		String  generatedString=RandomStringUtils.randomAlphabetic(3);
		@SuppressWarnings("deprecation")
		String generatedNumber=RandomStringUtils.randomNumeric(5);
		return (generatedString+"@"+generatedNumber);
	}
	
	public String captureScreen(String tname) 
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
		
	}
	
	
	
}
