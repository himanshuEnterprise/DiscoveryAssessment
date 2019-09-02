package stepDefinations;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.DiscoveryUtill;
import utilities.driverInitialize;


public class addFavoriteSteps{
	Actions action = null;
	DiscoveryUtill utill ;
	HashMap<String, String> homePageTitleDesc;
	public WebDriver driver;

public  addFavoriteSteps() {
	this.driver = driverInitialize.getDriver();
	utill = new DiscoveryUtill(driver);
}
	@Given("^https://go\\.discovery\\.com/$")
	public void https_go_discovery_com() throws Throwable {
		driver.get("https://go.discovery.com/");
		utill.waitForPageLoad();
	}

	@When("^Title of Home page is Discovery - Official Site$")
	public void title_of_Home_page_is_Discovery_Official_Site() throws Throwable {
		 String pageTitle=driver.getTitle();
		   Assert.assertEquals(pageTitle, "Discovery - Official Site","Page title mismatch");
	}

	@Then("^Scroll home page to RECOMMENDED FOR YOU categories$")
	public void scroll_home_page_to_RECOMMENDED_FOR_YOU_categories() throws Throwable {
		utill.scrollToElement("//div[.='Recommended']", "xpath");
	}

	@Then("^Check number of videos more than two$")
	public void check_number_of_videos_more_than_two() throws Throwable {
		List<WebElement> noOfVideos= driver.findElements
	    		(By.xpath("//div[.='Recommended']/../following-sibling::div/div/div/div/div/section[@class='tile showTileSquare__main transition-in']"));
			int count = noOfVideos.size();
	    Assert.assertTrue(count>2,"no of videos is less than 2");
	}

	@Then("^Get titles and description for two videos of homepage and add videos to favorites$")
	public void get_titles_and_description_for_two_videos_of_homepage_and_add_videos_to_favorites() throws Throwable {
		homePageTitleDesc=utill.getHomePageTitleDescription();
		System.out.println("homepage title description:"+homePageTitleDesc);
	}

	@Then("^Navigate to My videos page$")
	public void navigate_to_My_videos_page() throws Throwable {
		driver.get("https://go.discovery.com/my-videos");
	    utill.waitForPageLoad();
	    String pageTitle=driver.getTitle();
		Assert.assertEquals(pageTitle, "My Videos | Discovery");
	}

	@Then("^Validate title and description of both videos$")
	public void validate_title_and_description_of_both_videos() throws Throwable {
		utill.scrollToElement("//h2[contains(text(),'Favorite Shows')]", "xpath");
		HashMap<String, String> favTitleDesc=utill.getFavoriteShowsTitleDescription();
		System.out.println("favoritepage title description:"+favTitleDesc);
		Assert.assertEquals(homePageTitleDesc, favTitleDesc);
	}

}
