package pages.checkout;

import com.codeborne.selenide.SelenideElement;
import formData.CheckoutFormData;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckoutDataFillingPage extends CheckoutBasePage {
    public final SelenideElement
            firstNameInput = $x("//input[@id='billing_first_name']"),
            lastNameInput = $x("//input[@id='billing_last_name']"),
            countrySelect = $x("//select[@id='billing_country']"),
            addressInput = $x("//input[@id='billing_address_1']"),
            cityInput = $x("//input[@id='billing_city']"),
            stateInput = $x("//input[@id='billing_state']"),
            postcodeInput = $x("//input[@id='billing_postcode']"),
            phoneInput = $x("//input[@id='billing_phone']"),
            orderDateInput = $x("//input[@id='order_date']"),
            paymentInCashOnDeliveryRadioButton = $x("//input[@id='payment_method_cod']"),
            termsCheckbox = $x("//input[@id='terms']"),
            placeOrderButton = $x("//button[@id='place_order']");

    public CheckoutDataFillingPage fillOutOrderDetails(CheckoutFormData formData) {
        enterField(firstNameInput, formData.getFirstName());
        enterField(lastNameInput, formData.getLastName());
        selectCountry(formData.getCountry());
        enterField(addressInput, formData.getAddress());
        enterField(cityInput, formData.getCity());
        enterField(stateInput, formData.getState());
        enterField(postcodeInput, formData.getPostcode());
        enterField(phoneInput, formData.getPhone());
        return this;
    }

    private void enterField(WebElement inputField, String value) {
        inputField.clear();
        inputField.sendKeys(value);
    }

    public void selectCountry(String country) {
        countrySelect.selectOption(country);
    }

    public CheckoutDataFillingPage enterOrderDate(String orderDate) {
        orderDateInput.clear();
        orderDateInput.sendKeys(orderDate);
        return this;
    }

    public CheckoutDataFillingPage selectPaymentInCashOnDeliveryRadioButton() {
        paymentInCashOnDeliveryRadioButton.click();
        return this;
    }

    public CheckoutDataFillingPage clickTermsCheckbox() {
        termsCheckbox.click();
        return this;
    }

    public CheckoutDataFillingPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return this;
    }

}
