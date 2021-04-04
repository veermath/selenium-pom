package in.amazon.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class FindElementByAnyLocator 
{
protected WebDriver driver = null;
	
	protected FindElementByAnyLocator(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	protected WebElement getAnyElementByAnyLocator(String anylocatorPath)
	{
		if(anylocatorPath.startsWith("cl_"))
		{
			return driver.findElement(By.className(anylocatorPath.replace("cl_", "")));
		}
		else if(anylocatorPath.startsWith("c_"))
		{
			return driver.findElement(By.cssSelector(anylocatorPath.replace("c_", "")));
		}
		else if(anylocatorPath.startsWith("i_"))
		{
			return driver.findElement(By.id(anylocatorPath.replace("i_", "")));
		}
		else if(anylocatorPath.startsWith("l_"))
		{
			return driver.findElement(By.linkText(anylocatorPath.replace("l_", "")));
		}
		else if(anylocatorPath.startsWith("n_"))
		{
			return driver.findElement(By.name(anylocatorPath.replace("n_", "")));
		}
		else if(anylocatorPath.startsWith("p_"))
		{
			return driver.findElement(By.partialLinkText(anylocatorPath.replace("p_", "")));
		}
		else if(anylocatorPath.startsWith("t_"))
		{
			return driver.findElement(By.tagName(anylocatorPath.replace("t_", "")));
		}
		else if(anylocatorPath.startsWith("x_"))
		{
			return driver.findElement(By.xpath(anylocatorPath.replace("x_", "")));
		}
		return null;
	}
	
	protected List<WebElement> getAnyElementsByAnyLocator(String anylocatorPath)
	{
		if(anylocatorPath.startsWith("cl_"))
		{
			return driver.findElements(By.className(anylocatorPath.replace("cl_", "")));
		}
		else if(anylocatorPath.startsWith("c_"))
		{
			return driver.findElements(By.cssSelector(anylocatorPath.replace("c_", "")));
		}
		else if(anylocatorPath.startsWith("i_"))
		{
			return driver.findElements(By.id(anylocatorPath.replace("i_", "")));
		}
		else if(anylocatorPath.startsWith("l_"))
		{
			return driver.findElements(By.linkText(anylocatorPath.replace("l_", "")));
		}
		else if(anylocatorPath.startsWith("n_"))
		{
			return driver.findElements(By.name(anylocatorPath.replace("n_", "")));
		}
		else if(anylocatorPath.startsWith("p_"))
		{
			return driver.findElements(By.partialLinkText(anylocatorPath.replace("p_", "")));
		}
		else if(anylocatorPath.startsWith("t_"))
		{
			return driver.findElements(By.tagName(anylocatorPath.replace("t_", "")));
		}
		else if(anylocatorPath.startsWith("x_"))
		{
			return driver.findElements(By.xpath(anylocatorPath.replace("x_", "")));
		}
		return null;
	}
}
