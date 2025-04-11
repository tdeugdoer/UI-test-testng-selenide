package pages.checkout;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutBasePage extends BasePage {
    public final SelenideElement postTitle = $x("//h2[@class='post-title']");

    public String getPostTitle() {
        return postTitle.getText();
    }

    public String tryGetExpectedPostTitle(String expectedPostTitle) {
        postTitle.shouldHave(text(expectedPostTitle));
        return getPostTitle();
    }

}
