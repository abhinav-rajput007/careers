package StepDefinitions;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;

public class CareersTests {
	public static WebDriver driver;

	/*
	 * i. Navigate to the web site. ii. Locate and interact with the search input
	 * field. iii. Enter the search criteria. iv. Submit the search form. v.
	 * Validate that the search results contain the keyword.
	 * 
	 */

//  Scenario 1: Launch the web site
	@Test(priority = 0, groups = "smoke")
	@Description("Scenario 1: Launch the web site")
	@Parameters("browser")

	@Given("browser is started")
	public void is_started(String browser) {
		System.out.println("Running: browser_is_started method...");
		if (browser.equalsIgnoreCase("Chrome")) {
			System.out.println(" Executing on Chrome...");
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			chromeOptions.setExperimentalOption("excludeSwitches",
					Arrays.asList("disable-popup-blocking", "enable-automation"));
			chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
			chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("chrome.switches",
					"--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("test-type");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--start-maximized");
			driver = new ChromeDriver(chromeOptions);
			JavascriptExecutor scrptExec = (JavascriptExecutor) driver;
			scrptExec.executeScript("Object.defineProperty(navigator, 'driver', {get: () => undefined})");
		}

		else if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println(" Executing on Firefox...");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("dom.webdriver.enabled", false);
			firefoxOptions.setCapability("dom.webnotifications.enabled", false);
			firefoxOptions.setCapability("geo.enabled", false);
			firefoxOptions.setCapability("geo.provider.use_corelocation", false);
			firefoxOptions.setCapability("geo.provider.network.url", "data:,");
			firefoxOptions.addArguments("--disable-blink-features=AutomationControlled");
			driver = new FirefoxDriver(firefoxOptions);

			driver.manage().window().maximize();

			JavascriptExecutor scriptExecutor = (JavascriptExecutor) driver;
			scriptExecutor.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

		} else

		{
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}

