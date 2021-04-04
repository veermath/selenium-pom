package in.amazon.testdrive;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.amazon.pages.BooksPage;
import in.amazon.pages.CartPage;
import in.amazon.pages.DepartmentsPage;
import in.amazon.pages.ElectronicsPage;
import in.amazon.pages.HomePage;
import in.amazon.pages.MemoryCardsPage;
import in.amazon.util.ReadWriteXL;
import in.amazon.util.Utility;

public class AmazonTestExecutor 
{
	FileInputStream fis;
	Properties properties;
	WebDriver driver;

	@BeforeClass
	public void initialize() throws IOException
	{
		fis = new FileInputStream(".\\AmazonTestData\\AmazonTestData.properties");
		properties = new Properties();
		properties.load(fis);
		
		driver = Utility.openBrowser(properties.getProperty("BROWSERNAME"));
		Utility.openUrl(properties.getProperty("URL"));
	}
	
	@Test(dataProvider="amazondata",dataProviderClass=ReadWriteXL.class,priority=1)
	public void favouriteAuthor(String enterAuthorName, String verifyAuthorName) 
	{
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		homePage.clickOnSearchBoxOrCategory(properties.getProperty("SEARCHBOX"));
		homePage.sendFavouriteAuthorName(properties.getProperty("SEARCHBOX"), enterAuthorName);
		homePage.clickOnSearchButton(properties.getProperty("SEARCHBUTTON"));
		
		BooksPage booksPage = PageFactory.initElements(driver, BooksPage.class);
		
		boolean isAuthorAvailable = booksPage.verifyAuthorName(properties.getProperty("VERIFYAUTHORNAME"), verifyAuthorName);
		if(isAuthorAvailable)
		{
			booksPage.clickOnBook(properties.getProperty("CLICKONAUTHORBOOK"));
			booksPage.getAddCartWindow();
			System.out.println(driver.getTitle());
			booksPage.clickOnAddToCart(properties.getProperty("CLICKONADDTOCART"));
			driver.switchTo();
			System.out.println("Success: Favourite Author Book is added to Cart.");
		}
		else
		{
			System.out.println("Failure: Favourite Author Book is not available.");
		}
	}
	
	@Test(dataProvider="amazondata",dataProviderClass=ReadWriteXL.class,priority=2)
	public void lightningDeal(String iphone)
	{
		driver.navigate().to(properties.getProperty("URL"));
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		homePage.selectCategoryDropdown(properties.getProperty("CLICKONCATEGORY"));
		homePage.selectElectronicsSubCategory(properties.getProperty("CLICKONELECTRONICS"));
		
		BooksPage booksPage = PageFactory.initElements(driver, BooksPage.class);
		
		ElectronicsPage electronicsPage = PageFactory.initElements(driver, ElectronicsPage.class);
		electronicsPage.clickOnSearchBox(properties.getProperty("SEARCHBOX"));
		electronicsPage.sendPhoneName(properties.getProperty("SEARCHBOX"), iphone);
		electronicsPage.clickOnSearchButton(properties.getProperty("SEARCHBUTTON"));
		driver.switchTo();
		electronicsPage.waitForIphonesPageToLoad(properties.getProperty("WAITFORIPHONEPAGETOLOAD"));
		String lightningDealUrl = electronicsPage.filterOnIphoneModels(properties.getProperty("FILTERONIPHONEMODEL"), properties.getProperty("LIGHTNINGDEAL"));
		if(lightningDealUrl != null)
		{
			driver.get(lightningDealUrl);
			driver.switchTo();
			electronicsPage.getProductName(properties.getProperty("GETIPHONEPRODUCTNAME"));
			booksPage.getAddCartWindow();
			electronicsPage.clickOnAddToCart(properties.getProperty("CLICKONADDTOCART"));
			System.out.println("User must signin if there is Lightning Deal.");
			System.out.println("Success: iPhone added to the cart");
		}
		else
		{
			System.out.println("Failure: None of the iphones have Lightning Deal");
		}
	}
	
	@Test(dataProvider="amazondata",dataProviderClass=ReadWriteXL.class, priority=3)
	public void leastPriceProduct(String expectedPrice)
	{
		driver.navigate().to(properties.getProperty("URL"));
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		BooksPage booksPage = PageFactory.initElements(driver, BooksPage.class);
		
		homePage.clickOnSearchBoxOrCategory(properties.getProperty("CLICKONSHOPBYCATEGORY"));
		driver.switchTo();
		DepartmentsPage departmentsPage = PageFactory.initElements(driver, DepartmentsPage.class);
		departmentsPage.clickOnMemoryCards(properties.getProperty("CLICKONMEMORYCARDS"));
		driver.switchTo();
		
		MemoryCardsPage memoryCardsPage = PageFactory.initElements(driver, MemoryCardsPage.class);
		memoryCardsPage.select16GBMemoryCard(properties.getProperty("SELECT16GB"));
		driver.switchTo();
		WebElement memoryCard = memoryCardsPage.getLeastPriceMemoryCards(properties.getProperty("PRICELISTOFMEMORYCARDS"), expectedPrice);
		if(memoryCard != null)
		{
			memoryCard.click();
			booksPage.getAddCartWindow();
			driver.switchTo();
			memoryCardsPage.waitForMemoryCardPageToLoad(properties.getProperty("WAITFORMEMORYCARDPAGETOLOAD"));
			memoryCardsPage.getProductName(properties.getProperty("GETPRODUCTNAME"));
			memoryCardsPage.clickOnAddToCart(properties.getProperty("CLICKONADDTOCART"));
			System.out.println("Success: Least Price Product is added to Cart.");
		}
		else
		{
			System.out.println("Failure: Least Price Product is not available");
		}
	}
	
	@Test(dataProvider="amazondata",dataProviderClass=ReadWriteXL.class,priority=4)
	public void checkCartItems() throws IOException
	{
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.clickOnCart(properties.getProperty("CLICKONCART"));
		
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
		cartPage.checkProductsInCart(properties.getProperty("PRODUCTSINCART"));
		
		cartPage.deleteProductListInFile("");
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
}
