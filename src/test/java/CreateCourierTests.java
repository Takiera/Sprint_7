import model.CourierModel;
import org.junit.Test;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTests extends BaseCourierTest {

   @Test
    public void createCourierSuccess() {

    createCourier(courier)
            .then()
            .statusCode(HTTP_CREATED)
            .body("ok", equalTo(true));
   }

    @Test
    public void createDuplicateCourierReturnException() {
        createCourier(courier);
        createCourier(courier)
                .then()
                .statusCode(HTTP_CONFLICT);
    }

    @Test
    public void createCourierWithoutLoginReturnException() {
        CourierModel courierWithoutLogin = new CourierModel(null, courier.getPassword(), courier.getFirstName());
        createCourier(courierWithoutLogin)
                .then()
                .statusCode(HTTP_BAD_REQUEST);
    }

    @Test
    public void createCourierWithoutPasswordReturnException() {
        CourierModel courierWithoutPassword = new CourierModel(courier.getLogin(), null, courier.getFirstName());
        createCourier(courierWithoutPassword)
                .then()
                .statusCode(HTTP_BAD_REQUEST);
    }

}