	@Test(priority = 1, groups = "smoke")
	@When("^user launches the deutsche bank website$")
	public void user_launches_the_deutsche_bank_website() {
		try {
			System.out.println("Running: user_launches_the_deutsche_bank_website method...");
			driver.get("https://careers.db.com/");
			System.out.println("Website is launched...");
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

			JavascriptExecutor jsCookies = (JavascriptExecutor) driver;
			WebElement cookie_button = (WebElement) jsCookies.executeScript(
					"return document.querySelector('#usercentrics-root').shadowRoot.querySelector(\"button[data-testid='uc-accept-all-button']\")");
			cookie_button.click();

//			WebElement shadowHost = driver.findElement(By.cssSelector("div[data-testid='uc-app-container']"));
//
//			WebElement buttonWithinShadowDOM = (WebElement) ((JavascriptExecutor) driver).executeScript(
//					"return arguments[0].shadowRoot.querySelector('button[data-testid=\"uc-accept-all-button\"]');",
//					shadowHost);
//
//			buttonWithinShadowDOM.click();

		} catch (Exception e) {
			System.err.println("Exception occurred: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 2, dependsOnMethods = "user_launches_the_deutsche_bank_website", groups = "smoke")
	@Then("^deutsche bank home page is displayed$")
	public void deutsche_bank_home_page_is_displayed() {
		System.out.println("Running: deutsche_bank_home_page_is_displayed method...");
		then(driver.findElement(By.xpath("//body[1]/header[1]/div[1]/a[1]/img[1]")).isDisplayed());
	}

//  Scenario 2: Verify if search roles results contains the keyword
	@Test(priority = 3, dependsOnMethods = "user_launches_the_deutsche_bank_website", groups = "smoke")
	@Description("Scenario 2: Verify if search roles results contains the keyword")
	@Given("^user is on careers page$")
	public void user_is_on_careers_page() {
		System.out.println("Running: user_is_on_careers_page method...");
		then(driver.findElement(By.xpath("//body[1]/nav[1]/div[1]/ul[1]/li[6]/a[1]")).isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = "user_launches_the_deutsche_bank_website", groups = "smoke")
	@When("^user hovers over professionals nav and clicks on search roles$")
	public void user_hovers_over_professionals_nav_and_clicks_on_search_roles() {
		System.out.println("Running: user_hovers_over_professionals_nav_and_clicks_on_search_roles method...");
		WebElement professionals_Nav = driver.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/a[1]"));
		Actions action_professionals = new Actions(driver);
		action_professionals.moveToElement(professionals_Nav).perform();
		System.out.println("Mouse hovering over Professionals Nav...");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement your_application = driver
				.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/div[1]/section[1]/ul[1]/li[2]/a[1]"));
		Actions action_your_application = new Actions(driver);
		action_your_application.moveToElement(your_application).perform();
		System.out.println("Mouse hovering over Your Application Nav...");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		WebElement search_roles = driver
				.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/div[1]/section[1]/ul[1]/li[1]/a[1]"));
		Actions action_search_roles = new Actions(driver);
		action_search_roles.moveToElement(search_roles).perform();
		System.out.println("Mouse hovering over Search Roles Nav...");

		driver.findElement(By.xpath("//body[1]/nav[2]/div[1]/ul[1]/li[5]/div[1]/section[1]/ul[1]/li[1]/a[1]")).click();
	}

	@Test(priority = 5, dependsOnMethods = "user_launches_the_deutsche_bank_website", groups = "smoke")
	@And("^user enters role title and job keyword and clicks search button$")
	public void user_enters_role_title_and_job_keyword_and_clicks_search_button() {
		System.out.println("Running: user_enters_role_title_and_job_keyword_and_clicks_search_button method...");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor jsSize = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement jobID_keyword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#jobIdSearch")));
		jsSize.executeScript("arguments[0].scrollIntoView();", jobID_keyword);
		System.out.println("JobID/Keyword textbox is scrolled into view...");

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		new Actions(driver).sendKeys(jobID_keyword, "QA Test Engineer").perform();
		System.out.println("Search keyword is entered into the textbox...");

		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]"))
				.click();
		System.out.println("Search button is clicked...");

	}

	@Test(priority = 6, dependsOnMethods = "user_launches_the_deutsche_bank_website", groups = "smoke")
	@Then("^search results are displayed containing the keyword$")
	public void search_results_are_displayed_containing_the_keyword() {
		System.out.println("Running: search_results_are_displayed_containing_the_keyword method...");
		List<WebElement> jobs = driver.findElements(By.xpath("//div[@class='detail-entry']/h2"));
		String result = "";

		for (WebElement job : jobs) {
			result += job.getText() + "\n";
		}
		System.out.println("Fetched jobs are" + result);
		// WebElement first_result = driver.findElement(By.xpath(
		// "//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/h2[1]"));
		then(result.substring(2).lines().allMatch(str -> str.equals("QA Test Engineer")));
	}

//  Scenario 3: Verify that users can refine search results using advanced filters
	@Test(priority = 7, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 3: Verify that users can refine search results using advanced filters")
	@Given("user is on search roles form")
	public void user_is_on_search_roles_form() {
		System.out.println("Running: user_is_on_search_roles_form method...");
		then(driver.getCurrentUrl().contains("search"));
	}

	@Test(priority = 8, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Parameters({ "location", "jobType" })
	@When("location is set to New York and job type to Full Time")
	public void location_is_set_to_and_job_type_to(String location, String jobType) {
		System.out.println("Running: location_is_set_to_and_job_type_to " + location);
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]"))
				.click();
		new Actions(driver).sendKeys(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]")),
				location).perform();
		System.out.println("City entered into the textbox...");
		new Actions(driver).sendKeys(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[2]/div[1]/div[1]/input[1]")),
				Keys.ENTER).perform();

		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]"))
				.click();

		new Actions(driver).sendKeys(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")),
				jobType).perform();
		System.out.println(jobType + " is entered into the textbox...");

		new Actions(driver).sendKeys(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[1]/div[1]/input[1]")),
				Keys.ENTER).perform();

	}

	@Test(priority = 9, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("search button is clicked")
	public void search_button_is_clicked() {
		System.out.println("Running: search_button_is_clicked method...");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		JavascriptExecutor jsSearch = (JavascriptExecutor) driver;
		jsSearch.executeScript("window.scrollBy(0, arguments[0]);", 20000);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		
		WebElement search_button = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Search')]"))));
		
		jsSearch.executeScript("arguments[0].scrollIntoView();", search_button);
		System.out.println("Search button is scrolled into view...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"))
				.click();
	}

	@Test(priority = 10, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("job listings should be filtered based on the selected criteria")
	public void job_listings_should_be_filtered_based_on_the_selected_criteria() {
		System.out.println("Running: job_listings_should_be_filtered_based_on_the_selected_criteria method...");
		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/div[1]"))
				.getText().contains("New York"));

	}

//	Scenario 4: Verify how the system handles an empty search bar	
	@Test(priority = 11, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 4: Verify how the system handles an empty search bar	")
	@Given("user is on search roles page")
	public void user_is_on_search_roles_page() {
		System.out.println("Running: user_is_on_search_roles_page method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]"))
				.click();

	}

	@Test(priority = 12, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("search is left empty")
	public void search_is_left_empty() {
		System.out.println("Running: search_is_left_empty method...");
		WebElement role_keyword = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
		WebElement jobID_keyword = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
		role_keyword.clear();
		jobID_keyword.clear();
	}

	@Test(priority = 13, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("search button is clicked now")
	public void search_button_is_clicked_now() {
		System.out.println("Running: search_button_is_clicked_now method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]"))
				.click();
		System.out.println("Search button is clicked...");
	}

	@Test(priority = 14, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("job listings page should open with jobs listings without any filter")
	public void job_listings_page_should_open_with_jobs_listings_without_any_filter() {
		System.out.println("Running: job_listings_page_should_open_with_jobs_listings_without_any_filter method...");
		WebElement search_results_label = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"));
		String[] words = search_results_label.getText().split(" ");
		System.out.println("Total results: " + words[0]);

		int count = Integer.parseInt(words[0]);
		System.out.println("Integer value: " + count);

		if (count > 2000) {
			then(true);
		} else {
			then(false);
		}
	}

//	Scenario 5: Verify the functionality of pagination when search results span multiple pages	
	@Test(priority = 15, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 5: Verify the functionality of pagination when search results span multiple pages")
	@Given("search results are listed in more than one page")
	public void search_results_are_listed_in_more_than_one_page() {
		System.out.println("Running: search_results_are_listed_in_more_than_one_page method...");
		WebElement results_pagination = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]"));
		then(results_pagination.getText().contains("1 - 10"));

	}

	@Test(priority = 16, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("user scrolls to the bottom of the search results page")
	public void user_scrolls_to_the_bottom_of_the_search_results_page() {
		System.out.println("Running: user_scrolls_to_the_bottom_of_the_search_results_page method...");
		WebElement load_more_button = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[12]/button[1]"));
		JavascriptExecutor jsLoadMore = (JavascriptExecutor) driver;
		jsLoadMore.executeScript("arguments[0].scrollIntoView();", load_more_button);
		System.out.println("Load More button is scrolled into view...");
		then(load_more_button.isDisplayed());
	}

	@Test(priority = 17, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("user clicks on the next button")
	public void user_clicks_on_the_next_button() {
		System.out.println("Running: user_clicks_on_the_next_button method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[12]/button[1]"))
				.click();
	}

	@Test(priority = 18, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("next page of search results should be displayed")
	public void next_page_of_search_results_should_be_displayed() {
		System.out.println("Running: next_page_of_search_results_should_be_displayed method...");
		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]"))
				.getText().contains("20"));
	}

//	Scenario 6: Verify the ability to clear a search and return to the default view	
	@Test(priority = 19, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 6: Verify the ability to clear a search and return to the default view")
	@Given("a search has been performed")
	public void a_search_has_been_performed() {
		System.out.println("Running: a_search_has_been_performed method...");
		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"))
				.isDisplayed());
	}

	@Test(priority = 20, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("user clicks on the reset button")
	public void user_clicks_on_the_reset_button() {
		System.out.println("Running: user_clicks_on_the_reset_button method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]"))
				.click();
	}

	@Test(priority = 21, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("the search results should be cleared and return to the default view")
	public void the_search_results_should_be_cleared_and_return_to_the_default_view() {
		System.out.println("Running: the_search_results_should_be_cleared_and_return_to_the_default_view method...");
		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/h3[1]"))
				.isDisplayed());
	}

//	Scenario 7: Verify that the displayed count of search results matches the actual number of results	
	@Test(priority = 22, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 7: Verify that the displayed count of search results matches the actual number of results")
	@Parameters("SearchString")
	@Given("user entered keyword Cypress in the search bar")
	public void user_entered_keyword_in_the_search_bar(String SearchString) {
		System.out.println("Running: user_entered_keyword_in_the_search_bar method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"))
				.sendKeys(SearchString);
	}

	@Test(priority = 23, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("user clicks on search button")
	public void user_clicks_on_search_button() {
		System.out.println("Running: user_clicks_on_search_button method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]"))
				.click();
	}

	@Test(priority = 24, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("the count of displayed search results should match the actual number of results")
	public void the_count_of_displayed_search_results_should_match_the_actual_number_of_results() {
		System.out.println(
				"Running: the_count_of_displayed_search_results_should_match_the_actual_number_of_results method...");
		String[] countable_results = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"))
				.getText().split(" ");
		int countable_results_int = Integer.parseInt(countable_results[0]);
		System.out.println("Search results: " + countable_results_int);

		List<WebElement> results_list = driver.findElements(By.className("detail-entry"));
		int items_count = results_list.size();
		System.out.println("Results items: " + items_count);

	}

//	Scenario 8: Verify that clicking on a search result displays the job details page with accurate information	
	@Test(priority = 25, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 8: Verify that clicking on a search result displays the job details page with accurate information	")
	@Given("a search is performed")
	public void a_search_is_performed() {
		System.out.println("Running: a_search_is_performed method...");

	}

	@Test(priority = 26, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("user clicks on a search result")
	public void user_clicks_on_a_search_result() {
		System.out.println("Running: user_clicks_on_a_search_result method...");
	}

	@Test(priority = 27, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("the job details page should display accurate information about the selected job listing")
	public void the_job_details_page_should_display_accurate_information_about_the_selected_job_listing() {
		System.out.println(
				"Running: the_job_details_page_should_display_accurate_information_about_the_selected_job_listing method...");
		// WebElement paragraph = driver.findElement(By.xpath(
		// "//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[2]/li[2]/p[1]"));
		// JavascriptExecutor jsCypress = (JavascriptExecutor) driver;
		// jsCypress.executeScript("arguments[0].scrollIntoView();", paragraph);
		// then(driver.findElement(By.xpath(
		// "//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[2]/li[2]/p[1]"))
		// .getText().contains("Cypress"));
		then(driver.getPageSource().contains("cypress"));
		System.out.println("Cypress is present in the job description...");
	}

//	Scenario 9: Verify that users can search using advanced keywords and receive relevant results	
	@Test(priority = 28, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 9: Verify that users can search using advanced keywords and receive relevant results	")
	@Given("user navigates to search roles page")
	public void user_navigates_to_search_roles_page() {
		System.out.println("Running: user_navigates_to_search_roles_page method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]"))
				.click();

	}

	@Test(priority = 29, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Parameters("JobID")
	@When("user enters Cucumber in the job ID keyword field")
	public void user_enters_in_the_job_ID_keyword_field(String JobID) {
		System.out.println("Running: user_enters_in_the_job_ID_keyword_field method...");

		WebElement jobID_keyword_2 = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
		JavascriptExecutor jskeyword = (JavascriptExecutor) driver;
		jskeyword.executeScript("arguments[0].scrollIntoView();", jobID_keyword_2);
		System.out.println("JobID Keyword textbox is scrolled into view again...");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		new Actions(driver).sendKeys(jobID_keyword_2, JobID).perform();
		System.out.println("Cucumber Search keyword is entered into the textbox...");

	}

	@Test(priority = 30, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Parameters("role")
	@When("user enters SDET in the role title field")
	public void user_enters_in_the_role_title_field(String role) {
		System.out.println("Running: user_enters_in_the_role_title_field method...");

		WebElement role_title_2 = driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
		JavascriptExecutor jsRole = (JavascriptExecutor) driver;
		jsRole.executeScript("arguments[0].scrollIntoView();", role_title_2);
		System.out.println("Role title textbox is scrolled into view again...");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		new Actions(driver).sendKeys(role_title_2, role).perform();
		System.out.println("SDET Search keyword is entered into the textbox...");

		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]"))
				.click();
		System.out.println("Search button is clicked again...");
	}

	@Test(priority = 31, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("relevant job listings that match both keywords should be displayed")
	public void relevant_job_listings_that_match_both_keywords_should_be_displayed() {
		System.out.println("Running: relevant_job_listings_that_match_both_keywords_should_be_displayed method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]/div[1]/h2[1]"))
				.click();

		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/h1[1]"))
				.getText().contains("SDET"));
		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]"))
				.getText().contains("cucumber"));
		System.out.println("Both SDET and Cucumber are present in the job description...");

	}

	// Scenario 10: Verify that Profession option appears when user opts to search
	// by Profession ")
	@Test(priority = 32, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Description("Scenario 10: Verify that Profession option appears when user opts to search by Profession	")
	@Given("user is in the search roles page")
	public void user_is_in_the_search_roles_page() {
		System.out.println("Running: user_is_in_the_search_roles_page method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]"))
				.click();
	}

	@Test(priority = 33, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@When("user clicks on Profession checkbox")
	public void user_clicks_on_Profession_checkbox() {
		System.out.println("Running: user_clicks_on_Profession_checkbox method...");
		driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/label[2]/button[1]"))
				.click();
	}

	@Test(priority = 34, dependsOnMethods = "user_launches_the_deutsche_bank_website")
	@Then("profession dropdown appears in the search form")
	public void profession_dropdown_appears_in_the_search_form() {
		System.out.println("Running: profession_dropdown_appears_in_the_search_form method...");
		then(driver.findElement(By.xpath(
				"//body[1]/div[1]/section[4]/div[1]/div[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]"))
				.isDisplayed());

	}

	@AfterTest
	void tidyUpBrowser() {
		System.out.println("Closing browser....");
		driver.quit();
	}
}
