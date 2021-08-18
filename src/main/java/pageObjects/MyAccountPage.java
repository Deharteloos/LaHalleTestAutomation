package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends Page {

    private static final Logger LOG = LogManager.getLogger(MyAccountPage.class);

    //page elements declaration
    @FindBy(css = "#main > div.account-banner > h2")
    private WebElement pageTitle;

    @FindBy(css = "#RegistrationForm > fieldset:nth-child(2) > div > label")
    private WebElement emailLabel;

    @FindBy(id = "dwfrm_profile_customer_email")
    private WebElement emailInput;

    @FindBy(id = "dwfrm_profile_customer_email-error")
    private WebElement emailErrorMessage;

    public boolean isInformationPage() {
        return this.pageTitle.getText().equals("INFORMATIONS PERSONNELLES");
    }

    public void writeNewEmail(String newEmail) {
        scrollToElement(this.emailInput);
        this.emailInput.clear();
        sendKeysSlowly(this.emailInput, newEmail);
        action.click(emailLabel).perform();
    }

    public boolean acceptsEmail(String msg) {
        if(!waitUntil(ExpectedConditions.visibilityOf(this.emailErrorMessage)))
            return true;
        else {
            if(!this.emailErrorMessage.getText().equals(msg))
                LOG.warn("The error message is not the one expected");
            return false;
        }
    }

}
