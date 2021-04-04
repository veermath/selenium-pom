package in.amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.amazon.util.FileHelper;
import in.amazon.util.FindElementByAnyLocator;

public class MemoryCardsPage extends FindElementByAnyLocator
{
	public MemoryCardsPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void select16GBMemoryCard(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
	
	public void clickOnSortBy(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
	
	public void clickOnSortByPriceLowToHigh(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
	
	public WebElement getLeastPriceMemoryCards(String any8Locator, String expectedPrice)
	{
		double memoryCardPrice = Double.parseDouble(expectedPrice.replace('"', ' '));
		List<WebElement> listOfMemoryCards =  getAnyElementsByAnyLocator(any8Locator);
		ArrayList<String> comparePrice = new ArrayList<String>();
		for(WebElement memoryCard : listOfMemoryCards)
		{
			comparePrice.add(memoryCard.getText());
			if(Double.parseDouble(memoryCard.getText().replace(",", "")) < memoryCardPrice)
			{
				return memoryCard;
			}
		}
		/*for(int i=0; i<comparePrice.size() ; i++)
		{
			if(Double.parseDouble(comparePrice.get(i).toString().replace(",", "")) < memoryCardPrice)
			{
				System.out.println("Found one product");
			}
		}*/
		
		return null;
	}
	
	public void waitForMemoryCardPageToLoad(String any8Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(any8Locator)));
	}
	
	public void getProductName(String any8Locator)
	{
		FileHelper fileHelper = new FileHelper();
		WebElement productName = getAnyElementByAnyLocator(any8Locator);
		fileHelper.writeToAFile(productName.getText());
	}
	
	public void clickOnAddToCart(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
}
