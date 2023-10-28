package ObjectRepository;

import org.openqa.selenium.By;

public class LoginPage {
    public static By usernameField = By.name("user-name");
    public static By passwordField = By.name("password");
    public static By loginButton = By.id("login-button");
    public static By errorText = By.cssSelector("h3[data-test='error']");
}
