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
import sitepages.LoginStellarburgers;
import sitepages.data.DriverOptions;
import sitepages.data.SiteOptions;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestLoginSuccessful {
    WebDriver driver;
    private final String pointOfEntry;
    private final String browser;
    DriverOptions driverBrowser = new DriverOptions();
    LoginStellarburgers objLoginPage = new LoginStellarburgers(); //Создаем объект класса страницы логина
    public TestLoginSuccessful(String pointOfEntry, String browser) {
        this.pointOfEntry = pointOfEntry;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"Войти в аккаунт", "chrome"},
                {"Войти в аккаунт", "browser"},
                {"Личный кабинет", "chrome"},
                {"Личный кабинет", "browser"},
                {"Регистрация", "chrome"},
                {"Регистрация", "browser"},
                {"Восстановление пароля", "chrome"},
                {"Восстановление пароля", "browser"},
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
    @DisplayName("Логин пользователя")
    public void userLogin(){
        switch (pointOfEntry) {
            case "Войти в аккаунт":
                driver.get(LoginStellarburgers.getMainPageTestURL()); //Переходим на главную страницу
                driver.findElement(objLoginPage.getBurgerLoginButton()).click(); // Кликнули по кнопке "Войти в аккаунт"
                new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                        .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getFieldEmailLogin()));
            case "Личный кабинет":
                driver.get(LoginStellarburgers.getMainPageTestURL()); //Переходим на главную страницу
                driver.findElement(objLoginPage.getUserArea()).click(); // Кликнули по кнопке "Личный кабинет"
                new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                        .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getFieldEmailLogin()));
            case "Регистрация":
                driver.get(LoginStellarburgers.getRegisterPageTestURL()); //Переходим на страницу регистрации
                new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления ссылки "Войти"
                        .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getUserLogin()));
                driver.findElement(objLoginPage.getUserLogin()).click(); // Кликнули по ссылке "Войти"
                new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                        .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getFieldEmailLogin()));
            case "Восстановление пароля":
                driver.get(LoginStellarburgers.getPasswordResetPageTestUrl()); //Переходим на страницу восстановления пароля
                new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления ссылки "Войти"
                        .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getUserLogin()));
                driver.findElement(objLoginPage.getUserLogin()).click(); // Кликнули по ссылке "Войти"
                new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                        .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getFieldEmailLogin()));
            }
        driver.findElement(objLoginPage.getFieldEmailLogin()).clear();
        driver.findElement(objLoginPage.getFieldEmailLogin()).sendKeys(SiteOptions.getTestUserEmail());
        driver.findElement(objLoginPage.getFieldPasswordLogin()).clear();
        driver.findElement(objLoginPage.getFieldPasswordLogin()).sendKeys(SiteOptions.getTestUserPassword());
        driver.findElement(objLoginPage.getButtonEnter()).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(objLoginPage.getBurgerCheckout()));
        assertTrue(driver.findElement(objLoginPage.getBurgerCheckout()).isDisplayed());
    }

    @After
    public void tearsDown(){
        objLoginPage.userLogout(driver);
        driver.quit();
    }
}
