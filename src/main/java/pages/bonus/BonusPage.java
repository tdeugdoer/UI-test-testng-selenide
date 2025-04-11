package pages.bonus;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.confirm;

public class BonusPage extends BasePage {
    public final SelenideElement
            usernameInput = $x("//input[@id='bonus_username']"),
            phoneInput = $x("//input[@id='bonus_phone']"),
            submitButton = $x("//button[@type='submit' and @name='bonus']"),
            resultMessage = $x("//div[@id='bonus_main']/h3");

    public BonusPage fillOutBonusForm(String username, String phone) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        phoneInput.clear();
        phoneInput.sendKeys(phone);
        return this;
    }

    public BonusPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public String getAlertMessage() {
        return confirm();
    }

    public String getResultMessage() {
        return resultMessage.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

}
