package steps;

import context.ScenarioContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.ArticlesPage;
import pageObjects.HomePage;

import static org.testng.Assert.*;

public class SearchSteps {

    /* ====== Page objects declaration ====== */
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();

    ScenarioContext context = new ScenarioContext();

    /* ====== Steps definitions ====== */
    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.navigateToEnv();
        homePage.acceptsCookies(By.id("popin_tc_privacy"), By.id("popin_tc_privacy_button_2"));
    }

    @And("I click on the search button")
    public void iClickOnTheSearchButton() {
        homePage.openSearchForm();
    }

    @When("I type {string} in the search input")
    public void iTypeInTheSearchInput(String text) {
        homePage.fillSearchInput(text);
    }

    @And("I click on the first element in the collection list")
    public void iClickOnTheFirstElementInTheCollectionList() {
        context.set(Context.COLLECTION_SELECTED.toString(), homePage.clickOnCollection());
    }

    @Then("The results are displayed")
    public void theResultsAreDisplayed() {
        assertTrue(articlesPage.isTheRightPage(context.get(Context.COLLECTION_SELECTED.toString())), "The results page shown is not the one expected.");
    }

    @And("Some items are missing")
    public void someItemsAreMissing() {
        assertTrue(articlesPage.thereAreNoShownArticles(), "All the articles are visible. There is no missing articles in the list.");
    }

}
