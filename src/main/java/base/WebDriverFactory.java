package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static WebDriver getDriverInstance() {
        String browser = System.getProperty("webdriver.driver", "chrome");
        WebDriver driver;
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else {
            System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}