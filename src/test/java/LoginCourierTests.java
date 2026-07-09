import io.qameta.allure.Description;
import model.CourierModel;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;

public class LoginCourierTests extends BaseCourierTest {

    @Test
    @Description("Проверка успешной авторизации курьера при корректных значениях")
    public void loginCourierSuccess() {
        createCourier(courier);
        CourierModel loginCourierData = new CourierModel(courier.getLogin(), courier.getPassword());
        loginCourier(loginCourierData)
                .then()
                .statusCode(HTTP_OK)
                .body("id", notNullValue());
    }

    @Test
    @Description("Проверка, что попытка авторизации курьера при неверном логине возвращает ошибку")
    public void loginCourierWithWrongLoginReturnException() {
        createCourier(courier);
        CourierModel loginCourierDataWrongLogin = new CourierModel("wrong login", courier.getPassword());
        loginCourier(loginCourierDataWrongLogin)
                .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Проверка, что попытка авторизации курьера при неверном пароле возвращает ошибку")
    public void loginCourierWithWrongPasswordReturnException() {
        createCourier(courier);
        CourierModel loginCourierDataWrongLogin = new CourierModel(courier.getLogin(), "wrong password");
        loginCourier(loginCourierDataWrongLogin)
                .then()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    @Description("Проверка, что попытка авторизации курьера при отсутствии обязательного поля возвращает ошибку")
    public void loginCourierWithoutRequiredFieldReturnException() {
        createCourier(courier);
        CourierModel loginCourierDataWithoutLogin = new CourierModel(null, courier.getPassword());
        loginCourier(loginCourierDataWithoutLogin)
                .then()
                .statusCode(HTTP_BAD_REQUEST);
    }

}
