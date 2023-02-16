import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sitepages.AccountStellarburgers;
import sitepages.ConstructorStellarburgers;
import sitepages.data.DriverOptions;
import sitepages.data.SiteOptions;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOfTransitionFromPersonalAccountToConstructor {
    WebDriver driver;
    DriverOptions driverBrowser = new DriverOptions();
    ConstructorStellarburgers objConstructorPage; //Создаем объект класса страницы конструктора
    private final String clickPoint;
    private final String browser;  //Переменная для выбора браузера для тестирования (или Chrome или Яндекс.Браузер)

    public TestOfTransitionFromPersonalAccountToConstructor(String clickPoint, String browser) {
        this.clickPoint = clickPoint;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"pClass", "chrome"},
                {"pClass", "browser"},
                {"divClass", "chrome"},
                {"divClass", "browser"},
        };
    }
    @Before
    public void setUp(){
        if (browser.equals("chrome")) {
            driver = new ChromeDriver(driverBrowser.getChromeOptions()); //Драйвер для Chrome
        } else {
            driver = new ChromeDriver(driverBrowser.getYaBrowserOptions()); //Драйвер для Яндекс.Браузер
        }
        objConstructorPage = new ConstructorStellarburgers();
        driver.get(AccountStellarburgers.getLoginPageTestURL()); //Переходим на главную страницу
        SiteOptions.getLoginUserAccount(driver); //Логинимся
        SiteOptions.getUserAccount(driver);//Переходим в личный кабинет
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionToConstructor(){
        if (clickPoint.equals("pClass")){
            driver.findElement(objConstructorPage.getConstructorButton()).click();
        }else{
            driver.findElement(objConstructorPage.getConstructorLogo()).click();
        }
        assertTrue(driver.findElement(objConstructorPage.getBurgerCheckout()).isDisplayed());
    }
    @After
    public void tearsDown(){
        SiteOptions.getUserAccount(driver);
        SiteOptions.clickExitButton(driver);
        driver.quit();
    }


}
