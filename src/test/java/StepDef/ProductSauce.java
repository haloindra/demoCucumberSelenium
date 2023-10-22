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
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        String desc = driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText();
        Assert.assertEquals(desc, "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                "melds uncompromising style with unequaled laptop and tablet protection.");
    }

    @And("clicks the Add to Cart button")
    public void clicksTheAddToCartButton() {
        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("the product should be added to the cart")
    public void theProductShouldBeAddedToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartElement = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
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
            Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='" + productName + "']")).isDisplayed());
        }
    }

    @And("clicks the all product Add to Cart button")
    public void clicksTheAllProductAddToCartButton() {
        String[] productIds = {"sauce-labs-backpack", "sauce-labs-bike-light", "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket", "sauce-labs-onesie", "test.allthethings()-t-shirt-(red)"};

        for (String productId : productIds) {
            driver.findElement(By.id("add-to-cart-" + productId)).click();
        }
    }

    @Then("all product should be added to the cart")
    public void allProductShouldBeAddedToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartElement = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
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
            driver.findElement(By.id("add-to-cart-" + productId)).click();
        }
    }

    @And("I click button remove to all product")
    public void iClickButtonRemoveToAllProduct() {
        String[] productIds = {"sauce-labs-backpack", "sauce-labs-bike-light", "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket", "sauce-labs-onesie", "test.allthethings()-t-shirt-(red)"};

        for (String productId : productIds) {
            driver.findElement(By.id("remove-" + productId)).click();
        }
    }

    @Then("count cart is empty")
    public void countCartIsEmpty() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        driver.quit();
    }

    @And("I click hamburger menu")
    public void iClickHamburgerMenu() {
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
    }

    @And("I click reset app state")
    public void iClickResetAppState() {
        WebElement button = driver.findElement(By.xpath("//a[@id='reset_sidebar_link']"));
        button.click();



        // Wait for the element to become clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement crossButton =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='react-burger-cross-btn']")));

        // Click the cross button after it becomes clickable
        crossButton.click();
    }
}
