package ui.bonus;

import listeners.UIListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.bonus.BonusPage;
import utils.FailMessages;
import utils.TestConstants;
import utils.data.BonusData;

import static com.codeborne.selenide.Selenide.open;

@Listeners(UIListener.class)
public class BonusPageTest {
    private final BonusPage bonusPage = new BonusPage();

    @BeforeMethod
    public void setUp() {
        open(TestConstants.Urls.BONUS_URL);
    }

    @Test(description = "Успешное оформление карты с проверкой текста «Заявка отправлена, дождитесь, пожалуйста, оформления карты!»")
    public void createBonusCard() {
        String alertMessage = bonusPage.fillOutBonusForm(BonusData.USERNAME, BonusData.PHONE_NUMBER)
                .clickSubmit()
                .getAlertMessage();
        String resultMessage = bonusPage.getResultMessage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertMessage, "Заявка отправлена, дождитесь, пожалуйста, оформления карты!", FailMessages.STRING_NOT_MATCH_EXPECTED);
        softAssert.assertEquals(resultMessage, "Ваша карта оформлена!", FailMessages.STRING_NOT_MATCH_EXPECTED);
        softAssert.assertAll();
    }


}
