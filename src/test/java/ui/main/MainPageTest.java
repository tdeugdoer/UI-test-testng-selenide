package ui.main;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import listeners.UIListener;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.main.MainPage;
import utils.FailMessages;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.Assert.assertTrue;

@Listeners(UIListener.class)
public class MainPageTest {
    private final MainPage mainPage = new MainPage();

    @BeforeMethod
    public void setUp() {
        open("/");
    }

    @Test(description = "Переключение пиццы в слайдере нажатием на клавиатуре стрелки влево и вправо")
    public void slidePizzaSliderByKeys() {
        List<SelenideElement> initialVisiblePizzaList = mainPage.getVisiblePizzaInSlider();

        mainPage.slidePizzaSlider(4, Keys.LEFT);
        List<SelenideElement> afterLeftSlideVisiblePizzaList = mainPage.getVisiblePizzaInSlider();

        mainPage.slidePizzaSlider(3, Keys.RIGHT);
        List<SelenideElement> afterRightSlideVisiblePizzaList = mainPage.getVisiblePizzaInSlider();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(afterLeftSlideVisiblePizzaList, initialVisiblePizzaList, FailMessages.SLIDE_NOT_CHANGE_ELEMENTS);
        softAssert.assertNotEquals(afterRightSlideVisiblePizzaList, initialVisiblePizzaList, FailMessages.SLIDE_NOT_CHANGE_ELEMENTS);
        softAssert.assertAll();
    }

    @Test(description = "Переключение пиццы в слайдере нажатием стрелки влево и вправо")
    public void slidePizzaSliderByButtons() {
        List<SelenideElement> initialVisiblePizzaList = mainPage.getVisiblePizzaInSlider();

        mainPage.slideLeftPizzaSlider(4);
        List<SelenideElement> afterLeftSlideVisiblePizzaList = mainPage.getVisiblePizzaInSlider();

        mainPage.slideRightPizzaSlider(3);
        List<SelenideElement> afterRightSlideVisiblePizzaList = mainPage.getVisiblePizzaInSlider();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(afterLeftSlideVisiblePizzaList, initialVisiblePizzaList, FailMessages.SLIDE_NOT_CHANGE_ELEMENTS);
        softAssert.assertNotEquals(afterRightSlideVisiblePizzaList, initialVisiblePizzaList, FailMessages.SLIDE_NOT_CHANGE_ELEMENTS);
        softAssert.assertAll();
    }

    @Test(description = "Проверка отображения ссылки 'В корзину' при наведении на картинку напитка")
    public void addToCartLinkAppearsOnHover() {
        Long beforeCountVisibleDrinkToCartButtons = mainPage.getCountDrinkWithVisibleCartButtons();
        mainPage.moveToFirstDrinkImage();
        Long afterCountVisibleDrinkToCartButtons = mainPage.getCountDrinkWithVisibleCartButtons();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(beforeCountVisibleDrinkToCartButtons.longValue(), 0, FailMessages.ELEMENT_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertEquals(afterCountVisibleDrinkToCartButtons.longValue(), 1, FailMessages.ELEMENT_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertAll();
    }

    @Test(description = "Проверка перехода на страницу десерта при клике по его картинке")
    public void clickOnDessertImageRedirectsToDessertPage() {
        String firstDesertName = mainPage.getFirstDesertTitle();
        mainPage.clickFirstDesertPageLink();
        String currentPageTitle = Selenide.title();

        assertTrue(Objects.requireNonNull(currentPageTitle, FailMessages.STRING_SHOULD_NOT_BE_NULL).contains(firstDesertName), FailMessages.STRING_NOT_HAS_EXPECTED);
    }

    @Test(description = "Проверка отображения стрелки 'Наверх' при скроллинге вниз страницы")
    public void scrollToTopArrowAppearsWhenScrolledDown() {
        Boolean isScrollArrowVisible = mainPage.isTopScrollArrowVisible();
        assertTrue(isScrollArrowVisible, FailMessages.ELEMENT_NOT_VISIBLE);
    }

    @Test(description = "Проверка открытия ссылок на соцсети в новой вкладке")
    public void socialMediaLinksOpenInNewTab() {
        Integer beforeWindowCount = getWindowCount();

        mainPage.clickInstagramButton();

        switchTo().window(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(beforeWindowCount.intValue(), 1, FailMessages.WINDOW_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertEquals(getWindowCount().intValue(), 2, FailMessages.WINDOW_COUNT_NOT_MATCH_EXPECTED);
        softAssert.assertTrue(Objects.requireNonNull(WebDriverRunner.url(), FailMessages.STRING_SHOULD_NOT_BE_NULL)
                .contains("www.instagram.com"), FailMessages.NEW_WINDOW_NOT_MATCH_EXPECTED);
        softAssert.assertAll();
    }

    private Integer getWindowCount() {
        return WebDriverRunner.getWebDriver().getWindowHandles().size();
    }

}
