package pages.auth;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
    public final SelenideElement
            usernameField = $x("//input[@id='username']"),
            passwordField = $x("//input[@id='password']"),
            loginButton = $x("//button[@name='login']");

    public LoginPage fillOutLoginForm(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
