package base;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import user.UserData;

@Getter


public class BaseForRegistration {
    private static final Faker faker = new Faker();

    private final UserData testUser;

    private final BaseHttpClient baseHttpClient;

    @Setter
    private String accessToken;

    public BaseForRegistration() {
        this.baseHttpClient = new BaseHttpClient();
        this.testUser = generateTestUserData();
    }

    @Step("Генерация данных для нового пользователя")
    public UserData generateTestUserData() {
        return new UserData(
                faker.name().fullName(),
                faker.internet().emailAddress().toLowerCase(),
                faker.internet().password()
        );
    }

    @Step("Удаление тестового пользователя")
    public void deleteTestUser() {
        if (accessToken != null) {
            System.out.println("🗑 Удаляем тестового пользователя...");
            baseHttpClient.deleteUser(accessToken);
            System.out.println("✅ Пользователь успешно удалён.");
        } else {
            System.out.println("⚠ Пользователь не был зарегистрирован, удаление не требуется.");
        }
    }
}


