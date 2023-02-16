package sitepages;
import org.openqa.selenium.By;

public class RegisterStellarburgers {
    //private final WebDriver driver;
    private static final String REGISTRATION_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site/register";
    //Локатор поля ввода "Имя" на странице регистрации
    private static final By fieldNameRegistration = By.xpath("//*[text()='Имя']/following::input");
    //Локатор поля ввода "Email" на странице регистрации
    private static final By fieldEmailRegistration = By.xpath("//*[text()='Email']/following::input");
    //Локатор поля ввода "Пароль" на странице регистрации
    private static final By fieldPasswordRegistration = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Зарегистрироваться" на странице регистрации
    private static final By buttonRegistration = By.xpath("//*[text()='Зарегистрироваться']");
    //Локатор сообщения о некорректном пароле на странице регистрации
    private static final By messageErrorPassword = By.xpath("//*[text()='Некорректный пароль']");
    //Локатор надписи "Вход" странице логина
    private static final By buttonEnter = By.xpath("//h2[text()='Вход']");
    //Ожидаемый адрес страницы после регистраци
    private static final String expectedURL = "https://stellarburgers.nomoreparties.site/login";

    public RegisterStellarburgers() {

    }

    public static String getRegistrationPageTestURL() {
        return REGISTRATION_PAGE_TEST_URL;
    }
    public static By getFieldNameRegistration() {
        return fieldNameRegistration;
    }

    public static By getFieldEmailRegistration() {
        return fieldEmailRegistration;
    }

    public static By getFieldPasswordRegistration() {
        return fieldPasswordRegistration;
    }

    public static By getButtonRegistration() {
        return buttonRegistration;
    }

    public static By getMessageErrorPassword() {
        return messageErrorPassword;
    }

    public static By getButtonEnter() {
        return buttonEnter;
    }

    public static String getExpectedURL() {
        return expectedURL;
    }
}
