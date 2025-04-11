package pages.account;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AccountEditPage extends AccountBasePage {
    public final SelenideElement
            uploadFileInput = $x("//input[@id='uploadFile']"),
            saveAccountDetailsButton = $x("//button[@name='save_account_details']");

    public AccountEditPage inputFile(String filePath) {
        uploadFileInput.sendKeys(filePath);
        return this;
    }

    public void clickSaveAccountDetailsButton() {
        saveAccountDetailsButton.click();
    }

}
