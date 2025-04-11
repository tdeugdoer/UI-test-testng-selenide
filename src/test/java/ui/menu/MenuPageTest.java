package ui.menu;

import listeners.UIListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cart.CartPage;
import pages.menu.MenuPage;
import utils.FailMessages;
import utils.TestConstants;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(UIListener.class)
public class MenuPageTest {
    private final MenuPage menuPage = new MenuPage();
    private final CartPage cartPage = new CartPage();

    @BeforeMethod
    public void setUp() {
        open(TestConstants.Urls.MENU_URL);
    }

    @Test(description = "Применение сортировки пицц")
    public void sorting() {
        menuPage.sortMenuByAscPrice();

        List<Float> menuCardsPrices = menuPage.getMenuCardsPrices();
        List<Float> sortedCardsPrices = menuCardsPrices.stream()
                .sorted()
                .toList();

        assertEquals(menuCardsPrices, sortedCardsPrices, FailMessages.MENU_NOT_SORTED_BY_PRICE_ASC);
    }

    @Test(description = "Фильтрация пицц по цене")
    public void priceFiltering() {
        Integer expectedMinPrice = 300;
        Integer expectedMaxPrice = 480;

        List<Float> menuCardsPrices = menuPage
                .changeMinPrice(expectedMinPrice)
                .changeMaxPrice(expectedMaxPrice)
                .clickPriceFilteringButton()
                .getMenuCardsPrices();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(menuCardsPrices.stream()
                .allMatch(price -> price >= expectedMinPrice), FailMessages.MENU_NOT_FILTERED_BY_MIN_PRICE);
        softAssert.assertTrue(menuCardsPrices.stream()
                .allMatch(price -> price <= expectedMaxPrice), FailMessages.MENU_NOT_FILTERED_BY_MAX_PRICE);
        softAssert.assertAll();
    }

    @Test(description = "Добавление пиццы в корзину")
    public void additionToCart() {
        menuPage.addToCartFirstProduct()
                .clickLinkToCart();
        Boolean cartExist = cartPage.loadingProductTable();

        assertTrue(cartExist, FailMessages.ELEMENT_NOT_EXIST);
    }

}
