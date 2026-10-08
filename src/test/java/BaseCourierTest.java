import model.CourierModel;
import org.junit.After;
import org.junit.Before;

import static data.CourierData.*;
import static steps.CourierSteps.deleteCourier;
import static steps.CourierSteps.loginCourier;

public class BaseCourierTest extends BaseApiTest{

    protected CourierModel courier;

    @Before
    public void setUp() {
        courier = new CourierModel(LOGIN, PASSWORD, FIRST_NAME);
    }

    @After
    public void tearDown() {
        try {
            if (courier != null) {
                String courierId = getCourierId(courier);
                if (courierId != null) {
                    deleteCourier(new CourierModel(courierId));
                }
            }
        } catch (Exception e) {}
    }

    protected String getCourierId(CourierModel courier) {
        CourierModel loginData = new CourierModel(courier.getLogin(), courier.getPassword());
        return loginCourier(loginData)
                .then()
                .extract()
                .path("id")
                .toString();
    }
}
