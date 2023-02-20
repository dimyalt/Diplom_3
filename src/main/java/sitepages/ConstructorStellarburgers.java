package sitepages;
import org.openqa.selenium.By;


public class ConstructorStellarburgers {
    private static final String MAIN_PAGE_TEST_URL = "https://stellarburgers.nomoreparties.site";
    private final By burgerCheckout = By.xpath("//button[text()='Оформить заказ']");
    private final By constructorLogo = By.xpath("//div/a[@href='/']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By sectionBunBtn = By.xpath("//span[text()='Булки']");
    private final By resultSection = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]//span");
    private final By sectionSauceBtn = By.xpath(".//span[text()='Соусы']");
    private final By sectionStuffingBtn = By.xpath("//span[text()='Начинки']");

    public ConstructorStellarburgers() {

    }
    public By getBurgerCheckout() {
        return burgerCheckout;
    }
    public By getConstructorButton() {
        return constructorButton;
    }

    public By getConstructorLogo() {
        return constructorLogo;
    }
    public By getSectionBunBtn() {
        return sectionBunBtn;
    }
    public String getMainPageTestURL() {
        return MAIN_PAGE_TEST_URL;
    }

    public By getSectionSauceBtn() {
        return sectionSauceBtn;
    }

    public By getSectionStuffingBtn() {
        return sectionStuffingBtn;
    }

    public By getResultSection() {
        return resultSection;
    }

}
