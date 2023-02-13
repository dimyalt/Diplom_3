import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sitepages.ConstructorStellarburgers;
import sitepages.data.DriverOptions;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestSwitchingBetweenSectionsOfTheConstructor {
    WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final String sector;
    private final String browser;  //Переменная для выбора браузера для тестирования (или Chrome или Яндекс.Браузер)

    public TestSwitchingBetweenSectionsOfTheConstructor(String sector, String browser) {
        this.sector = sector;
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Sauce", "chrome"},
                {"Sauce", "browser"},
                {"Bun", "chrome"},
                {"Bun", "browser"},
                {"Stuffing", "chrome"},
                {"Stuffing", "browser"},
        };
    }
    @Test
    @DisplayName("Переходы между разделов конструктора")
    public void transitionConstructorSectors() throws Exception {
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
        driver.get(PAGE_URL); //Переходим на страницу логина
        ConstructorStellarburgers objConstructorPage = new ConstructorStellarburgers(driver); //Создаем объект класса страницы регистрации
        assertTrue(objConstructorPage.checkConstructorSectors(sector));
    }
    @After
    public void tearsDown(){
        driver.quit();
    }
}
