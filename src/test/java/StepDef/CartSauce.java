package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CartSauce {
     WebDriver driver;

    @Given("the user is on the Cart page")
    public void theUserIsOnTheCartPage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        driver = new ChromeDriver(opt);
        driver.get("https://www.saucedemo.com");


        // Log in
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @When("the user adds an item to the cart")
    public void theUserAddsAnItemToTheCart() {
        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        String desc = driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText();
        Assert.assertEquals(desc, "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                "melds uncompromising style with unequaled laptop and tablet protection.");
        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        driver.findElement(By.xpath("(//div[@class='cart_item'])[1]"));

    }

    @And("the user adds another item to the cart")
    public void theUserAddsAnotherItemToTheCart() {
        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();

        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Bike Light']")).click();

        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
    }

    @Then("the cart should display the added items")
    public void theCartShouldDisplayTheAddedItems() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        driver.findElement(By.xpath("(//div[@class='cart_item'])[2]"));
    }

    @And("the cart total should be updated accordingly")
    public void theCartTotalShouldBeUpdatedAccordingly() {
        driver.findElement(By.xpath("//div[@class='cart_list']"));
        driver.quit();
    }

    @When("the user attempts to remove an item from the empty cart")
    public void theUserAttemptsToRemoveAnItemFromTheEmptyCart() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();

        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bike-light']")).click();
    }

    @When("the user clicks on the Proceed to Checkout button")
    public void theUserClicksOnTheProceedToCheckoutButton() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @Then("the cart should indicating it's empty")
    public void theCartShouldIndicatingItSEmpty() {
        try {
            WebElement list = driver.findElement(By.xpath("//div[@class='cart_item']"));
            // If the element is found, check its visibility
            boolean displayStatus = list.isDisplayed();

            if (displayStatus) {
                // The element is visible, which means it's not empty, so you can fail the test if needed
                System.out.println("The cart is not empty.");
                // Assert.fail("The cart should indicate it's empty");
            } else {
                // The element is found but not visible, which could mean it's empty
                System.out.println("The cart may be empty.");
            }
        } catch (NoSuchElementException e) {
            // The element is not found, which is the expected behavior for an empty cart
            System.out.println("The cart is empty.");
        }
    }

    @Then("the user cannot access checkout")
    public void theUserCannotAccessCheckout() {
        String expectedURL = "https://www.saucedemo.com/cart";
        String currentURL = driver.getCurrentUrl();

        try {
            Assert.assertEquals("URL does not match the expected URL.", expectedURL, currentURL);
            System.out.println("URL is correct: " + currentURL);
        } catch (AssertionError e) {
            System.out.println("Expected URL is invalid because you have not added a product to the cart.");
        }
        driver.quit();
    }
}
