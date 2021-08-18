package steps;

import context.ScenarioContext;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.HomePage;

public final class Hooks {

    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    private ScenarioContext context;
    private HomePage homePage;

    public Hooks(ScenarioContext context, HomePage homePage) {
        this.context = context;
        this.homePage = homePage;
    }

    @After("@connect")
    public void deconnection() {
        homePage.deconnection();
    }

}
