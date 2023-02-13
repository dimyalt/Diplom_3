import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sitepages.UserPersonalAccountStarburgers;
import sitepages.data.DriverOptions;

import static org.junit.Assert.assertTrue;

public class TestOfTransitionToPersonalAccount {
    WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site";
    final String userEmail = "testuserChrome25@yandex.ru";
    final String userPassword = "testuser25";

    @Test
    @DisplayName("Переход в личный кабинет в Chrome")
    public void userPersonalAccountChrome() throws Exception {
        DriverOptions driverChrome = new DriverOptions();
        System.setProperty(driverChrome.getDriverType(), driverChrome.getChromeDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.setBinary(driverChrome.getChromePath());
        options.addArguments("--headless");
        driver = new ChromeDriver(options); //Драйвер для Chrome
        driver.get(PAGE_URL);
        UserPersonalAccountStarburgers objPersonalAccountPage = new UserPersonalAccountStarburgers(driver); //Создаем объект класса страницы личного кабинета
        objPersonalAccountPage.loginUserForGetPersonalAccount(userEmail, userPassword);
        assertTrue(objPersonalAccountPage.getUserPersonalAccount());
    }
    @Test
    @DisplayName("Переход в личный кабинет в Яндекс Браузере")
    public void userPersonalAccountBrowser() throws Exception {
        DriverOptions driverBrowser = new DriverOptions();
        System.setProperty(driverBrowser.getDriverType(), driverBrowser.getBrowserDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.setBinary(driverBrowser.getBrowserPath());
        options.addArguments("--headless");
        driver = new ChromeDriver(options); //Драйвер для Яндекс.Браузера
        driver.get(PAGE_URL);
        UserPersonalAccountStarburgers objPersonalAccountPage = new UserPersonalAccountStarburgers(driver); //Создаем объект класса страницы личного кабинета
        objPersonalAccountPage.loginUserForGetPersonalAccount(userEmail, userPassword);
        assertTrue(objPersonalAccountPage.getUserPersonalAccount());
    }

    @After
    public void tearsDown() throws Exception {
        UserPersonalAccountStarburgers objPersonalAccountPage = new UserPersonalAccountStarburgers(driver); //Создаем объект класса страницы личного кабинета
        objPersonalAccountPage.logoutUserFromPersonalAccount();
        driver.quit();
    }
}
