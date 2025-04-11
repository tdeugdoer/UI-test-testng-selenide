package pages.account;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AccountInformationPage extends AccountBasePage {
    private final SelenideElement message = $x("//div[@class='woocommerce-message']");

    public String getMessage() {
        return message.getText();
    }

}
