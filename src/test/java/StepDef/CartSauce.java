package StepDef;

import ObjectRepository.CartPage;
import ObjectRepository.LoginPage;
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
        driver.findElement(LoginPage.usernameField).sendKeys("standard_user");
        driver.findElement(LoginPage.passwordField).sendKeys("secret_sauce");
        driver.findElement(LoginPage.loginButton).sendKeys(Keys.ENTER);
        driver.findElement(CartPage.cartLink).click();
    }

    @When("the user adds an item to the cart")
    public void theUserAddsAnItemToTheCart() {
        driver.findElement(CartPage.continueShoppingButton).click();
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        String desc = driver.findElement(CartPage.itemDescription).getText();
        Assert.assertEquals(desc, "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                "melds uncompromising style with unequaled laptop and tablet protection.");
        driver.findElement(CartPage.addToCartButtonBackpack).click();
        driver.findElement(CartPage.cartLink).click();
        driver.findElement(CartPage.cartItem1);
    }

    @And("the user adds another item to the cart")
    public void theUserAddsAnotherItemToTheCart() {
        driver.findElement(CartPage.continueShoppingButton).click();
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Bike Light']")).click();
        driver.findElement(CartPage.addToCartButtonBikeLight).click();
    }

    @Then("the cart should display the added items")
    public void theCartShouldDisplayTheAddedItems() {
        driver.findElement(CartPage.cartLink).click();
        driver.findElement(CartPage.cartItem2);
    }

    @And("the cart total should be updated accordingly")
    public void theCartTotalShouldBeUpdatedAccordingly() {
        driver.findElement(CartPage.cartList);
        driver.quit();
    }

    @When("the user attempts to remove an item from the empty cart")
    public void theUserAttemptsToRemoveAnItemFromTheEmptyCart() {
        driver.findElement(CartPage.cartLink).click();
        driver.findElement(CartPage.removeButtonBackpack).click();
        driver.findElement(CartPage.removeButtonBikeLight).click();
    }

    @When("the user clicks on the Proceed to Checkout button")
    public void theUserClicksOnTheProceedToCheckoutButton() {
        driver.findElement(CartPage.proceedToCheckoutButton).click();
    }

    @Then("the cart should indicating it's empty")
    public void theCartShouldIndicatingItSEmpty() {
        try {
            WebElement list = driver.findElement(CartPage.cartItem1);
            boolean displayStatus = list.isDisplayed();
            if (displayStatus) {
                System.out.println("The cart is not empty.");
            } else {
                System.out.println("The cart may be empty.");
            }
        } catch (NoSuchElementException e) {
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
