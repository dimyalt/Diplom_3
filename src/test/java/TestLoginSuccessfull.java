import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sitepages.LoginStellarburgers;
import sitepages.data.DriverOptions;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestLoginSuccessfull {
    WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final String userEmail = "testuserChrome25@yandex.ru";
    private final String userPassword = "testuser25";
    private final String pointOfEntry;
    private final String browser;

    public TestLoginSuccessfull(String pointOfEntry, String browser) {
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

    @Test
    @DisplayName("Логин пользователя")
    public void userLogin() throws Exception {
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
        driver.get(PAGE_URL); //Переходим на главную страницу
        LoginStellarburgers objLoginPage = new LoginStellarburgers(driver); //Создаем объект класса страницы логина
        assertTrue(objLoginPage.getUserLogin(pointOfEntry, userEmail, userPassword));
    }

    @After
    public void tearsDown(){
        LoginStellarburgers objLoginPage = new LoginStellarburgers(driver); //Создаем объект класса страницы логина
        objLoginPage.userLogout();
        driver.quit();
    }
}
