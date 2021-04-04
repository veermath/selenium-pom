package in.amazon.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import in.amazon.pages.HomePage;

public class Utility 
{
	static WebDriver driver = null;
	
	public static WebDriver openBrowser(String browserName)
	{
		if(browserName.startsWith("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.startsWith("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", ".//BrowserExecutableFiles/chromeDriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.startsWith("ie"))
		{
			System.setProperty("webdriver.ie.driver", ".//BrowserExecutableFiles/iedriver.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
	
	public static HomePage openUrl(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		return new HomePage(driver);
	}
	
	public static void takeScreenShot() throws IOException
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:\\seleniumWorkspace\\amazon\\screenShot.png"));
	}

}
