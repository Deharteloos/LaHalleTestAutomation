package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HomePage extends Page {

    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    /**
     * FindBy
     */
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

    @FindBy(css = "#navigation > div.wrapper-header-menu > div.menu-utility-user > div.user-info.user-info-no-authenticated")
    private WebElement connectionButton;

    @FindBy(css = "#wrapper > div.omniconnect-layer.active")
    private WebElement connectionPanel;

    @FindBy(id = "dwfrm_omniconnect_email")
    private WebElement emailInput;

    @FindBy(css = "input[type=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css = "#dwfrm_login > fieldset > div.form-row.form-row-button > button")
    private WebElement loginValidationButton;

    @FindBy(css = "#navigation > div.wrapper-header-menu > div.menu-utility-user > div.user-info.user-info-authenticated")
    private WebElement accountButton;

    @FindBy(css = "#navigation > div.wrapper-header-menu > div.menu-utility-user > div.user-info.user-info-authenticated > div.user-panel")
    private WebElement userPanel;

    @FindBy(linkText = "Mes informations")
    private WebElement accountInformationLink;

    /**
     * Static selectors
     */
    private static final String deconnectionBtnCssSelector = ".user-logout.buttonstyle";

    public void navigateToEnv() {
        get(config.getEnvironment());
    }

    public void openSearchForm() {
        wait.until(elementToBeClickable(this.searchButton));
        clickOn(this.searchButton);
        if(waitUntil(visibilityOf(this.searchForm)))
            LOG.info("Search form opened.");
        else
            LOG.warn("Search form not opened.");
    }

    public void fillSearchInput(String text) {
        sendKeysSlowly(this.searchInput, text);
    }

    public String clickOnCollection() {
        String selected = null;
        if(!shortUntil(visibilityOf(this.collections)))
            LOG.warn("There was no suggestion of collections");
        else {
            wait.until(elementToBeClickable(this.firstCollectionSuggestion));
            selected = this.firstCollectionSuggestion.getText();
            clickOn(this.firstCollectionSuggestion);
        }
        return selected;
    }

    public void openConnectionPanel() {
        wait.until(elementToBeClickable(this.connectionButton));
        clickOn(this.connectionButton);
        if(waitUntil(visibilityOf(this.connectionPanel)))
            LOG.info("Connection panel opened.");
        else
            LOG.warn("Connection panel not opened.");
    }

    public void writeEmail(String email) {
        sendKeysSlowly(this.emailInput, email);
    }

    public void writePassword(String password) {
        if(!shortUntil(visibilityOf(this.passwordInput)))
            LOG.warn("Password input not shown. Either the email is incorrect or the account does not exists.");
        else {
            sendKeysSlowly(this.passwordInput, password);
        }
    }

    public void login() {
        clickOn(this.loginValidationButton);
        waitForLoadingPage();
    }

    public boolean isAuthenticated() {
        return shortUntil(presenceOfElementLocated(By.cssSelector(deconnectionBtnCssSelector)));
    }

    public void goToAccountInformation() {
        waitForLoadingPage();
        if(middleUntil(elementToBeClickable(accountButton)))
            action.moveToElement(accountButton).perform();
        else {
            LOG.warn("Account button not interactive");
        }
        if(shortUntil(visibilityOf(userPanel))) {
            LOG.info("User panel opened.");
            wait.until(elementToBeClickable(accountInformationLink));
            clickOn(accountInformationLink);
            waitForLoadingPage();
        } else {
            LOG.warn("User panel not opened.");
        }
    }

    public void deconnection() {
        if(middleUntil(elementToBeClickable(accountButton)))
            action.moveToElement(accountButton).perform();
        else {
            LOG.warn("Account button not interactive");
        }
        if(shortUntil(visibilityOf(userPanel))) {
            LOG.info("User panel opened.");
            wait.until(elementToBeClickable(By.cssSelector(deconnectionBtnCssSelector)));
            clickOn(driver.findElement(By.cssSelector(deconnectionBtnCssSelector)));
            waitForLoadingPage();
        } else {
            LOG.warn("User panel not opened.");
        }
    }


}
