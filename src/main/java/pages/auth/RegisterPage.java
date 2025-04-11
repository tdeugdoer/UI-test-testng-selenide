package pages.auth;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage extends BasePage {
    public final SelenideElement
            usernameField = $x("//input[@id='reg_username']"),
            emailField = $x("//input[@id='reg_email']"),
            passwordField = $x("//input[@id='reg_password']"),
            registerButton = $x("//button[@name='register']"),
            successRegisterMessage = $x("//div[@class='content-page']/div"),
            errorMessage = $x("//ul[@class='woocommerce-error']/li");

    public RegisterPage fillOutRegisterForm(String username, String email, String password) {
        usernameField.sendKeys(username);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        return this;
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    public String getSuccessRegisterMessage() {
        return successRegisterMessage.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}
