import io.qameta.allure.Description;
import model.CourierModel;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.deleteCourier;

public class DeleteCourierTests extends BaseCourierTest{
    @Test
    @Description("Проверка, что успешный запрос на удаление курьера возвращает ok: true")
    public void deleteCourierSuccess() {
        createCourier(courier);
        String courierId = getCourierId(courier);
        CourierModel deleteCourierData = new CourierModel(courierId);
        deleteCourier(deleteCourierData)
                .then()
                .statusCode(HTTP_OK)
                .body("ok", equalTo(true));
    }
}