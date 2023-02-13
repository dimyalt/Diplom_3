package sitepages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStellarburgers {
    private final WebDriver driver;
    //Локатор поля Email на странице логина
    private final By fieldEmailLogin = By.xpath("//*[text()='Email']/following::input");
    //Локатор поля Пароль на странице логина
    private final By fieldPasswordLogin = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Войти" на странице логина
    private final By buttonEnter = By.xpath("//*[text()='Войти']");
    private final By burgerCheckout = By.xpath("//button[text()='Оформить заказ']");
    private final By burgerLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By userArea = By.xpath("//a[@href='/account']");
    private final By userRegistration = By.xpath("//a[@href='/register']");
    private final By userPasswordReset = By.xpath("//a[@href='/forgot-password']");
    private final By userLogin = By.xpath("//a[@href='/login']");
    private final By buttonExit = By.xpath("//button[text()='Выход']");
    public LoginStellarburgers(WebDriver driver) {
        this.driver = driver;
    }
    public boolean getUserLogin(String pointOfEntry, String setUserEmail, String setUserPassword) throws Exception {
        switch (pointOfEntry) {
            case "Войти в аккаунт":
                return userLoginFromMainPageCheckout(setUserEmail, setUserPassword);
            case "Личный кабинет":
                return userLoginFromUserArea(setUserEmail, setUserPassword);
            case "Регистрация":
                return userLoginFromRegistration(setUserEmail, setUserPassword);
            case "Восстановление пароля":
                return userLoginFromPasswordReset(setUserEmail, setUserPassword);
        }
        return false;
    }

    public boolean userLoginFromMainPageCheckout(String setUserEmail, String setUserPassword) throws Exception {
        driver.findElement(burgerLoginButton).click(); // Кликнули по кнопке "Войти в аккаунт"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
        .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
        return userLogin(setUserEmail, setUserPassword);
    }
    public boolean userLoginFromUserArea(String setUserEmail, String setUserPassword) throws Exception {
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
        return userLogin(setUserEmail, setUserPassword);
    }
    public boolean userLoginFromRegistration(String setUserEmail, String setUserPassword) throws Exception {
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        driver.findElement(userRegistration).click(); // Кликнули по ссылке "Зарегистрироваться"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления ссылки "Войти"
                .until(ExpectedConditions.visibilityOfElementLocated(userLogin));
        driver.findElement(userLogin).click(); // Кликнули по ссылке "Войти"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
        return userLogin(setUserEmail, setUserPassword);
    }
    public boolean userLoginFromPasswordReset(String setUserEmail, String setUserPassword) throws Exception {
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        driver.findElement(userPasswordReset).click(); // Кликнули по ссылке "Восстановить пароль"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления ссылки "Войти"
                .until(ExpectedConditions.visibilityOfElementLocated(userLogin));
        driver.findElement(userLogin).click(); // Кликнули по ссылке "Войти"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
        return userLogin(setUserEmail, setUserPassword);
    }
    public boolean userLogin(String setUserEmail, String setUserPassword) throws Exception {
        setNameField(setUserEmail);
        setPasswordField(setUserPassword);
        clickButtonLogin();
        return getLoginStatus();
    }
    public void userLogout(){
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(3)) // Ждем 2 секунды до появления кнопки профиль
                .until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
        driver.findElement(buttonExit).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Step("Передача email пользователя {setUserEmail} в поле \"Email\"")
    public void setNameField(String setUserEmail){
        driver.findElement(fieldEmailLogin).isEnabled();
        driver.findElement(fieldEmailLogin).clear();
        driver.findElement(fieldEmailLogin).sendKeys(setUserEmail);
    }
    @Step("Передача пароля пользователя {setUserPassword} в поле \"Пароль\"")
    public void setPasswordField(String setUserPassword){
        driver.findElement(fieldPasswordLogin).isEnabled();
        driver.findElement(fieldPasswordLogin).clear();
        driver.findElement(fieldPasswordLogin).sendKeys(setUserPassword);
    }
    @Step("Жмем кнопку \"Войти\"")
    public void clickButtonLogin(){
        driver.findElement(buttonEnter).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Step("Проверяем, что вход выполнен")
    public boolean getLoginStatus() throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                    .until(ExpectedConditions.visibilityOfElementLocated(burgerCheckout));
            driver.findElement(burgerCheckout).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}