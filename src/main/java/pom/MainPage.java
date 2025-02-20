package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class MainPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    private final By profileButton = By.xpath(".//a[@href='/account']");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    public final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final String selectedSection = "//div[contains(@class, 'current')]//span[text() = '%s']";
    private By bunElement = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    private By sauceElement = By.xpath(".//img[@alt='Соус фирменный Space Sauce']");
    public By fillingElement = By.xpath(".//img[@alt='Филе Люминесцентного тетраодонтимформа']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public boolean orderButtonIsVisible() {
        return isElementDisplayed(orderButton);
    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }


    @Step("Ожидаем появления актуальной вкладки 'Соусы'")
    public boolean waitSelectedSauce() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(sauceElement));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(sauceElement));
        return driver.findElement(sauceElement).isDisplayed();

    }

    @Step("Ожидаем появления актуальной вкладки 'Начинки'")
    public boolean waitSelectedFilling() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(fillingElement));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(fillingElement));
        return driver.findElement(fillingElement).isDisplayed();
    }

    @Step("Ожидаем появления актуальной вкладки 'Булки'")
    public boolean waitSelectedBun() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(bunElement));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(bunElement));
        return driver.findElement(bunElement).isDisplayed();
    }
}