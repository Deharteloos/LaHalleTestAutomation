package steps;

import context.ScenarioContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.ArticlesPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ArticlesPageSteps {

    /* ====== Page objects declaration ====== */
    ArticlesPage articlesPage;

    ScenarioContext context;

    public ArticlesPageSteps(ScenarioContext context, ArticlesPage articlesPage) {
        this.context = context;
        this.articlesPage = articlesPage;
    }

    /* ====== Steps definitions ====== */
    @Then("The results are displayed")
    public void theResultsAreDisplayed() {
        assertTrue(articlesPage.isTheRightPage(context.get(Context.COLLECTION_SELECTED.toString())), "The results page shown is not the one expected.");
    }

    @And("Some items are missing")
    public void someItemsAreMissing() {
        assertFalse(articlesPage.thereAreNoShownArticles(), "All the articles are not visible. There are missing articles in the list.");
    }

}
