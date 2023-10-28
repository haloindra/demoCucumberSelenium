package StepDef;

import ObjectRepository.ProductPage;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductSauce {
    WebDriver driver;

    @Given("I am on the Sauce Demo homepage")
    public void iAmOnTheSauceDemoHomepage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        driver = new ChromeDriver(opt);
        driver.get("https://www.saucedemo.com");

        // Log in
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).sendKeys(Keys.ENTER);
    }

    @When("the user finds the product Sauce Labs Backpack")
    public void theUserFindsTheProductSauceLabsBackpack() {
        driver.findElement(ProductPage.productName("Sauce Labs Backpack")).click();
        String desc = driver.findElement(ProductPage.productDescription).getText();
        Assert.assertEquals(desc, "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                "melds uncompromising style with unequaled laptop and tablet protection.");
    }

    @And("clicks the Add to Cart button")
    public void clicksTheAddToCartButton() {
        driver.findElement(ProductPage.addToCartButton("sauce-labs-backpack")).click();
    }

    @Then("the product should be added to the cart")
    public void theProductShouldBeAddedToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartElement = driver.findElement(ProductPage.cartLink);
        wait.until(ExpectedConditions.textToBePresentInElement(cartElement, "1"));

        int updatedCartCount = Integer.parseInt(cartElement.getText());
        Assert.assertEquals(updatedCartCount, 1);

        driver.quit();
    }

    @When("the user finds all the product")
    public void theUserFindsAllTheProduct() {
        String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};

        for (String productName : productNames) {
            Assert.assertTrue(driver.findElement(ProductPage.productName(productName)).isDisplayed());
        }
    }

    @And("clicks the all product Add to Cart button")
    public void clicksTheAllProductAddToCartButton() {
        String[] productIds = {"sauce-labs-backpack", "sauce-labs-bike-light", "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket", "sauce-labs-onesie", "test.allthethings()-t-shirt-(red)"};

        for (String productId : productIds) {
            driver.findElement(ProductPage.addToCartButton(productId)).click();
        }
    }

    @Then("all product should be added to the cart")
    public void allProductShouldBeAddedToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartElement = driver.findElement(ProductPage.cartLink);
        wait.until(ExpectedConditions.textToBePresentInElement(cartElement, "6"));

        int updatedCartCount = Integer.parseInt(cartElement.getText());
        Assert.assertEquals(updatedCartCount, 6);

        driver.quit();
    }

    @When("I have all product on cart")
    public void iHaveAllProductOnCart() {
        String[] productIds = {"sauce-labs-backpack", "sauce-labs-bike-light", "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket", "sauce-labs-onesie", "test.allthethings()-t-shirt-(red)"};

        for (String productId : productIds) {
            driver.findElement(ProductPage.addToCartButton(productId)).click();
        }
    }

    @And("I click button remove to all product")
    public void iClickButtonRemoveToAllProduct() {
        String[] productIds = {"sauce-labs-backpack", "sauce-labs-bike-light", "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-j"};

        for (String productId : productIds) {
            driver.findElement(ProductPage.removeButton(productId)).click();
        }
    }
}
