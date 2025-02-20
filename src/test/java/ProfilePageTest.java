import base.Base;
import base.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pom.LoginPage;
import pom.MainPage;
import pom.ProfilePage;
import user.UserData;

import static org.junit.Assert.assertTrue;

public class ProfilePageTest  extends SetUpTest {
    private UserData user;
    private Base base;

    @Before
    @DisplayName("Создание пользователя с помощью API")
    public void setUpUser() {
        base = new Base(driver);
        user = base.createTestUser();
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет")
    public void testNavigateToProfile() {
        driver.get(Constants.LOGIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.userLogin(user.getEmail(), user.getPassword());
        mainPage.orderButtonIsVisible();
        mainPage.clickProfileButton();
        assertTrue("Должен быть переход в личный кабинет", profilePage.exitButtonIsVisible());

    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор через кнопку 'Конструктор'")
    public void testNavigateToConstructorFromProfile() {
        driver.get(Constants.LOGIN_PAGE_URL);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        loginPage.userLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButton();
        profilePage.clickOnConstructorButton();
        assertTrue("Должен быть переход в конструктор", mainPage.orderButtonIsVisible());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор через логотип")
    public void testNavigateToConstructorFromLogo() {
        driver.get(Constants.LOGIN_PAGE_URL);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        loginPage.userLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButton();
        profilePage.clickOnLogo();
        assertTrue("Должен быть переход в конструктор", mainPage.orderButtonIsVisible());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    public void testLogout() {
        driver.get(Constants.LOGIN_PAGE_URL);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        loginPage.userLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButton();
        profilePage.clickOnExitButton();
        assertTrue("Должен быть редирект на страницу логина после выхода",
                loginPage.loginButtonIsVisible());
    }

    @After
    @DisplayName("Удаление созданного пользователя с помощью API")
    public void tearDownUser() {
        base.deleteTestUser();
        super.tearDown();
    }
}
