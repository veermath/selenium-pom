package in.amazon.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import in.amazon.util.FileHelper;
import in.amazon.util.FindElementByAnyLocator;

public class CartPage extends FindElementByAnyLocator
{
	public CartPage (WebDriver driver)
	{
		super(driver);
	}
	
	public void checkProductsInCart(String any8Locator) throws IOException
	{
		File file = new File(".\\AmazonTestData\\result.txt");
		FileHelper fileHelper = new FileHelper();
		
		String eachLine = fileHelper.getFileContents(file);
		String[] listOfProducts = eachLine.split(" \\| ");
		List<WebElement> products = getAnyElementsByAnyLocator(any8Locator);
		ArrayList<String> matchProduct = new ArrayList<String>();
		for(WebElement eachProduct: products)
		{
			matchProduct.add(eachProduct.getText());
		}

		for(int i=0; i<products.size(); i++)
		{
			if(Arrays.asList(listOfProducts).contains(matchProduct.get(i)))
			{
				System.out.println("Success: "+ matchProduct.get(i) +" Product added to Cart.");
			}
			else
			{
				System.out.println("Failure: "+ matchProduct.get(i) +" product not added to Cart.");
			}
		}
	}
	
	public void deleteProductListInFile(String content)
	{
		FileHelper fileHelper = new FileHelper();
		fileHelper.deletContentInFile(content);
	}
}
