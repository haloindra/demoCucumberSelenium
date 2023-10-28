package ObjectRepository;

import org.openqa.selenium.By;

public class ProductPage {
    public static By productName(String name) {
        return By.xpath("//div[normalize-space()='" + name + "']");
    }

    public static By addToCartButton(String productId) {
        return By.name("add-to-cart-" + productId);
    }

    public static By removeButton(String productId) {
        return By.id("remove-" + productId);
    }

    public static By cartLink = By.xpath("//a[@class='shopping_cart_link']");
    public static By hamburgerMenuButton = By.xpath("//*[@id=\"react-burger-menu-btn\"]");
    public static By resetAppStateButton = By.xpath("//a[@id='reset_sidebar_link']");
    public static By crossButton = By.xpath("//button[@id='react-burger-cross-btn']");
    public static By productDescription = By.cssSelector(".inventory_details_desc.large_size");
}
