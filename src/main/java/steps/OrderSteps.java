package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("создание заказа")
    public static Response createOrder(OrderModel order) {
        return given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then()
                .extract().response();
    }

    @Step("получение списка заказов")
    public static Response getOrdersList() {
        return given()
                .get("/api/v1/orders")
                .then()
                .extract().response();
    }
}
