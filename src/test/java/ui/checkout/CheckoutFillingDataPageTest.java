package ui.checkout;

import formData.CheckoutFormData;
import listeners.UIListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.auth.LoginPage;
import pages.cart.CartPage;
import pages.checkout.CheckoutDataFillingPage;
import pages.checkout.CheckoutOrderInformationPage;
import pages.main.MainPage;
import pages.menu.MenuPage;
import utils.DateUtils;
import utils.FailMessages;
import utils.TestConstants;
import utils.data.CheckoutData;
import utils.data.LoginData;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

@Listeners(UIListener.class)
public class CheckoutFillingDataPageTest {
    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();
    private final MenuPage menuPage = new MenuPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutDataFillingPage checkoutDataFillingPage = new CheckoutDataFillingPage();
    private final CheckoutOrderInformationPage checkoutOrderInformationPage = new CheckoutOrderInformationPage();

    private final CheckoutFormData checkoutFormData = getCheckoutFormData();

    @BeforeMethod
    public void setUp() {
        open(TestConstants.Urls.MY_ACCOUNT_URL);
        loginPage.fillOutLoginForm(LoginData.EXISTING_EMAIL, LoginData.EXISTING_PASSWORD)
                .clickLoginButton();

        mainPage.clickLinkToCart();
        cartPage.clearCart()
                .clickMenuPageButton();

        menuPage.addToCartFirstProduct()
                .clickLinkToCart();

        cartPage.loadingProductTable();
        cartPage.clickProceedToPayment();
    }

    @Test(description = "Установка даты заказа")
    public void orderTime() {
        String tomorrowString = DateUtils.getTomorrowDateString();

        checkoutDataFillingPage
                .fillOutOrderDetails(checkoutFormData)
                .enterOrderDate(tomorrowString)
                .clickTermsCheckbox()
                .clickPlaceOrderButton();

        String title = checkoutOrderInformationPage
                .tryGetExpectedPostTitle("ЗАКАЗ ПОЛУЧЕН");

        assertEquals(title, "ЗАКАЗ ПОЛУЧЕН", FailMessages.STRING_NOT_MATCH_EXPECTED);
    }

    @Test(description = "Успешное оформление заказа с оплатой наличными")
    public void paymentInCash() {
        checkoutDataFillingPage
                .fillOutOrderDetails(checkoutFormData)
                .selectPaymentInCashOnDeliveryRadioButton()
                .clickTermsCheckbox()
                .clickPlaceOrderButton();

        String title = checkoutOrderInformationPage
                .tryGetExpectedPostTitle("ЗАКАЗ ПОЛУЧЕН");
        String paymentMethod = checkoutOrderInformationPage.getPaymentMethod();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(title, "ЗАКАЗ ПОЛУЧЕН", FailMessages.STRING_NOT_MATCH_EXPECTED);
        softAssert.assertEquals(paymentMethod, "Оплата при доставке", FailMessages.STRING_NOT_MATCH_EXPECTED);
        softAssert.assertAll();
    }

    private CheckoutFormData getCheckoutFormData() {
        return CheckoutFormData.builder()
                .firstName(CheckoutData.FIRST_NAME)
                .lastName(CheckoutData.LAST_NAME)
                .country(CheckoutData.COUNTRY)
                .address(CheckoutData.ADDRESS)
                .city(CheckoutData.CITY)
                .state(CheckoutData.STATE)
                .postcode(CheckoutData.POSTCODE)
                .phone(CheckoutData.PHONE)
                .build();
    }

}
