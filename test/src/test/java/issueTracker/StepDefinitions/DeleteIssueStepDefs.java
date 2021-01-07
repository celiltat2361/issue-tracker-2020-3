package issueTracker.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import issueTracker.Utilities.Driver;
import issueTracker.Utilities.Pages;
import issueTracker.Utilities.ReusableMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteIssueStepDefs {
    int row;
    Pages pages = new Pages();
    ReusableMethods reusableMethods =new ReusableMethods();

    @Given("user clicks on issue list button")
    public void user_clicks_on_issue_list_button() {
        pages.homePage().issueListButton.click();
    }

    @Given("user selects {string} to delete")
    public void user_selects_to_delete(String title) {
        reusableMethods.wait(2);
        for (int i = 0; i < pages.issueListPage().rows.size(); i++) {
            if (pages.issueListPage().rows.get(i).getText().equals(title)) {
                JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
                row = i+1;
                WebElement actualTitle = Driver.getDriver().findElement(By.xpath("//tbody//tr[" + row + "]"));
                js.executeScript("arguments[0].scrollIntoView(true);", actualTitle);
               reusableMethods.wait(2);
                js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", actualTitle);
            }
        }
    }

    @Given("user clicks on trash icon")
    public void user_clicks_on_trash_icon() {
        reusableMethods.wait(2);
        String xpath = "(//tbody//tr["+ row + "]//td[3]//*[name()='svg']/*[name()='path'])[2]";
        WebElement trash = Driver.getDriver().findElement(By.xpath(xpath));
        Actions actions = new Actions(Driver.getDriver());
        actions.click(trash).build().perform();

    }
    @Given("user clicks on ok button on the popup")
    public void user_clicks_on_ok_button_on_the_popup() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 3);
        wait.until(ExpectedConditions.alertIsPresent());
        Driver.getDriver().switchTo().alert().accept();
        reusableMethods.wait(2);

    }
    @Then("user verify the success message")
    public void user_verify_the_success_message() {
        reusableMethods.wait(2);
        Assert.assertTrue(pages.issueListPage().message.isDisplayed());
    }
}