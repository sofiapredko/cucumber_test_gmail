package stepdefs;

import com.sofia.businesslayer.HomeEmailPageBO;
import com.sofia.businesslayer.LoginBO;
import com.sofia.utilmanager.driver.DriverManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static com.sofia.utilmanager.jsonparser.JsonParser.getCheckboxAmount;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class StepDefinitions {
    private LoginBO signIn;
    private HomeEmailPageBO homePage;
    private static final int CHECKBOX_AMOUNT = getCheckboxAmount();

    public StepDefinitions() {
        signIn = new LoginBO();
        homePage = new HomeEmailPageBO();
    }

    @Given("^I go to URL \"([^\"]*)\"$")
    public void iGoToURL(String arg0) {
        DriverManager.getDriverInstance().get(arg0);
    }

    @Then("^user fill \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iFillUsernameAndPassword(String arg0, String arg1) {
        assertEquals(signIn.loginIntoAccount(arg0, arg1), arg0);
    }


    @Then("^home page loads, user check few emails and delete them$")
    public void homePageLoadsUserCheckFewEmailsAndDeleteThem() {
        assertTrue(homePage.deleteCheckedEmails(CHECKBOX_AMOUNT));
    }

    @Then("^user click on Undo button and emails are not deleted$")
    public void userClickOnUndoButtonAndEmailsAreNotDeleted() {
        assertTrue(homePage.undoEmailDeletion().matches("^[^ .]+ .+\\.$"));
    }
}
