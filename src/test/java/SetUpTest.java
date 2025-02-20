import base.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class SetUpTest {
    protected WebDriver driver;


    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriverInstance();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

