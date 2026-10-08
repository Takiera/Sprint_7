package data;

public class CourierData {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    public static final String LOGIN = "super_login" + System.currentTimeMillis();
    public static final String PASSWORD = "12345";
    public static final String FIRST_NAME = "Oleg";

    public static final String CREATE_COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    public static final String DELETE_COURIER_PATH = "/api/v1/courier/";

}
