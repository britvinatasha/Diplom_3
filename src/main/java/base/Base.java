package base;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import user.UserData;
import user.UserLogin;
import org.openqa.selenium.WebDriver;

public class Base {
    private static final Faker faker = new Faker();
    private final BaseHttpClient baseHttpClient = new BaseHttpClient();
    private String accessToken;
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Создание пользователя")
    public  UserData createTestUser() {
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress().toLowerCase();
        String password = faker.internet().password();


        UserData testUser = new UserData(name, email, password);
        baseHttpClient.userRegister(testUser);
        accessToken = baseHttpClient.getAccessToken(new UserLogin(email, password));
        return testUser;
    }

    @Step("Удаление тестового пользователя")
    public void deleteTestUser() {
        System.out.println("Удаление пользователя: " + accessToken);
        if (accessToken != null) {
            baseHttpClient.deleteUser(accessToken);
        } else {
            System.out.println("Токен не получен, невозможно удалить пользователя.");
        }
    }
}
