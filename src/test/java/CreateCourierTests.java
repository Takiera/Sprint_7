import io.qameta.allure.Description;
import model.CourierModel;
import org.junit.Test;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTests extends BaseCourierTest {

   @Test
   @Description("Проверка успешного создания курьера при корректных значениях")
    public void createCourierSuccess() {

    createCourier(courier)
            .then()
            .statusCode(HTTP_CREATED)
            .body("ok", equalTo(true));
   }

    @Test
    @Description("Проверка, что попытка создания двух одинаковых курьеров возвращает ошибку")
    public void createDuplicateCourierReturnException() {
        createCourier(courier);
        createCourier(courier)
                .then()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @Description("Проверка, что попытка создания курьера без поля логин возвращает ошибку")
    public void createCourierWithoutLoginReturnException() {
        CourierModel courierWithoutLogin = new CourierModel(null, courier.getPassword(), courier.getFirstName());
        createCourier(courierWithoutLogin)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @Description("Проверка, что попытка создания курьера без поля пароль возвращает ошибку")
    public void createCourierWithoutPasswordReturnException() {
        CourierModel courierWithoutPassword = new CourierModel(courier.getLogin(), null, courier.getFirstName());
        createCourier(courierWithoutPassword)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
