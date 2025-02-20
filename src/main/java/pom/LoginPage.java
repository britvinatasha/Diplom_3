package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    private By emailField = By.xpath(".//input[@type='text']");
    private By passwordField = By.xpath(".//input[@type='password']");
    private By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод в поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод в поле 'Password'")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие на кнопу 'Войти' на странице авторизации")
    public void clickToTheEnterButton() {
        driver.findElement(loginButton).click();
    }

    public void userLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickToTheEnterButton();
    }

    @Step("")
    public boolean loginButtonIsVisible() {
        return isElementDisplayed(loginButton);
    }

     boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}
