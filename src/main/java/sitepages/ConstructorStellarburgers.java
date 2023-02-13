package sitepages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorStellarburgers {
    private final WebDriver driver;
    private final By fieldEmailLogin = By.xpath("//*[text()='Email']/following::input");
    //Локатор поля Пароль на странице логина
    private final By fieldPasswordLogin = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Войти" на странице логина
    private final By burgerCheckout = By.xpath("//button[text()='Оформить заказ']");
    private final By userArea = By.xpath("//a[@href='/account']");
    private final By userProfileButton = By.xpath("//a[@href='/account/profile']");
    private final By buttonEnter = By.xpath("//*[text()='Войти']");
    private final By constructorLogo = By.xpath("//div/a[@href='/']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By exitButton = By.xpath("//button[text()='Выход']");
    private final By sectionBun = By.xpath("//span[text()='Булки']");
    private final By headerSectionBun = By.xpath("//h2[text()='Булки']");
    private final By sectionSauce = By.xpath("//span[text()='Соусы']");
    private final By headerSectionSauce = By.xpath("//h2[text()='Соусы']");
    private final By sectionStuffing = By.xpath("//span[text()='Начинки']");
    private final By headerSectionStuffing = By.xpath("//h2[text()='Начинки']");

    public ConstructorStellarburgers(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Логинимся и заходим в личный кабинет")
    public void getLoginUserAccount(String setUserEmail, String setUserPassword){
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
    @Step("Жмем на \"Конструктор\"")
    public void clickConstructorButton(String clickPoint){
        if (clickPoint.equals("pClass")){
            driver.findElement(constructorButton).click();
        }else{
            driver.findElement(constructorLogo).click();
        }
                new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Step("Проверяем, что переход выполнен")
    public boolean getConstructorStatus() throws Exception {
        try {
            driver.findElement(burgerCheckout).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    @Step("Жмем на кнопку \"Выход\"")
    public void clickExitButton(){
        driver.findElement(userArea).click(); // Кликнули по кнопке "Личный кабинет"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления кнопки профиль
                .until(ExpectedConditions.visibilityOfElementLocated(userProfileButton));
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Step("Жмем на кнопку \"Соусы\"")
    public boolean clickSauceButton(){
        driver.findElement(sectionSauce).click(); // Кликнули по кнопке "Соусы"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления заголовка
                .until(ExpectedConditions.visibilityOfElementLocated(headerSectionSauce));
        try {
            driver.findElement(headerSectionSauce).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    @Step("Жмем на кнопку \"Булки\"")
    public boolean clickBunButton(){
        driver.findElement(sectionStuffing).click(); // Кликнули по кнопке "Начинка"
        new WebDriverWait(driver, Duration.ofSeconds(3)) // Ждем 2 секунды до появления заголовка
                .until(ExpectedConditions.visibilityOfElementLocated(headerSectionStuffing));
        driver.findElement(sectionBun).click(); // Кликнули по кнопке "Начинка"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления заголовка
                .until(ExpectedConditions.visibilityOfElementLocated(headerSectionBun));
        try {
            driver.findElement(headerSectionBun).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    @Step("Жмем на кнопку \"Начинка\"")
    public boolean clickStuffingButton(){
        driver.findElement(sectionStuffing).click(); // Кликнули по кнопке "Начинка"
        new WebDriverWait(driver, Duration.ofSeconds(2)) // Ждем 2 секунды до появления заголовка
                .until(ExpectedConditions.visibilityOfElementLocated(headerSectionStuffing));
        try {
            driver.findElement(headerSectionStuffing).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean checkConstructorSectors(String sectorName){
        switch (sectorName) {
            case "Bun":
                return clickBunButton();
            case "Sauce":
                return clickSauceButton();
            case "Stuffing":
                return clickStuffingButton();
        }
        return false;
    }

}
