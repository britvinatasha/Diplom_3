package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By stellarLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажать на кнопку 'Выйти'")
    public void clickOnExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Нажать на кнопку 'Конструктор")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на лого")
    public void clickOnLogo() {
        driver.findElement(stellarLogo).click();
    }

    @Step("В личном кабинте появилась кнопка'Выйти'")
    public boolean exitButtonIsVisible() {
        return isElementDisplayed(exitButton);
    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}
