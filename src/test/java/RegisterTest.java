import base.BaseForRegistration;
import base.Constants;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.LoginPage;
import pom.MainPage;
import pom.RegisterPage;
import user.UserLogin;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends SetUpTest {
    private BaseForRegistration baseForRegistration;

    @Before
    @DisplayName("Генерация данных для нового пользователя")
    public void setUpUser() {
        baseForRegistration = new BaseForRegistration();
    }

    @After
    @DisplayName("Удаление пользователя после теста")
    public void tearDownUser() {
        baseForRegistration.deleteTestUser();
        super.tearDown();
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void testSuccessfulRegistration() {
        driver.get(Constants.REGISTER_PAGE_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        registerPage.userRegistration(
                baseForRegistration.getTestUser().getName(),
                baseForRegistration.getTestUser().getEmail(),
                baseForRegistration.getTestUser().getPassword()
        );

        driver.get(Constants.LOGIN_PAGE_URL);
        loginPage.userLogin(
                baseForRegistration.getTestUser().getEmail(),
                baseForRegistration.getTestUser().getPassword()
        );

        String accessToken = baseForRegistration.getBaseHttpClient().getAccessToken(
                new UserLogin(baseForRegistration.getTestUser().getEmail(), baseForRegistration.getTestUser().getPassword())
        );
        baseForRegistration.setAccessToken(accessToken);

        assertTrue("После успешной регистрации должен быть редирект на главную страницу",
                mainPage.orderButtonIsVisible());
    }

    @Test
    @DisplayName("Проверка ошибки при регистрации с коротким паролем")
    public void testInvalidPasswordError() {
        driver.get(Constants.REGISTER_PAGE_URL);
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.userRegistration(
                baseForRegistration.getTestUser().getName(),
                baseForRegistration.getTestUser().getEmail(),
                "12"
        );

        assertTrue("Должно отображаться сообщение об ошибке при вводе короткого пароля",
                registerPage.isWarningIncorrectPasswordDisplayed());
    }
}