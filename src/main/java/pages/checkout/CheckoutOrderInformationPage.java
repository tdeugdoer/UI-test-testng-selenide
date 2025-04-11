package pages.checkout;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckoutOrderInformationPage extends CheckoutBasePage {
    public final SelenideElement paymentMethod = $x("//li[@class='woocommerce-order-overview__payment-method method']/strong");

    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

}
