import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sitepages.RegisterStellarburgers;
import sitepages.data.DriverOptions;
import java.time.Duration;
import java.util.Random;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestRegistrationWithWrongPasswordError {
    WebDriver driver;
    DriverOptions driverBrowser = new DriverOptions();
    private static final Random random = new Random();
    private static final int randomNumber = random.nextInt(1000);
    private final String userName;
    private final String userEmail;
    private final String userPassword;
    private final String browser;  //Переменная для выбора браузера для тестирования (или Chrome или Яндекс.Браузер)

    public TestRegistrationWithWrongPasswordError(String userName, String userEmail, String userPassword, String browser) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"testuse" + randomNumber, "testuser_" + randomNumber + "@yandex.ru", "u" + randomNumber, "chrome"},
                {"testuse" + randomNumber, "testuser_" + randomNumber + "@yandex.ru", "u" + randomNumber, "browser"},
        };
    }
    @Before
    public void setUp(){

        if (browser.equals("chrome")) {
            driver = new ChromeDriver(driverBrowser.getChromeOptions()); //Драйвер для Chrome
        } else {
            driver = new ChromeDriver(driverBrowser.getYaBrowserOptions()); //Драйвер для Яндекс.Браузер
        }
    }
    @Test
    @DisplayName("Создание пользователя")
    public void userRegistrationWithWrongPassword(){
        driver.get(RegisterStellarburgers.getRegistrationPageTestURL()); //Переходим на страницу регистрации
        driver.findElement(RegisterStellarburgers.getFieldNameRegistration()).clear();
        driver.findElement(RegisterStellarburgers.getFieldNameRegistration()).sendKeys(userName);
        driver.findElement(RegisterStellarburgers.getFieldEmailRegistration()).clear();
        driver.findElement(RegisterStellarburgers.getFieldEmailRegistration()).sendKeys(userEmail);
        driver.findElement(RegisterStellarburgers.getFieldPasswordRegistration()).clear();
        driver.findElement(RegisterStellarburgers.getFieldPasswordRegistration()).sendKeys(userPassword);
        driver.findElement(RegisterStellarburgers.getButtonRegistration()).click();
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(RegisterStellarburgers.getMessageErrorPassword()));
        assertTrue(driver.findElement(RegisterStellarburgers.getMessageErrorPassword()).isDisplayed());
    }
    @After
    public void tearsDown(){
        driver.quit();
    }
}
