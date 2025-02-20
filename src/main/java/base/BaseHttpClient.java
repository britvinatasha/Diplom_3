package base;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import user.UserData;
import user.UserLogin;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {

    ValidatableResponse response;

    private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Constants.HOST_URL)
            .addHeader("Content-Type", "application/json")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

    @Step("Регистрация пользователя")
    public void userRegister(UserData user) {
        response = given()
                .spec(requestSpecification)
                .body(user)
                .when()
                .post(Constants.CREATE_USER)
                .then()
                .statusCode(200);
    }

    @Step("Логин пользователя")
    public String getAccessToken(UserLogin userLogin) {
        String accessToken;
        response = given()
                .spec(requestSpecification)
                .body(userLogin)
                .when()
                .post(Constants.LOGIN_USER)
                .then()
                .statusCode(200);
        accessToken = response.extract().path("accessToken");
        return accessToken;
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        response = given()
                .spec(requestSpecification)
                .header("Authorization", accessToken)
                .delete(Constants.DELETE_USER)
                .then()
                .statusCode(202);
    }

}

