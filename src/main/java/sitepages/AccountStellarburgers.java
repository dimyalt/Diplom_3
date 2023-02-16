package sitepages;
import org.openqa.selenium.By;


public class AccountStellarburgers {
    private static final String LOGIN_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final By fieldEmailLogin = By.xpath("//*[text()='Email']/following::input");
    //Локатор кнопки "Выход" на странице аккаунта
    private static final By exitButton = By.xpath("//button[text()='Выход']");
    private static final String EXPECTED_URL = "https://stellarburgers.nomoreparties.site/login";

    public AccountStellarburgers() {
    }
    public static String getLoginPageTestURL() {
        return LOGIN_PAGE_TEST_URL;
    }

    public static String getExpectedURL() {
        return EXPECTED_URL;
    }
    public static By getExitButton() {
        return exitButton;
    }
    public static By getFieldEmailLogin() {
        return fieldEmailLogin;
    }


}
