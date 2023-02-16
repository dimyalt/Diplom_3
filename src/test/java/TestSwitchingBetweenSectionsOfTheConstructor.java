import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import sitepages.ConstructorStellarburgers;
import sitepages.data.DriverOptions;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestSwitchingBetweenSectionsOfTheConstructor {
    WebDriver driver;
    DriverOptions driverBrowser = new DriverOptions();
    private final String sector;
    private final String browser;  //Переменная для выбора браузера для тестирования (или Chrome или Яндекс.Браузер)
    private String resultActual;
    private final String RESULT_EXPECTED_SAUCE = "Соусы";
    private final String RESULT_EXPECTED_BUN = "Булки";
    private final String RESULT_EXPECTED_STUFFING = "Начинки";

    public TestSwitchingBetweenSectionsOfTheConstructor(String sector, String browser) {
        this.sector = sector;
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Соусы", "chrome"},
                {"Соусы", "browser"},
                {"Булки", "chrome"},
                {"Булки", "browser"},
                {"Начинки", "chrome"},
                {"Начинки", "browser"},
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
    @DisplayName("Переходы между разделов конструктора")
    public void transitionConstructorSectors(){
        ConstructorStellarburgers objConstructorPage = new ConstructorStellarburgers(); //Создаем объект класса страницы конструктора
        driver.get(objConstructorPage.getMainPageTestURL()); //Переходим на главную страницу
        switch (sector) {
            case RESULT_EXPECTED_SAUCE:
                driver.findElement(objConstructorPage.getSectionSauceBtn()).click(); // Кликнули по кнопке радела
                new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
                resultActual = driver.findElement(objConstructorPage.getResultSection()).getText(); // Получаем название активного раздела
                assertEquals(RESULT_EXPECTED_SAUCE, resultActual);
                break;
            case RESULT_EXPECTED_BUN:
                driver.findElement(objConstructorPage.getSectionSauceBtn()).click(); // Кликнули по кнопке радела
                new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
                driver.findElement(objConstructorPage.getSectionBunBtn()).click(); // Кликнули по кнопке радела
                new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
                resultActual = driver.findElement(objConstructorPage.getResultSection()).getText(); // Получаем название активного раздела
                assertEquals(RESULT_EXPECTED_BUN, resultActual);
                break;
            case RESULT_EXPECTED_STUFFING:
                driver.findElement(objConstructorPage.getSectionStuffingBtn()).click(); // Кликнули по кнопке радела
                new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
                resultActual = driver.findElement(objConstructorPage.getResultSection()).getText(); // Получаем название активного раздела
                assertEquals(RESULT_EXPECTED_STUFFING, resultActual);
                break;
        }
    }
    @After
    public void tearsDown(){
        driver.quit();
    }
}
