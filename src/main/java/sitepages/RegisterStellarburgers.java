package sitepages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterStellarburgers {
    private final WebDriver driver;
    private final By fieldNameRegistration = By.xpath("//*[text()='Имя']/following::input");
    //Локатор поля ввода "Email" на странице регистрации
    private final By fieldEmailRegistration = By.xpath("//*[text()='Email']/following::input");
    //Локатор поля ввода "Пароль" на странице регистрации
    private final By fieldPasswordRegistration = By.xpath("//*[text()='Пароль']/following::input");
    //Локатор кнопки "Зарегистрироваться" на странице регистрации
    private final By buttonRegistration = By.xpath("//*[text()='Зарегистрироваться']");
    //Локатор сообщения о некорректном пароле на странице регистрации
    private final By messageErrorPassword = By.xpath("//*[text()='Некорректный пароль']");
    public RegisterStellarburgers(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Передача имени пользователя {setUserName} в поле \"Имя\"")
    public void setNameField(String setUserName){
        driver.findElement(fieldNameRegistration).isEnabled();
        driver.findElement(fieldNameRegistration).clear();
        driver.findElement(fieldNameRegistration).sendKeys(setUserName);
    }
    @Step("Передача email пользователя {setUserEmail} в поле \"Email\"")
    public void setEmailField(String setUserEmail){
        driver.findElement(fieldEmailRegistration).isEnabled();
        driver.findElement(fieldEmailRegistration).clear();
        driver.findElement(fieldEmailRegistration).sendKeys(setUserEmail);
    }
    @Step("Передача пароля пользователя {setUserPassword} в поле \"Пароль\"")
    public void setPasswordField(String setUserPassword){
        driver.findElement(fieldPasswordRegistration).isEnabled();
        driver.findElement(fieldPasswordRegistration).clear();
        driver.findElement(fieldPasswordRegistration).sendKeys(setUserPassword);
    }
    @Step("Жмем кнопку \"Зарегистрироваться\"")
    public void clickButtonRegistration(){
        driver.findElement(buttonRegistration).click();
        new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Step("Получаем результат нажатия кнопки")
    public boolean getRegistrationStatus() throws Exception {
        try {
            driver.findElement(messageErrorPassword).isDisplayed();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}
