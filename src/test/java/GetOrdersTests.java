import io.qameta.allure.Description;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasKey;

public class GetOrdersTests extends BaseApiTest{
    @Test
    @Description("Проверка, что запрос на получение списка заказов возвращает список заказов")
    public void getOrdersReturnOrdersList() {
        given()
                .get("/api/v1/orders")
                .then()
                .statusCode(HTTP_OK)
                .body("$", hasKey("orders"))
                .body("orders", instanceOf(List.class));
    }
}
