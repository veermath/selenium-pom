package in.amazon.pages;

import org.openqa.selenium.WebDriver;

import in.amazon.util.FindElementByAnyLocator;

public class DepartmentsPage extends FindElementByAnyLocator 
{
	public DepartmentsPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void clickOnMemoryCards(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
}
