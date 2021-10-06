package steps;

import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pageObjects.MyAccountPage;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class MyAccountPageSteps {

    /* ====== Page objects declaration ====== */
    MyAccountPage myAccountPage;
    ScenarioContext context;

    public MyAccountPageSteps(ScenarioContext context, MyAccountPage myAccountPage) {
        this.context = context;
        this.myAccountPage = myAccountPage;
    }

    /* ====== Steps definitions ====== */
    @And("I write a the new wrong email from {string}")
    public void iWriteATheNewWrongEmailFrom(String workSheetName) throws IOException, InvalidFormatException {
        assertTrue(myAccountPage.isInformationPage(), "The page is not Personal Information page.");

        //Reading and retrieving data from the excel TestData file
        ExcelReader reader = new ExcelReader();
        List<Map<String,String>> testData = reader.getData("src/test/resources/TestData.xlsx", workSheetName);
        String new_email = testData.get(0).get("new_email");

        myAccountPage.writeNewEmail(new_email);
    }

    @Then("I should get the error message {string}")
    public void iShouldGetTheErrorMessage(String errorMsg) {
        assertFalse(myAccountPage.acceptsEmail(errorMsg), "No error message. The email was accepted.");
    }


}
