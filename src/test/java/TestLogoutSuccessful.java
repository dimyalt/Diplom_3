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
import sitepages.AccountStellarburgers;
import sitepages.data.DriverOptions;
import sitepages.data.SiteOptions;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestLogoutSuccessful {
    WebDriver driver;
    DriverOptions driverBrowser = new DriverOptions();
    AccountStellarburgers objAccountPage; //Создаем объект класса страницы личного кабинета
    private final String browser;


    public TestLogoutSuccessful(String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {"chrome"},
                {"browser"},
        };
    }

    @Before
    public void setUp(){
        if (browser.equals("chrome")) {
            driver = new ChromeDriver(driverBrowser.getChromeOptions()); //Драйвер для Chrome
        } else {
            driver = new ChromeDriver(driverBrowser.getYaBrowserOptions()); //Драйвер для Яндекс.Браузер
        }
        objAccountPage = new AccountStellarburgers();
        driver.get(AccountStellarburgers.getLoginPageTestURL()); //Переходим на главную страницу
        SiteOptions.getLoginUserAccount(driver); //Логинимся
        SiteOptions.getUserAccount(driver);//Переходим в личный кабинет
    }
    @Test
    @DisplayName("Выход из личного кабинета пользователя")
    public void logoutUserAccount(){
        driver.findElement(AccountStellarburgers.getExitButton()).click();
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(AccountStellarburgers.getFieldEmailLogin()));
        assertEquals(AccountStellarburgers.getExpectedURL(), driver.getCurrentUrl());

    }
    @After
    public void tearsDown(){
        driver.quit();
    }

}
