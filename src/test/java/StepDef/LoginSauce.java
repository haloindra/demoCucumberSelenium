package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class LoginSauce {
    WebDriver driver;

    @Given("I am on the Sauce Demo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        driver = new ChromeDriver(opt);
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter username into the field username")
    public void iEnterUsernameIntoTheFieldUsername() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
    }

    @And("I enter password into the field password")
    public void iEnterPasswordIntoTheFieldPassword() {
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }


    @And("I Click button Login")
    public void iClickButtonLogin() {
        driver.findElement(By.xpath("//input[@id='login-button']")).sendKeys(Keys.ENTER);
    }

    @Then("I successfully login")
    public void iSuccessfullyLogin() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        driver.quit();
    }


    @And("I enter invalid password into the field password")
    public void iEnterInvalidPasswordIntoTheFieldPassword() {
        driver.findElement(By.name("password")).sendKeys("sauce_secret");
    }

    @And("the user should not be logged in")
    public void theUserShouldNotBeLoggedIn() {
        driver.getCurrentUrl();
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        driver.findElement(By.cssSelector("h3[data-test='error']"));
        driver.quit();
    }

    @When("I enter invalid username into the field username")
    public void iEnterInvalidUsernameIntoTheFieldUsername() {
        driver.findElement(By.name("user-name")).sendKeys("user-standard");
    }
}
