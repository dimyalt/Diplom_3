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
import sitepages.UserPersonalAccountStarburgers;
import sitepages.data.DriverOptions;
import sitepages.data.SiteOptions;
import java.time.Duration;

@RunWith(Parameterized.class)
public class TestOfTransitionToPersonalAccount {
    WebDriver driver;
    DriverOptions driverBrowser = new DriverOptions();
    UserPersonalAccountStarburgers objUserPersonalAccount; //Создаем объект класса страницы личного кабинета
    private final String browser;

    public TestOfTransitionToPersonalAccount(String browser) {
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
        objUserPersonalAccount = new UserPersonalAccountStarburgers();
        driver.get(UserPersonalAccountStarburgers.getLoginPageTestURL()); //Переходим на главную страницу
        SiteOptions.getLoginUserAccount(driver); //Логинимся
    }
    @Test
    @DisplayName("Переход в личный кабинет пользователя")
    public void userPersonalAccount(){
        driver.findElement(objUserPersonalAccount.getUserArea()).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления кнопки профиль
                .until(ExpectedConditions.visibilityOfElementLocated(objUserPersonalAccount.getUserProfileButton()));
    }

    @After
    public void tearsDown(){
        SiteOptions.getUserAccount(driver);
        SiteOptions.clickExitButton(driver);
        driver.quit();
    }
}
