package pages.account;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AccountViewOrderPage extends AccountBasePage {
    private final SelenideElement customerDetails = $x("//section[@class='woocommerce-customer-details']/address");

    public String getCustomerDetails() {
        return customerDetails.getText();
    }

}
