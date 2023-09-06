Feature: feature to test db.com Careers Search functionality

	@SmokeTest @RegressionTest
  Scenario: Launch the website
    Given 'Chrome' is started
    When user launches the deutsche bank website
    Then deutsche bank home page is displayed

	@RegressionTest
  Scenario: Verify if search roles results contains the keyword
    Given user is on careers page
    When user hovers over professionals nav and clicks on search roles
    And user enters role title and job keyword and clicks search button
    Then search results are displayed containing the keyword

	@RegressionTest @Sanity
  Scenario: Verify that users can refine search results using advanced filters
    Given user is on search roles form
    When location is set to 'New York' and job type to 'Full time'
    And search button is clicked
    Then job listings should be filtered based on the selected criteria

	@RegressionTest @Sanity
  Scenario: Verify how the system handles an empty search bar
    Given user is on search roles page
    When search is left empty
    And search button is clicked now
    Then job listings page should open with jobs listings without any filter

	@SmokeTest @RegressionTest
  Scenario: Verify the functionality of pagination when search results span multiple pages
    Given search results are listed in more than one page
    When user scrolls to the bottom of the search results page
    And user clicks on the next button
    Then next page of search results should be displayed

	@RegressionTest @Sanity
  Scenario: Verify the ability to clear a search and return to the default view
    Given a search has been performed
    When user clicks on the reset button
    Then the search results should be cleared and return to the default view

	@RegressionTest @Sanity
  Scenario: Verify that the displayed count of search results matches the actual number of results
    Given user entered keyword 'Cypress' in the search bar
    When user clicks on search button
    Then the count of displayed search results should match the actual number of results

	@RegressionTest
  Scenario: Verify that clicking on a search result displays the job details page with accurate information
    Given a search is performed
    When user clicks on a search result
    Then the job details page should display accurate information about the selected job listing

	@SmokeTest @RegressionTest @Sanity
  Scenario: Verify that users can search using advanced keywords and receive relevant results
    Given user navigates to search roles page
    When user enters 'Cucumber' in the job ID keyword field
    And user enters 'SDET' in the role title field
    Then relevant job listings that match both keywords should be displayed
  
  @RegressionTest  
  Scenario: Verify that Profession option appears when user opts to search by Profession
  	Given user is in the search roles page
  	When user clicks on Profession checkbox
  	Then profession dropdown appears in the search form
