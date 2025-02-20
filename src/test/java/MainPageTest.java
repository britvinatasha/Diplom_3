import base.Constants;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import static org.junit.Assert.assertTrue;
import pom.MainPage;

public class MainPageTest extends SetUpTest {

    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    public void testNavigateToSauces() {
        MainPage mainPage = new MainPage(driver);
        driver.get(Constants.HOST_URL);
        mainPage.clickSaucesTab();
        assertTrue("Раздел 'Соусы' должен быть активен",mainPage.waitSelectedSauce());
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    public void testNavigateToFillings() {
       MainPage mainPage = new MainPage(driver);
        driver.get(Constants.HOST_URL);
        mainPage.clickFillingsTab();
        assertTrue("Раздел 'Начинки' должен быть активен", mainPage.waitSelectedFilling());
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    public void testNavigateToBuns() {
        MainPage mainPage = new MainPage(driver);
        driver.get(Constants.HOST_URL);
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        assertTrue("Раздел 'Булки' должен быть активен", mainPage.waitSelectedBun());
    }
}