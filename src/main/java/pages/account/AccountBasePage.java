package pages.account;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class AccountBasePage extends BasePage {
    public final SelenideElement
            editAccountLink = $x("//li[contains(@class, 'edit-account')]"),
            ordersAccountLink = $x("//li[contains(@class, 'orders')]/a");

    public void clickEditAccountLink() {
        editAccountLink.click();
    }

    public void clickOrdersAccountLink() {
        ordersAccountLink.click();
    }

}
