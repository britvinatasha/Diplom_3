package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private final By loginButton = By.xpath(".//a[contains(text(),'Войти')]");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку'Войти' ")
    public void clickLoginButton(){
        driver.findElement(loginButton).isEnabled();
        driver.findElement(loginButton).click();
    }
}