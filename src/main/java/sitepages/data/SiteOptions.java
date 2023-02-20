package sitepages.data;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SiteOptions {
    private static final String testUserEmail = "testuserChrome25@yandex.ru";
    private static final String testUserPassword = "testuser25";
    private static final By fieldEmailLogin = By.xpath("//*[text()='Email']/following::input");
    //Локатор поля Пароль на странице логина
    private static final By fieldPasswordLogin = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Войти" на странице логина
    private static final By burgerCheckout = By.xpath("//button[text()='Оформить заказ']");
    private static final By userArea = By.xpath("//a[@href='/account']");
    //Локатор кнопки "Войти" на странице логина
    private static final By buttonEnter = By.xpath("//*[text()='Войти']");
    private static final By userProfileButton = By.xpath("//a[@href='/account/profile']");
    //Локатор кнопки "Выход" на странице аккаунта
    private static final By exitButton = By.xpath("//button[text()='Выход']");

    public SiteOptions() {
    }
    public static String getTestUserEmail() {
        return testUserEmail;
    }

    public static String getTestUserPassword() {
        return testUserPassword;
    }

    @Step("Логинимся в личный кабинет")

    public static void getLoginUserAccount(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления поля ввода Email
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmailLogin));
        driver.findElement(fieldEmailLogin).clear();
        driver.findElement(fieldEmailLogin).sendKeys(testUserEmail);
        driver.findElement(fieldPasswordLogin).clear();
        driver.findElement(fieldPasswordLogin).sendKeys(testUserPassword);
        driver.findElement(buttonEnter).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления кнопки "Оформить заказ"
                .until(ExpectedConditions.visibilityOfElementLocated(burgerCheckout));
    }
    @Step("Заходим в личный кабинет")
    public static void getUserAccount(WebDriver driver){
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(3)) // Ждем 2 секунды до появления кнопки профиль
                .until(ExpectedConditions.visibilityOfElementLocated(userProfileButton));
    }
    @Step("Жмем на кнопку \"Выход\"")
    public static void clickExitButton(WebDriver driver){
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }

}
