package StepDefinitions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CareersTests
{
	public static WebDriver driver;	
	
	/*
	 *  i.	Navigate to the website.
		ii.	Locate and interact with the search input field.
		iii.	Enter the search criteria.
		iv.	Submit the search form.
		v.	Validate that the search results contain the keyword.
	 * 
	 */
	
	
	
	
		
	
//  Scenario 1: Launch the website
	@Test(priority=0)
	@Parameters("browsr")
	@Given("{string} is started")
	public void is_started(String browsr)
	{
		System.out.println("Running: browser_is_started method...");
		if (browsr.equalsIgnoreCase("Chrome")) 
		{
	         System.out.println(" Executing on Chrome...");
	         ChromeOptions chromeOptions = new ChromeOptions();
	         WebDriverManager.chromedriver().setup();
	         chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking","enable-automation"));
	         chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
	         chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
	         chromeOptions.addArguments("--remote-allow-origins=*");
	         chromeOptions.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
	         chromeOptions.addArguments("--disable-extensions");
	         chromeOptions.addArguments("user-data-dir=/tmp/rajeshsamuel");
	         chromeOptions.addArguments("profile-directory=Profile 1");
	         chromeOptions.addArguments("test-type");
	         Map<String, Object> prefs = new HashMap<String, Object>();
	         prefs.put("credentials_enable_service", false);
	         prefs.put("profile.password_manager_enabled", false);
	         chromeOptions.setExperimentalOption("prefs", prefs);
	         chromeOptions.addArguments("--window-size=1920,1080");
	         chromeOptions.addArguments("--start-maximized");
	         //chromeOptions.addArguments("--headless");
	         driver = new ChromeDriver(chromeOptions);
	         JavascriptExecutor scrptExec = (JavascriptExecutor)driver;	
	         scrptExec.executeScript("Object.defineProperty(navigator, 'driver', {get: () => undefined})");
	         
		} 
		//else if (browsr.equalsIgnoreCase("Internet Explorer")) 
		//{
	    //     System.out.println(" Executing on Firefox...");
//	    //     /Applications/Firefox.app/Contents/MacOS/firefox-bin -profilemanager
	    //     driver = new FirefoxDriver();
		//}
		else 
		{
	         throw new IllegalArgumentException("The Browser Type is Undefined");
	    }
	    
	}

	@Test(priority=1)
	@When("^user launches the deutsche bank website$")
	public void user_launches_the_deutsche_bank_website() {
		System.out.println("Running: user_launches_the_deutsche_bank_website method...");
		driver.get("https://careers.db.com/");
		System.out.println("Website is launched...");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		JavascriptExecutor jsCookies = (JavascriptExecutor) driver;
		WebElement cookie_button = (WebElement) jsCookies.executeScript("return document.querySelector('#usercentrics-root').shadowRoot.querySelector(\"button[data-testid='uc-accept-all-button']\")");
		cookie_button.click();
		
		//jsCookies.executeScript("""return document.querySelector('#usercentrics-root').shadowRoot.querySelector("button[data-testid='uc-accept-all-button']")""");
	     
	}

	@Test(priority=2)
	@Then("^deutsche bank home page is displayed$")
	public void deutsche_bank_home_page_is_displayed() {
		System.out.println("Running: deutsche_bank_home_page_is_displayed method...");
		then(driver.findElement(By.xpath("//body[1]/header[1]/div[1]/a[1]/img[1]")).isDisplayed());
	}
	
	
	
	
	
	
//  Scenario 2: Verify if search roles results contains the keyword
	@Test(priority=3)
	@Given("^user is on careers page$")
	public void user_is_on_careers_page()
	{
		System.out.println("Running: user_is_on_careers_page method...");
		then(driver.findElement(By.xpath("//body[1]/nav[1]/div[1]/ul[1]/li[6]/a[1]")).isDisplayed());
	}
	
	@Test(priority=4)
	@When("^user hovers over professionals nav and clicks on search roles$")
	public void user_hovers_over_professionals_nav_and_clicks_on_search_roles()
	{
		System.out.println("Running: user_hovers_over_professionals_nav_and_clicks_on_search_roles method...");
		WebElement professionals_Nav = driver.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/a[1]"));
		Actions action_professionals = new Actions(driver);
		action_professionals.moveToElement(professionals_Nav).perform();
		System.out.println("Mouse hovering over Professionals Nav...");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		
		WebElement your_application = driver.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/div[1]/section[1]/ul[1]/li[2]/a[1]"));
		Actions action_your_application = new Actions(driver);
		action_your_application.moveToElement(your_application).perform();
		System.out.println("Mouse hovering over Your Application Nav...");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		
		WebElement search_roles = driver.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/div[1]/section[1]/ul[1]/li[1]/a[1]"));
		Actions action_search_roles = new Actions(driver);
		action_search_roles.moveToElement(search_roles).perform();
		System.out.println("Mouse hovering over Search Roles Nav...");

		driver.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/div[1]/section[1]/ul[1]/li[1]/a[1]")).click();
	}
	
	@Test(priority=5)
	@And("^user enters role title and job keyword and clicks search button$")
	public void user_enters_role_title_and_job_keyword_and_clicks_search_button()
	{
		System.out.println("Running: user_enters_role_title_and_job_keyword_and_clicks_search_button method...");

		//WebElement role_keyword = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
		WebElement jobID_keyword = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
		JavascriptExecutor jsSize = (JavascriptExecutor) driver;
		jsSize.executeScript("arguments[0].scrollIntoView();", jobID_keyword);
		System.out.println("JobID/Keyword textbox is scrolled into view...");

		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		
		//role_keyword.sendKeys("QA Test Engineer");
		
		new Actions(driver).sendKeys(jobID_keyword, "QA Test Engineer").perform();
		System.out.println("Search keyword is entered into the textbox...");

		
		//role_keyword.sendKeys(Keys.ARROW_DOWN);
		//role_keyword.sendKeys(Keys.ENTER);
		//role_keyword.sendKeys(Keys.ESCAPE);
		//driver.findElement(By.xpath("//button[@id='profession']")).click();		
		//driver.findElement(By.xpath("//h3[contains(text(),'Keyword search:')]")).click();
		
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")).click();
		System.out.println("Search button is clicked...");

	}

	@Test(priority=6)
	@Then("^search results are displayed containing the keyword$")
	public void search_results_are_displayed_containing_the_keyword()
	{
		System.out.println("Running: search_results_are_displayed_containing_the_keyword method...");
		//Assert.assertTrue(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/h2[1]")).getText().contains("QA"),"Search results have the keyword in them...");
		WebElement first_result = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/h2[1]"));
		//SoftAssertions check = new SoftAssertions();
		//assertThat(first_result.getText().contains("QA"));
		then(first_result.getText()).contains("QA");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


//  Scenario 3: Verify that users can refine search results using advanced filters
	@Test(priority=7)
	@Given("user is on search roles form")
	public void user_is_on_search_roles_form() {
		System.out.println("Running: user_is_on_search_roles_form method...");
		then(driver.getCurrentUrl().contains("search"));
	}

	@Test(priority=8)
	@When("location is set to {string} and job type to {string}")
	public void location_is_set_to_and_job_type_to(String string, String string2)
	{
		System.out.println("Running: location_is_set_to_and_job_type_to method...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]")).click();
									 //body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]
		//driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]")).sendKeys(string);
		new Actions(driver).sendKeys(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]")), string).perform();
		System.out.println("City entered into the textbox...");
		new Actions(driver).sendKeys(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]")), Keys.ENTER).perform();
		
									 
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")).click();
		//driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")).sendKeys(string2);
		new Actions(driver).sendKeys(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")), string2).perform();
		System.out.println("Full time entered into the textbox...");
		//driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")).sendKeys(Keys.ENTER);
		new Actions(driver).sendKeys(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")), Keys.ENTER).perform();

	}

	@Test(priority=9)
	@When("search button is clicked")
	public void search_button_is_clicked() {
		System.out.println("Running: search_button_is_clicked method...");
		WebElement search_button = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
																//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]
		JavascriptExecutor jsSearch = (JavascriptExecutor) driver;
		jsSearch.executeScript("arguments[0].scrollIntoView();", search_button);
		System.out.println("Search button is scrolled into view...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]")).click();
	}								//body/div[@id='content']/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]

	@Test(priority=10)
	@Then("job listings should be filtered based on the selected criteria")
	public void job_listings_should_be_filtered_based_on_the_selected_criteria() {
		System.out.println("Running: job_listings_should_be_filtered_based_on_the_selected_criteria method...");
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/div[1]")).getText().contains("New York"));
	    
	}

	
	
	
//	Scenario 4: Verify how the system handles an empty search bar	
	@Test(priority=11)
	@Given("user is on search roles page")
	public void user_is_on_search_roles_page() {
		System.out.println("Running: user_is_on_search_roles_page method...");
	    driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")).click();
	    
	}

	@Test(priority=12)
	@When("search is left empty")
	public void search_is_left_empty() {
		System.out.println("Running: search_is_left_empty method...");
		WebElement role_keyword = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
		WebElement jobID_keyword = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
		role_keyword.clear();
		jobID_keyword.clear();
	}

	@Test(priority=13)
	@When("search button is clicked now")
	public void search_button_is_clicked_now() {
		System.out.println("Running: search_button_is_clicked_now method...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")).click();
		System.out.println("Search button is clicked...");
	}

	@Test(priority=14)
	@Then("job listings page should open with jobs listings without any filter")
	public void job_listings_page_should_open_with_jobs_listings_without_any_filter() {
		System.out.println("Running: job_listings_page_should_open_with_jobs_listings_without_any_filter method...");
	    WebElement search_results_label = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"));
	    String[] words = search_results_label.getText().split(" ");
	    System.out.println("Total results: "+words[0]);
	    
	    int count = Integer.parseInt(words[0]);
	    System.out.println("Integer value: "+count);
	    
	    if(count>2000)
	    {
	    	then(true);
	    }
	    else
	    {
	    	then(false);
	    }
	}

	
	
	
	
	
//	Scenario 5: Verify the functionality of pagination when search results span multiple pages	
	@Test(priority=15)
	@Given("search results are listed in more than one page")
	public void search_results_are_listed_in_more_than_one_page()
	{
		System.out.println("Running: search_results_are_listed_in_more_than_one_page method...");
	    WebElement results_pagination = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]"));
	    then(results_pagination.getText().contains("1 - 10"));
		
	}

	@Test(priority=16)
	@When("user scrolls to the bottom of the search results page")
	public void user_scrolls_to_the_bottom_of_the_search_results_page() {
		System.out.println("Running: user_scrolls_to_the_bottom_of_the_search_results_page method...");
		WebElement load_more_button = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[12]/button[1]"));
		JavascriptExecutor jsLoadMore = (JavascriptExecutor) driver;
		jsLoadMore.executeScript("arguments[0].scrollIntoView();", load_more_button);
		System.out.println("Load More button is scrolled into view...");
	    then(load_more_button.isDisplayed());
	}

	@Test(priority=17)
	@When("user clicks on the next button")
	public void user_clicks_on_the_next_button() {
		System.out.println("Running: user_clicks_on_the_next_button method...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[12]/button[1]")).click();
	}

	@Test(priority=18)
	@Then("next page of search results should be displayed")
	public void next_page_of_search_results_should_be_displayed() {
		System.out.println("Running: next_page_of_search_results_should_be_displayed method...");
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]")).getText().contains("20"));
	}

	
	
	
	
