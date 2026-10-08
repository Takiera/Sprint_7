package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;

import static data.CourierData.*;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("создание курьера")
    public static Response createCourier(CourierModel courier) {
        return given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH)
                .then()
                .extract().response();
    }

    @Step("авторизация курьера")
    public static Response loginCourier(CourierModel courier) {
        return given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(LOGIN_COURIER_PATH)
                .then()
                .extract().response();
    }

    @Step("удаление курьера")
    public static Response deleteCourier(CourierModel courier) {
        return given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .delete(DELETE_COURIER_PATH + courier.getId())
                .then()
                .extract().response();
    }
}
