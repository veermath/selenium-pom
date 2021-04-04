package in.amazon.pages;

import org.openqa.selenium.WebDriver;

import in.amazon.util.FindElementByAnyLocator;

public class HomePage extends FindElementByAnyLocator {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOnSearchBoxOrCategory(String any8Locator) {
        getAnyElementByAnyLocator(any8Locator).click();
    }

    public void sendFavouriteAuthorName(String any8Locator, String input) {
        getAnyElementByAnyLocator(any8Locator).sendKeys(input);
    }

    public void clickOnSearchButton(String any8Locator) {
        getAnyElementByAnyLocator(any8Locator).click();
    }

    public void selectCategoryDropdown(String category) {
        getAnyElementByAnyLocator(category).click();
    }

    public void selectElectronicsSubCategory(String any8Locator) {
        getAnyElementByAnyLocator(any8Locator).click();
    }

    public void clickOnCart(String any8Locator) {
        getAnyElementByAnyLocator(any8Locator).click();
    }

}
