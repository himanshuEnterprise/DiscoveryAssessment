package utilities;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DiscoveryUtill {

	private WebDriver driver;
	public DiscoveryUtill(WebDriver driver) {
		this.driver = driverInitialize.getDriver();
	}
	Actions actions;
		
		public void waitForTextToPresent(String name, String ctrlProps, String textExpected) throws Exception {

        By webElementLocator = null;
        webElementLocator = getWebElementLocator(name, ctrlProps);

        new WebDriverWait(driver, 30).
                until(ExpectedConditions.textToBePresentInElementValue(webElementLocator, textExpected));

        new FluentWait<By>(webElementLocator).
                withTimeout(30, TimeUnit.SECONDS).
                pollingEvery(5, TimeUnit.SECONDS).
                ignoring(NoSuchElementException.class);
    }
	
	
	public By getWebElementLocator(String name, String ctrlProps) {
        try {
            if (ctrlProps.equalsIgnoreCase("ID")) {
                return By.id(name);
            } else if (ctrlProps.equalsIgnoreCase("Name")) {
                return (By.name(name));
            } else if (ctrlProps.equalsIgnoreCase("CLASSNAME")) {
                return (By.className(name));
            } else if (ctrlProps.equalsIgnoreCase("LINK")) {
                return (By.linkText(name));
            } else if (ctrlProps.equalsIgnoreCase("PARTIALLINK")) {
                return (By.partialLinkText(name));
            } else if (ctrlProps.equalsIgnoreCase("CSSSELECTOR")) {
                return (By.cssSelector(name));
            } else if (ctrlProps.equalsIgnoreCase("TAGNAME")) {
                return (By.tagName(name));
            } else if (ctrlProps.equalsIgnoreCase("XPATH")) {
                return (By.xpath(name));
            }
            
        } catch (Exception e) {           
            Assert.assertNull(e);
        }
        return null;

    }
	
	public void scrollToElement(String element, String ctrlPropeties) {
        (new Actions(this.driver)).moveToElement(getWebElement(element, ctrlPropeties)).perform();
    }
	
	public WebElement getWebElement(String name, String ctrlProps) {
        try {
              if (ctrlProps.equalsIgnoreCase("ID")) {          
                    return driver.findElement(By.id(name));        
        	 } else if (ctrlProps.equalsIgnoreCase("Name")) {
                return driver.findElement(By.name(name));
        	 } else if (ctrlProps.equalsIgnoreCase("className")) {
                return driver.findElement(By.className(name));
        	 } else if (ctrlProps.equalsIgnoreCase("LinkText")) {
                return driver.findElement(By.linkText(name));
        	 } else if (ctrlProps.equalsIgnoreCase("PartialLinkText")) {
                return driver.findElement(By.partialLinkText(name));
        	 } else if (ctrlProps.equalsIgnoreCase("CssSelector")) {
                return driver.findElement(By.cssSelector(name));
        	 } else if (ctrlProps.equalsIgnoreCase("TagName")) {
                return driver.findElement(By.tagName(name));
        	 } else if (ctrlProps.equalsIgnoreCase("XPATH")) {
        		 return driver.findElement(By.xpath(name));
             }else if (ctrlProps.equalsIgnoreCase("")) { 
        		 return null;
        	 }            
        } catch (Exception e) {
            
            Assert.assertNull(e);

        }
        return null;

    }
	
	 public void waitForPageLoad() throws Exception {
	        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        try {
	            wait.until(expectation);
	        } catch (Exception error) {
	            Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");
	        }
	    }
	
	 public HashMap<String, String> getHomePageTitleDescription() throws InterruptedException {
			HashMap<String, String> titleDescription = new HashMap<String, String>();
			for (int j = 1; j <= 2; j++) {
				actions = new Actions(driver);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				actions.moveToElement(driver.findElement(By.xpath("(//div[.='Recommended']/../following-sibling::div/div/div/div/div/section[@class='tile showTileSquare__main transition-in'])["+j+"]"))).build().perform();
				driver.findElement(By.xpath("(//section[@class='tile showTileSquare__main transition-in'])["+j+"]/div[3]//i[@class='flipIconCore__icon icon-plus ']")).click();				
				String notification = driver.findElement(By.className("notification__main")).getText();
				Assert.assertEquals(notification, "Show added to My Favorite Shows on the My Videos page.");
				String title = driver
						.findElement(By.xpath("(//div[@class='content carousel__content showCarousel__content'])[2]/div["+j+"]//h3/div")).getText();
				String description = driver
						.findElement(By.xpath("(//div[@class='content carousel__content showCarousel__content'])[2]/div["+j+"]//div[@class='showTileSquare__description']/div[1]"))
						.getText();
				titleDescription.put(title, description);
			}
			return titleDescription;
		}

	 public HashMap<String, String> getFavoriteShowsTitleDescription() throws InterruptedException {
			actions = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			HashMap<String, String> favTitleDescription = new HashMap<String, String>();
				for (int k = 1; k <= 2; k++) {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//section[@class='tile showTileSquare__main transition-in'])["+k+"]/div[3]//h3[@class='showTileSquare__title']/div")));
					actions.moveToElement(driver.findElement(By.xpath("(//section[@class='tile showTileSquare__main transition-in'])["+k+"]/div[3]//h3[@class='showTileSquare__title']/div"))).build().perform();
					String favTitle = driver.findElement(By.xpath("(//section[@class='tile showTileSquare__main transition-in'])["+k+"]/div[3]//h3[@class='showTileSquare__title']/div")).getText();
					String favDescription = driver.findElement(By.xpath("(//section[@class='tile showTileSquare__main transition-in'])["+k+"]/div[3]//div[@class='showTileSquare__description']/div")).getText();
					favTitleDescription.put(favTitle, favDescription);
				}
			
			return favTitleDescription;

		}
}	

