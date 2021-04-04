package in.amazon.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import in.amazon.util.FileHelper;
import in.amazon.util.FindElementByAnyLocator;

public class BooksPage extends FindElementByAnyLocator 
{
	public BooksPage (WebDriver driver)
	{
		super(driver);
	}
	
	public boolean verifyAuthorName(String any8Locator, String input)
	{
		 
		List<WebElement> elements = getAnyElementsByAnyLocator(any8Locator);
		for(WebElement element : elements)
		{
			if(element.getText().equalsIgnoreCase(input))
			{
				System.out.println("Favorite Author Book is available");
				return true;
			}
		}
		return false;
	}
	
	public void getAddCartWindow()
	{
		Set<String>windows = driver.getWindowHandles();
		Iterator<String> window = windows.iterator();
		String mainWindow = window.next();
		String secondWindow = window.next();
		driver.close();
		driver.switchTo().window(secondWindow);
	}
	
	public String clickOnBook(String any8Locator)
	{
		FileHelper fileHelper = new FileHelper();
		WebElement element = getAnyElementByAnyLocator(any8Locator);
		element.click();
		fileHelper.writeToAFile(element.getText());
		return element.getAttribute("href");
	}
	
	public void clickOnAddToCart(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
}
