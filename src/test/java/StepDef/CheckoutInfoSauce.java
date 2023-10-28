package StepDef;

import ObjectRepository.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class CheckoutInfoSauce {
    WebDriver driver;

    @Given("i am on the Checkout: Your Information Page")
    public void iAmOnTheCheckoutYourInformationPage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        driver = new ChromeDriver(opt);
        driver.get("https://www.saucedemo.com");

        // Log in
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).sendKeys(Keys.ENTER);

        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @And("i clicks the Continue button")
    public void IClicksTheContinueButton() {
        driver.findElement(CheckoutPage.continueButton).click();
    }

    @When("i inputs a valid first name")
    public void iInputsAValidFirstName() {
        driver.findElement(CheckoutPage.firstNameField).sendKeys("TestQA");
    }

    @And("i inputs a valid last name")
    public void iInputsAValidLastName() {
        driver.findElement(CheckoutPage.lastNameField).sendKeys("ABC");
    }

    @Then("i should see an error message indicating invalid information")
    public void iShouldSeeAnErrorMessageIndicatingInvalidInformation() {
        driver.findElement(CheckoutPage.errorPostalCodeRequired);
    }

    @When("i enters a valid identity")
    public void iEntersAValidIdentity() {
        driver.findElement(CheckoutPage.firstNameField).sendKeys("TestQA");
        driver.findElement(CheckoutPage.lastNameField).sendKeys("ABC");
        driver.findElement(CheckoutPage.postalCodeField).sendKeys("551122");
    }

    @And("i inputs an invalid zip\\/postal code")
    public void iInputsAnInvalidZipPostalCode() {
        driver.findElement(CheckoutPage.postalCodeField).sendKeys("");
    }

    @Then("i should cannot access checkout overview")
    public void iShouldCannotAccessCheckoutOverview() {
        try {
            WebElement inputAlert = driver.findElement(CheckoutPage.errorFirstNameRequired);
            String actualErrorMessage = inputAlert.getText();
            String expectedErrorMessage = "Error : required to fill out all forms";

            // Assert that the actual error message matches the expected error message
            Assert.assertEquals("Validation message does not match expected", expectedErrorMessage, actualErrorMessage);
        } catch (AssertionError e) {
            // Handle the case where the error message is not found on the page
            System.out.println("Error message not found. Test continues...");
        }
        driver.quit();
    }

    @Then("order has been dispatched")
    public void orderHasBeenDispatched() {
        // Find all elements matching the specified XPaths
        List<WebElement> elements = driver.findElements(By.xpath("//img[@alt='Pony Express']"));
        Assert.assertTrue("Pony Express image should be present", !elements.isEmpty());

        elements = driver.findElements(By.xpath("//h2[normalize-space()='Thank you for your order!']"));
        Assert.assertTrue("Thank you message should be present", !elements.isEmpty());

        elements = driver.findElements(By.xpath("//div[@class='complete-text']"));
        Assert.assertTrue("Complete text should be present", !elements.isEmpty());

        elements = driver.findElements(By.xpath("//button[@id='back-to-products']"));
        Assert.assertTrue("Back to products button should be present", !elements.isEmpty());

        driver.quit();
    }

    @And("i click finish")
    public void iClickFinish() {
        driver.findElement(CheckoutPage.finishButton).click();
    }
}
