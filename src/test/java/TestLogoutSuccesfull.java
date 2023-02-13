import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sitepages.AccountStellarburgers;
import sitepages.data.DriverOptions;

import static org.junit.Assert.assertTrue;

public class TestLogoutSuccesfull {
    WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final String userEmail = "testuserChrome25@yandex.ru";
    private final String userPassword = "testuser25";

    @Test
    @DisplayName("Выход из личного кабинета в Chrome")
    public void logoutUserAccountChrome() throws Exception {
        DriverOptions driverChrome = new DriverOptions();
        System.setProperty(driverChrome.getDriverType(), driverChrome.getChromeDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.setBinary(driverChrome.getChromePath());
        options.addArguments("--headless");
        driver = new ChromeDriver(options); //Драйвер для Chrome
        driver.get(PAGE_URL);
        AccountStellarburgers objAccountPage = new AccountStellarburgers(driver); //Создаем объект класса страницы личного кабинета
        objAccountPage.getLoginUserAccount(userEmail, userPassword);
        objAccountPage.clickExitButton();
        assertTrue(objAccountPage.getLogoutStatus());
    }
    @Test
    @DisplayName("Выход из личного кабинета в Chrome")
    public void logoutUserAccountBrowser() throws Exception {
        DriverOptions driverBrowser = new DriverOptions();
        System.setProperty(driverBrowser.getDriverType(), driverBrowser.getBrowserDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.setBinary(driverBrowser.getBrowserPath());
        options.addArguments("--headless");
        driver = new ChromeDriver(options); //Драйвер для Яндекс.Браузера
        driver.get(PAGE_URL);
        AccountStellarburgers objAccountPage = new AccountStellarburgers(driver); //Создаем объект класса страницы личного кабинета
        objAccountPage.getLoginUserAccount(userEmail, userPassword);
        objAccountPage.clickExitButton();
        assertTrue(objAccountPage.getLogoutStatus());
    }


}
