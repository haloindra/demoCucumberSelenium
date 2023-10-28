package ObjectRepository;

import org.openqa.selenium.By;

public class CheckoutPage {
    public static By continueButton = By.xpath("//input[@id='continue']");
    public static By firstNameField = By.xpath("//input[@id='first-name']");
    public static By lastNameField = By.xpath("//input[@id='last-name']");
    public static By postalCodeField = By.xpath("//input[@id='postal-code']");
    public static By errorFirstNameRequired = By.xpath("//h3[normalize-space()='Error: First Name is required']");
    public static By errorPostalCodeRequired = By.xpath("//h3[normalize-space()='Error: Postal Code is required']");
    public static By finishButton = By.xpath("//button[@id='finish']");
}
