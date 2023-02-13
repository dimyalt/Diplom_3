package sitepages.data;


public class DriverOptions {
    private final String CHROME_PATH = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
    private final String BROWSER_PATH = "C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
    private final String DRIVER_TYPE = "webdriver.chrome.driver";
    private final String CHROME_DRIVER_PATH = "C:\\WebDriver\\bin\\chromedriver.exe";
    private final String BROWSER_DRIVER_PATH = "C:\\WebDriver\\bin\\chrome_for_yandex\\chromedriver.exe";

    public DriverOptions() {
    }

    public String getChromePath() {
        return CHROME_PATH;
    }

    public String getBrowserPath() {
        return BROWSER_PATH;
    }

    public String getDriverType() {
        return DRIVER_TYPE;
    }

    public String getChromeDriverPath() {
        return CHROME_DRIVER_PATH;
    }

    public String getBrowserDriverPath() {
        return BROWSER_DRIVER_PATH;
    }
}
