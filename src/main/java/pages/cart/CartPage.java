package pages.cart;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage {
    public final SelenideElement
            productTable = $x("//table[contains(@class,'shop_table')]"),
            updateCartButton = $x("//button[@name='update_cart']"),
            totalCartAmount = $x("//tr[@class='order-total']//span[contains(@class,'amount')]"),
            proceedToPaymentButton = $x("//a[contains(@class,'checkout-button')]"),
            couponCodeInput = $x("//input[@id='coupon_code']"),
            applyCouponButton = $x("//button[@name='apply_coupon']");
    public final ElementsCollection
            removeButtons = $$x("//a[@class='remove']"),
            quantityInputs = $$x("//div[@class='quantity']//input"),
            removeCouponButtons = $$x("//a[contains(@class,'remove-coupon')]");


    public Integer getFirstProductQuantity() {
        actions().pause(Duration.ofSeconds(1)).perform();
        return Integer.valueOf(Objects.requireNonNull(quantityInputs.first().getDomProperty("value")));
    }

    public CartPage setFirstProductQuantity(Integer quantity) {
        quantityInputs.first().clear();
        quantityInputs.first().sendKeys(String.valueOf(quantity));
        return this;
    }

    public Float getTotalCartAmount() {
        actions().pause(Duration.ofSeconds(1)).perform();
        return parseFloatPriceValue(totalCartAmount.getText());
    }

    public CartPage setCouponCode(String couponCode) {
        couponCodeInput.clear();
        couponCodeInput.sendKeys(couponCode);
        couponCodeInput.sendKeys(Keys.ENTER);
        return this;
    }

    public CartPage tryRemoveCoupons() {
        try {
            removeCouponButtons.forEach(WebElement::click);
        } catch (NoSuchElementException ignored) {
        }
        return this;
    }

    public CartPage clickUpdateCartButton() {
        updateCartButton.click();
        return this;
    }

    public CartPage clickApplyCouponButton() {
        applyCouponButton.shouldBe(interactable).click();
        return this;
    }

    public void clickProceedToPayment() {
        proceedToPaymentButton.shouldBe(clickable).click();
    }

    public Boolean loadingProductTable() {
        return Selenide.Wait()
                .until(d -> {
                    refresh();
                    return productTable.isDisplayed();
                });
    }

    public CartPage clearCart() {
        removeButtons.forEach(button ->
                button.shouldBe(interactable).click()
        );
        return this;
    }

}
