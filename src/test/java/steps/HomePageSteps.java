package steps;

import context.ScenarioContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pageObjects.HomePage;

public class HomePageSteps {

    /* ====== Page objects declaration ====== */
    HomePage homePage;

    ScenarioContext context;

    public HomePageSteps(ScenarioContext context, HomePage homePage) {
        this.context = context;
        this.homePage = homePage;
    }

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

}
