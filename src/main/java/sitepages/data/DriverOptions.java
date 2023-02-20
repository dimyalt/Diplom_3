package sitepages.data;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverOptions {
    private final String BROWSER_PATH = "C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
    private final String DRIVER_TYPE = "webdriver.chrome.driver";
    private final String BROWSER_DRIVER_PATH = "C:\\WebDriver\\bin\\chrome_for_yandex\\chromedriver.exe";

    public DriverOptions() {
    }

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return options;
    }
    public ChromeOptions getYaBrowserOptions(){
        System.setProperty(DRIVER_TYPE, BROWSER_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(BROWSER_PATH);
        options.addArguments("--headless");
        return options;
    }

}
