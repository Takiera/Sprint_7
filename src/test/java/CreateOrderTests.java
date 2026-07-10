import io.qameta.allure.Description;
import model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.isA;
import static steps.OrderSteps.createOrder;

@RunWith(Parameterized.class)
public class CreateOrderTests  extends BaseApiTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public CreateOrderTests(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }


    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { "Ангелина", "Изосимова", "Чернышевская, дом 1", 3, "+7 800 355 35 36", 3, "2026-08-08", "без комментариев",new String[]{"BLACK"}},
                { "Юлий", "Котов", "Чернышевская, дом 14", 2, "+7 800 355 33 36", 3, "2026-08-09", "без комментариев",new String[]{"GREY"}},
                { "Влад", "Чреватый", "Чернышевская, дом 66", 4, "+7 800 355 35 66", 3, "2026-09-08", "без комментариев",new String[]{"BLACK", "GREY"}},
                { "Олег", "Швепс", "Чернышевская, дом 33", 1, "+7 800 355 35 76", 3, "2026-09-09", "без комментариев",new String[]{}}
        };
    }

    @Test
    @Description("Проверка, что при создании заказа можно выбрать любой из цветов, оба цвета или ни один из цветов")
    public void createOrderTests() {
        OrderModel order  = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        createOrder(order)
                .then()
                .statusCode(HTTP_CREATED)
                .body("track", isA(Integer.class));
    }
}
