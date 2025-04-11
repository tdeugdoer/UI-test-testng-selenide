package pages.account;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class AccountOrdersPage extends AccountBasePage {
    private final ElementsCollection numberOrders = $$x("//td[@class='woocommerce-orders-table__cell woocommerce-orders-table__cell-order-number']/a");

    public void clickFirstOrder() {
        numberOrders.first().click();
    }

}
