package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;

public class RegisterPage {
    private WebDriver driver;

    private By nameField = By.xpath(".//label[text()='Имя']/..//input[@type='text']");
    private By emailField = By.xpath(".//label[text()='Email']/..//input[@type='text']");
    private By passwordField = By.xpath(".//input[@name='Пароль']");
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By warningIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private By enterButton = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод имени при регистрации")
    public void setName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email при регистрации")
    public void setEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля при регистрации")
    public void setPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться' на странице регистрации")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Регистрация нового пользователя: Имя, Email, Пароль")
    public void userRegistration(String name, String email, String password) {

        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    @Step("Проверка наличия предупреждения о некорректном пароле")
    public boolean isWarningIncorrectPasswordDisplayed() {
        return driver.findElement(warningIncorrectPassword).isDisplayed();
    }

    @Step("Нажать на кнопку 'Войти' для перехода на страницу авторизации")
    public void clickLoginButton() {
        WebElement element = driver.findElement(enterButton);
        scrollToElement(element);
        element.click();
    }


    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}