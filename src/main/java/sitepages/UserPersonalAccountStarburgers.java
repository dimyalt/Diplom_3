package sitepages;
import org.openqa.selenium.By;


public class UserPersonalAccountStarburgers {
    private final By userArea = By.xpath("//a[@href='/account']");
    private final By userProfileButton = By.xpath("//a[@href='/account/profile']");
    private static final String LOGIN_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site/login";

    public UserPersonalAccountStarburgers() {

    }
    public static String getLoginPageTestURL() {
        return LOGIN_PAGE_TEST_URL;
    }

    public By getUserArea() {
        return userArea;
    }

    public By getUserProfileButton() {
        return userProfileButton;
    }
}
