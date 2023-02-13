import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sitepages.RegisterStellarburgers;
import sitepages.data.DriverOptions;

import java.util.Random;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestRegistrationSuccessfulAndPasswordError {
    WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private static final Random random = new Random();
    private static final int randomNumber = random.nextInt(100);
    private final String userName;
    private final String userEmail;
    private final String userPassword;
    private final boolean expectedResult;
    private final String browser;  //Переменная для выбора браузера для тестирования (или Chrome или Яндекс.Браузер)

    public TestRegistrationSuccessfulAndPasswordError(String userName, String userEmail, String userPassword, boolean expectedResult, String browser) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.expectedResult = expectedResult;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"testuserChrome" + randomNumber, "testuserChrome" + randomNumber + "@yandex.ru", "testuser" + randomNumber, true, "chrome"},
                {"testuserBrowser" + randomNumber, "testuserBrowser" + randomNumber + "@yandex.ru", "testuser" + randomNumber, true, "browser"},
                {"testuser" + randomNumber, "testuser" + randomNumber + "@yandex.ru", "us" + randomNumber, false, "chrome"},
                {"testuser" + randomNumber, "testuser" + randomNumber + "@yandex.ru", "us" + randomNumber, false, "browser"},
        };
    }
    @Test
    @DisplayName("Создание пользователя")
    public void userRegistration() throws Exception {
        if(browser.equals("chrome")){
            DriverOptions driverChrome = new DriverOptions();
            System.setProperty(driverChrome.getDriverType(), driverChrome.getChromeDriverPath());
            ChromeOptions options = new ChromeOptions();
            options.setBinary(driverChrome.getChromePath());
            options.addArguments("--headless");
            driver = new ChromeDriver(options); //Драйвер для Chrome
        }else{
            DriverOptions driverBrowser = new DriverOptions();
            System.setProperty(driverBrowser.getDriverType(), driverBrowser.getBrowserDriverPath());
            ChromeOptions options = new ChromeOptions();
            options.setBinary(driverBrowser.getBrowserPath());
            options.addArguments("--headless");
            driver = new ChromeDriver(options); //Драйвер для Яндекс.Браузера
        }
        driver.get(PAGE_URL); //Переходим на страницу регистрации
        RegisterStellarburgers objRegistrationPage = new RegisterStellarburgers(driver); //Создаем объект класса страницы регистрации
        objRegistrationPage.setNameField(userName); // Заполняем поле "Имя"
        objRegistrationPage.setEmailField(userEmail); // Заполняем поле "Email"
        objRegistrationPage.setPasswordField(userPassword); // Заполняем поле "Пароль"
        objRegistrationPage.clickButtonRegistration(); // Жмем на кнопку "Зарегистрироваться"
        assertEquals(expectedResult, objRegistrationPage.getRegistrationStatus()); //Сравниваем ОР и ФР
    }
    @After
    public void tearsDown(){
        driver.quit();
    }
}
