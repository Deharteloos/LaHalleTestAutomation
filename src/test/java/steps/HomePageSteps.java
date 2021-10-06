package steps;

import context.ScenarioContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import pageObjects.HomePage;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class HomePageSteps {

    /* ====== Page objects declaration ====== */
    HomePage homePage;
    ScenarioContext context;

    /**
     * Static selectors
     */
    private static final By cookiesDialogBoxId = By.id("popin_tc_privacy");
    private static final By cookiesAcceptBtnId = By.id("popin_tc_privacy_button_2");

    public HomePageSteps(ScenarioContext context, HomePage homePage) {
        this.context = context;
        this.homePage = homePage;
    }

    /* ====== Steps definitions ====== */
    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.navigateToEnv();
        homePage.acceptsCookies(cookiesDialogBoxId, cookiesAcceptBtnId);
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
        context.set(Context.COLLECTION_SELECTED, homePage.clickOnCollection());
    }

    @When("I click on the connection button")
    public void iClickOnTheConnectionButton() {
        homePage.openConnectionPanel();
    }

    @And("I log in with my credentials from {string}")
    public void iLogInWithMyCredentialsFrom(String workSheetName) throws IOException, InvalidFormatException {
        //Reading and retrieving data from the excel TestData file
        ExcelReader reader = new ExcelReader();
        List<Map<String,String>> testData = reader.getData("src/test/resources/TestData.xlsx", workSheetName);
        String email = testData.get(0).get("email");
        String password = testData.get(0).get("password");

        homePage.writeEmail(email);
        homePage.writePassword(password);
        homePage.login();
    }

    @And("I click on my informations")
    public void iClickOnMyInformations() throws InterruptedException {
        assertTrue(homePage.isAuthenticated(), "The authentication failed.");
        homePage.goToAccountInformation();
    }
}
