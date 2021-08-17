package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page {

    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    //Page elements declaration
    @FindBy(css = "#navigation > div.wrapper-header-menu > div.wrapper-header-search > div")
    private WebElement searchButton;

    @FindBy(css = "#wrapper > div.search-form-container")
    private WebElement searchForm;

    @FindBy(id = "q")
    private WebElement searchInput;

    @FindBy(css = "#search-suggestions > div > div.phrase-suggestions > div.hitgroup")
    private WebElement collections;

    @FindBy(css = "#search-suggestions > div > div.phrase-suggestions > div > ul > li:first-child")
    private WebElement firstCollectionSuggestion;

    public void navigateToEnv() {
        get(config.getEnvironment());
    }

    public void openSearchForm() {
        wait.until(ExpectedConditions.elementToBeClickable(this.searchButton));
        this.searchButton.click();
        if(waitUntil(ExpectedConditions.visibilityOf(this.searchForm)))
            LOG.info("Search form opened.");
        else
            LOG.warn("Search form not opened.");
    }

    public void fillSearchInput(String text) {
        sendKeysSlowly(this.searchInput, text);
    }

    public String clickOnCollection() {
        String selected = null;
        if(!shortUntil(ExpectedConditions.visibilityOf(this.collections)))
            LOG.warn("There was no suggestion of collections");
        else {
            wait.until(ExpectedConditions.elementToBeClickable(this.firstCollectionSuggestion));
            selected = new String(this.firstCollectionSuggestion.getText());
            this.firstCollectionSuggestion.click();
        }
        return selected;
    }


}
