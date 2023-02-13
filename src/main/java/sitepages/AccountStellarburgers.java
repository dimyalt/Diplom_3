package sitepages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountStellarburgers {
    private final WebDriver driver;
    //Локатор кнопки "Выход" на странице аккаунта
    private final By exitButton = By.xpath("//button[text()='Выход']");
    //Локатор кнопки "Войти" на странице логина
    private final By buttonEnter = By.xpath("//*[text()='Войти']");
    private final By fieldEmailLogin = By.xpath("//*[text()='Email']/following::input");
    private final By burgerLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор поля Пароль на странице логина
    private final By fieldPasswordLogin = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Войти" на странице логина
    private final By burgerCheckout = By.xpath("//button[text()='Оформить заказ']");
    private final By userArea = By.xpath("//a[@href='/account']");
    private final By userProfileButton = By.xpath("//a[@href='/account/profile']");

    public AccountStellarburgers(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Логинимся и заходим в личный кабинет")
    public void getLoginUserAccount(String setUserEmail, String setUserPassword){
        driver.findElement(burgerLoginButton).click(); // Кликнули по кнопке "Войти в аккаунт"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
        driver.findElement(fieldEmailLogin).isEnabled();
        driver.findElement(fieldEmailLogin).clear();
        driver.findElement(fieldEmailLogin).sendKeys(setUserEmail);
        driver.findElement(fieldPasswordLogin).isEnabled();
        driver.findElement(fieldPasswordLogin).clear();
        driver.findElement(fieldPasswordLogin).sendKeys(setUserPassword);
        driver.findElement(buttonEnter).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления кнопки "Оформить заказ"
                .until(ExpectedConditions.visibilityOfElementLocated(burgerCheckout));
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления кнопки профиль
                .until(ExpectedConditions.visibilityOfElementLocated(userProfileButton));
    }
    @Step("Жмем на кнопку \"Выход\"")
    public void clickExitButton(){
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Step("Проверяем, что выход выполнен")
    public boolean getLogoutStatus() throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                    .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
