import io.qameta.allure.Step;
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
    private final String browser;  //Переменная для выбора браузера для тестирования (или Chrome или Яндекс.Браузер)
    private String resultActual;
    private final String RESULT_EXPECTED_SAUCE = "Соусы";
    private final String RESULT_EXPECTED_BUN = "Булки";
    private final String RESULT_EXPECTED_STUFFING = "Начинки";
    ConstructorStellarburgers objConstructorPage = new ConstructorStellarburgers(); //Создаем объект класса страницы конструктора
    public TestSwitchingBetweenSectionsOfTheConstructor(String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Chrome"},
                {"Yandex.browser"},
        };
    }
    @Before
    public void setUp(){
        if (browser.equals("Chrome")) {
            driver = new ChromeDriver(driverBrowser.getChromeOptions()); //Драйвер для Chrome
        } else {
            driver = new ChromeDriver(driverBrowser.getYaBrowserOptions()); //Драйвер для Яндекс.Браузер
        }
    }
    @Test
    @DisplayName("Переход в раздел \"Соусы\"")
    @Step("Переходим в \"Соусы\"")
    public void transitionSectorSauce(){
        driver.get(objConstructorPage.getMainPageTestURL()); //Переходим на главную страницу
        driver.findElement(objConstructorPage.getSectionSauceBtn()).click(); // Кликнули по кнопке радела
        new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
        resultActual = driver.findElement(objConstructorPage.getResultSection()).getText(); // Получаем название активного раздела
        assertEquals(RESULT_EXPECTED_SAUCE, resultActual);
    }
    @Test
    @DisplayName("Переход в раздел \"Начинки\"")
    @Step("Переходим в \"Начинки\"")
    public void transitionSectorStuffing(){
        driver.get(objConstructorPage.getMainPageTestURL()); //Переходим на главную страницу
        driver.findElement(objConstructorPage.getSectionStuffingBtn()).click(); // Кликнули по кнопке радела
        new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
        resultActual = driver.findElement(objConstructorPage.getResultSection()).getText(); // Получаем название активного раздела
        assertEquals(RESULT_EXPECTED_STUFFING, resultActual);
    }
    @Test
    @DisplayName("Переход в раздел \"Булки\"")
    @Step("Переходим в \"Булки\"")
    public void transitionSectorBuns(){
        driver.get(objConstructorPage.getMainPageTestURL()); //Переходим на главную страницу
        driver.findElement(objConstructorPage.getSectionSauceBtn()).click(); // Кликнули по кнопке радела
        new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
        driver.findElement(objConstructorPage.getSectionBunBtn()).click(); // Кликнули по кнопке радела
        new WebDriverWait(driver, Duration.ofSeconds(1)); // Ждем 1 секунду
        resultActual = driver.findElement(objConstructorPage.getResultSection()).getText(); // Получаем название активного раздела
        assertEquals(RESULT_EXPECTED_BUN, resultActual);
    }
    @After
    public void tearsDown(){
        driver.quit();
    }
}
