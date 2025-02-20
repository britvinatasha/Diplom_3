import base.Base;
import base.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pom.LoginPage;
import pom.MainPage;
import pom.RegisterPage;
import pom.ForgotPasswordPage;
import user.UserData;

public class LoginTest extends SetUpTest {
    private Base base;
    private UserData user;


    @Before
    @DisplayName("Создание пользователя с помощью API")
    public void setUpUser() {
        base = new Base(driver);
        user = base.createTestUser();
    }

    @After
    @DisplayName("Удаление созданного пользователя с помощью API")
    public void tearDownUser() {
        base.deleteTestUser();
        super.tearDown();
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void testLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        driver.get(Constants.HOST_URL);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue("Ошибка входа", mainPage.orderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginFromProfileButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(Constants.HOST_URL);
        mainPage.clickProfileButton();
        loginPage.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue("Ошибка входа", mainPage.orderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegisterPage() {
        driver.get(Constants.REGISTER_PAGE_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        registerPage.clickLoginButton();
        loginPage.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue("Ошибка входа", mainPage.orderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordPage() {
        driver.get(Constants.RECOVERY_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        forgotPasswordPage.clickLoginButton();
        loginPage.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue("Ошибка входа", mainPage.orderButtonIsVisible());
    }
}
