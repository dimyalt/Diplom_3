package sitepages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStellarburgers {
    private static final String MAIN_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site/register";
    private static final String PASSWORD_RESET_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Локатор поля Email на странице логина
    private final By fieldEmailLogin = By.xpath("//*[text()='Email']/following::input");
    //Локатор поля Пароль на странице логина
    private final By fieldPasswordLogin = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Войти" на странице логина
    private final By buttonEnter = By.xpath("//*[text()='Войти']");
    private final By burgerCheckout = By.xpath("//button[text()='Оформить заказ']");
    private final By burgerLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By userArea = By.xpath("//a[@href='/account']");
    private final By userLogin = By.xpath("//a[@href='/login']");
    private final By buttonExit = By.xpath("//button[text()='Выход']");
    public LoginStellarburgers() {
    }
    public By getFieldEmailLogin() {
        return fieldEmailLogin;
    }

    public By getFieldPasswordLogin() {
        return fieldPasswordLogin;
    }

    public By getButtonEnter() {
        return buttonEnter;
    }

    public By getBurgerCheckout() {
        return burgerCheckout;
    }

    public void userLogout(WebDriver driver){
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(3)) // Ждем 2 секунды до появления кнопки профиль
                .until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
        driver.findElement(buttonExit).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    public void setUserLoginPoint(WebDriver driver, String loginPoint){
        switch (loginPoint) {
            case "Войти в аккаунт":
                loginFromMainPage(driver);
                break;
            case "Личный кабинет":
                loginFromUserAccount(driver);
                break;
            case "Регистрация":
                loginFromRegistrationPage(driver);
                break;
            case "Восстановление пароля":
                loginFromPasswordRestorePage(driver);
                break;
        }

    }

    public void loginFromMainPage(WebDriver driver) {
        driver.get(MAIN_PAGE_TEST_URL); //Переходим на главную страницу
        driver.findElement(burgerLoginButton).click(); // Кликнули по кнопке "Войти в аккаунт"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
    }
    public void loginFromUserAccount(WebDriver driver){
        driver.get(MAIN_PAGE_TEST_URL); //Переходим на главную страницу
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
    }
    public void loginFromRegistrationPage(WebDriver driver){
        driver.get(REGISTER_PAGE_TEST_URL); //Переходим на страницу регистрации
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления ссылки "Войти"
                .until(ExpectedConditions.visibilityOfElementLocated(userLogin));
        driver.findElement(userLogin).click(); // Кликнули по ссылке "Войти"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
    }
    public void loginFromPasswordRestorePage(WebDriver driver){
        driver.get(PASSWORD_RESET_PAGE_TEST_URL); //Переходим на страницу восстановления пароля
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления ссылки "Войти"
                .until(ExpectedConditions.visibilityOfElementLocated(userLogin));
        driver.findElement(userLogin).click(); // Кликнули по ссылке "Войти"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
    }
}