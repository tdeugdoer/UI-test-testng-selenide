package ui.auth;

import listeners.UIListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.auth.RegisterPage;
import utils.FailMessages;
import utils.StringGenerator;
import utils.TestConstants;
import utils.data.RegisterData;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

@Listeners(UIListener.class)
public class RegisterPageTest {
    private final RegisterPage registerPage = new RegisterPage();
    private String username;
    private String email;
    private String password;

    @BeforeMethod
    public void setUp() {
        username = StringGenerator.getUniqueString();
        email = StringGenerator.getUniqueString() + TestConstants.EMAIL_SUFFIX;
        password = StringGenerator.getUniqueString();

        open(TestConstants.Urls.REGISTER_URL);
    }

    @Test(description = "Пользователь успешно регистрируется при вводе валидных значений")
    public void successRegister() {
        String message = registerPage
                .fillOutRegisterForm(username, email, password)
                .clickRegisterButton()
                .getSuccessRegisterMessage();

        assertEquals(message, "Регистрация завершена", FailMessages.STRING_NOT_MATCH_EXPECTED);
    }

    @Test(description = "Пользователь пытается создать уже существующий аккаунт и получает ошибку")
    public void repeatRegister() {
        registerPage
                .fillOutRegisterForm(username, email, password)
                .clickRegisterButton()
                .clickLogoutButton();

        open(TestConstants.Urls.REGISTER_URL);
        String message = registerPage
                .fillOutRegisterForm(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        assertEquals(message, "Error: Учетная запись с такой почтой уже зарегистировавана. Пожалуйста авторизуйтесь.", FailMessages.STRING_NOT_MATCH_EXPECTED);
    }

    @Test(description = "Пользователь пытается зарегистрироваться, не вводя пароль и получает ошибку")
    public void missingPasswordRegister() {
        String password = RegisterData.EMPTY_PASSWORD;

        String message = registerPage
                .fillOutRegisterForm(username, email, password)
                .clickRegisterButton()
                .getErrorMessage();

        assertEquals(message, "Error: Введите пароль для регистрации.", FailMessages.STRING_NOT_MATCH_EXPECTED);
    }

}
