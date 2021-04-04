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

public class ElectronicsPage extends FindElementByAnyLocator 
{
	public ElectronicsPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public void clickOnElectronicSubCategory(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
	
	public void clickOnSearchBox(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
	
	public void sendPhoneName(String any8Locator, String input)
	{
		getAnyElementByAnyLocator(any8Locator).sendKeys(input);;
	}
	
	public void clickOnSearchButton(String any8Locator)
	{
		getAnyElementByAnyLocator(any8Locator).click();
	}
	
	public void waitForIphonesPageToLoad(String any8Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(any8Locator)));
	}
	
	public String filterOnIphoneModels(String iPhoneModelsPath, String lightningDealPath)
	{
		List<WebElement> listOfiPhoneModels = getAnyElementsByAnyLocator(iPhoneModelsPath);
		ArrayList<String> iPhoneLinks = new ArrayList<String>();
		ArrayList<String> lightningDealLinks = new ArrayList<String>();
		
		System.out.println("There are " + listOfiPhoneModels.size() + " iPhone Models.");
		for(WebElement iphoneLink : listOfiPhoneModels)
		{
			iPhoneLinks.add(iphoneLink.getAttribute("href"));
		}
		
		List<WebElement> lightningDeals = getAnyElementsByAnyLocator(lightningDealPath);
		if(!lightningDeals.isEmpty())
		{
			for(WebElement lightningDeal : lightningDeals)
			{
				lightningDealLinks.add(lightningDeal.getAttribute("href"));
			}
			if(iPhoneLinks.get(0).equals(lightningDealLinks.get(0)))
				return iPhoneLinks.get(0);
		}
		return null;
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
