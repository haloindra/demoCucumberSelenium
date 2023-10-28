package ObjectRepository;

import org.openqa.selenium.By;

public class CartPage {
    public static By cartLink = By.xpath("//a[@class='shopping_cart_link']");
    public static By continueShoppingButton = By.xpath("//button[@id='continue-shopping']");
    public static By itemDescription = By.cssSelector(".inventory_details_desc.large_size");
    public static By addToCartButtonBackpack = By.name("add-to-cart-sauce-labs-backpack");
    public static By addToCartButtonBikeLight = By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']");
    public static By removeButtonBackpack = By.xpath("//button[@id='remove-sauce-labs-backpack']");
    public static By removeButtonBikeLight = By.xpath("//button[@id='remove-sauce-labs-bike-light']");
    public static By proceedToCheckoutButton = By.xpath("//button[@id='checkout']");
    public static By cartItem1 = By.xpath("(//div[@class='cart_item'])[1]");
    public static By cartItem2 = By.xpath("(//div[@class='cart_item'])[2]");
    public static By cartList = By.xpath("//div[@class='cart_list']");
}