//	Scenario 6: Verify the ability to clear a search and return to the default view	
	@Test(priority=19)
	@Given("a search has been performed")
	public void a_search_has_been_performed() {
		System.out.println("Running: a_search_has_been_performed method...");
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).isDisplayed());
	}

	@Test(priority=20)
	@When("user clicks on the reset button")
	public void user_clicks_on_the_reset_button() {
		System.out.println("Running: user_clicks_on_the_reset_button method...");
	    driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")).click();
	}

	@Test(priority=21)
	@Then("the search results should be cleared and return to the default view")
	public void the_search_results_should_be_cleared_and_return_to_the_default_view() {
		System.out.println("Running: the_search_results_should_be_cleared_and_return_to_the_default_view method...");
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/h3[1]")).isDisplayed());
	}

	
	
	
	
	
//	Scenario 7: Verify that the displayed count of search results matches the actual number of results	
	@Test(priority=22)
	@Given("user entered keyword {string} in the search bar")
	public void user_entered_keyword_in_the_search_bar(String string) {
		System.out.println("Running: user_entered_keyword_in_the_search_bar method...");
	    driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]")).sendKeys(string);
	}

	@Test(priority=23)
	@When("user clicks on search button")
	public void user_clicks_on_search_button()
	{
		System.out.println("Running: user_clicks_on_search_button method...");
	    driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")).click();
	}

	@Test(priority=24)
	@Then("the count of displayed search results should match the actual number of results")
	public void the_count_of_displayed_search_results_should_match_the_actual_number_of_results()
	{
		System.out.println("Running: the_count_of_displayed_search_results_should_match_the_actual_number_of_results method...");
	    String[] countable_results = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).getText().split(" ");
	    int countable_results_int = Integer.parseInt(countable_results[0]);
	    System.out.println("Search results: "+countable_results_int);
	    
	    List<WebElement> results_list = driver.findElements(By.className("detail-entry"));
	    int items_count = results_list.size();
	    System.out.println("Results items: "+items_count);
	    
	}

	
	
	
	
//	Scenario 8: Verify that clicking on a search result displays the job details page with accurate information	
	@Test(priority=25)
	@Given("a search is performed")
	public void a_search_is_performed() 
	{
		System.out.println("Running: a_search_is_performed method...");
	    
	}

	@Test(priority=26)
	@When("user clicks on a search result")
	public void user_clicks_on_a_search_result() {
		System.out.println("Running: user_clicks_on_a_search_result method...");
	    driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/h2[1]")).click();
	}

	@Test(priority=27)
	@Then("the job details page should display accurate information about the selected job listing")
	public void the_job_details_page_should_display_accurate_information_about_the_selected_job_listing() {
		System.out.println("Running: the_job_details_page_should_display_accurate_information_about_the_selected_job_listing method...");
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[2]/li[2]/p[1]")).getText().contains("Cypress"));
	    System.out.println("Cypress is present in the job description...");
	}

	
	
	
	
	
//	Scenario 9: Verify that users can search using advanced keywords and receive relevant results	
	@Test(priority=28)
	@Given("user navigates to search roles page")
	public void user_navigates_to_search_roles_page() {
		System.out.println("Running: user_navigates_to_search_roles_page method...");
	    driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")).click();

	}
	
	@Test(priority=29)
	@When("user enters {string} in the job ID keyword field")
	public void user_enters_in_the_job_ID_keyword_field(String string) {
		System.out.println("Running: user_enters_in_the_job_ID_keyword_field method...");
		
		WebElement jobID_keyword_2 = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
		JavascriptExecutor jskeyword = (JavascriptExecutor) driver;
		jskeyword.executeScript("arguments[0].scrollIntoView();", jobID_keyword_2);
		System.out.println("JobID Keyword textbox is scrolled into view again...");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		new Actions(driver).sendKeys(jobID_keyword_2, string).perform();
		System.out.println("Cucumber Search keyword is entered into the textbox...");
		
	}


	@Test(priority=30)
	@When("user enters {string} in the role title field")
	public void user_enters_in_the_role_title_field(String string) {
		System.out.println("Running: user_enters_in_the_role_title_field method...");
		
		WebElement role_title_2 = driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
		JavascriptExecutor jsRole = (JavascriptExecutor) driver;
		jsRole.executeScript("arguments[0].scrollIntoView();", role_title_2);
		System.out.println("Role title textbox is scrolled into view again...");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		new Actions(driver).sendKeys(role_title_2, string).perform();
		System.out.println("SDET Search keyword is entered into the textbox...");
		
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")).click();
		System.out.println("Search button is clicked again...");
	}

	
	@Test(priority=31)
	@Then("relevant job listings that match both keywords should be displayed")
	public void relevant_job_listings_that_match_both_keywords_should_be_displayed() {
		System.out.println("Running: relevant_job_listings_that_match_both_keywords_should_be_displayed method...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/h2[1]")).click();
		
		//JavascriptExecutor jsWord = (JavascriptExecutor) driver;
		//jsWord.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[2]/li[1]/span[1]")));
		//System.out.println("Keyword is scrolled into view...");
		
		//then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[2]/li[1]/span[1]")).getText().contains("cucumber"));
		
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/h1[1]")).getText().contains("SDET"));
	    then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]")).getText().contains("cucumber"));
	    System.out.println("Both SDET and Cucumber are present in the job description...");

	}

	
	
	
	@Given("user is in the search roles page")
	public void user_is_in_the_search_roles_page()
	{
		System.out.println("Running: user_is_in_the_search_roles_page method...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")).click();
	}
	
	@When("user clicks on Profession checkbox")
	public void user_clicks_on_Profession_checkbox()
	{
		System.out.println("Running: user_clicks_on_Profession_checkbox method...");
		driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/label[2]/button[1]/div[1]"));
	}
	
	@Then("profession dropdown appears in the search form")
	public void profession_dropdown_appears_in_the_search_form()
	{
		System.out.println("Running: profession_dropdown_appears_in_the_search_form method...");
		then(driver.findElement(By.xpath("//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]")).isDisplayed());
	}
	
	
	
	
	@After
	public void tidyUp() 
	{
		driver.quit();
	}
}
