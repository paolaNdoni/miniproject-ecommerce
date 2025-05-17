package Other.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LibraryPageElements {
    @FindBy(xpath = "//img[@class='icon mb-4']")
    public WebElement katalog;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')])[2]")
    public WebElement titulliOption;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')])[3]")
    public WebElement secondDropdown;

    @FindBy(xpath = "//*[contains(text(), 'fillon me')]")
    public WebElement permban;

    @FindBy(xpath = "//input[@placeholder='Vendos elementin e kërkimit']")
    public WebElement titleInput;

    @FindBy(xpath = "//div[text()='Gjuha']/..//input")
    public WebElement gjuhaInput;

    @FindBy(xpath = "//div[contains(@class,'option') and text()='Shqip']")
    public WebElement gjuhaShqip;

    @FindBy(xpath = "//input[@type='checkbox' and @class='form-check-input']")
    public WebElement checkbox;

    @FindBy(xpath = "//button[contains(text(), 'Kërko')]")
    public WebElement searchButton;

    @FindBy(xpath = "//div[contains(text(),'Dante Alighieri')]")
    public WebElement autori;

    @FindBy(xpath = "//button[contains(text(),'Rendit')]")
    public WebElement sortButton;

    @FindBy(xpath = "//a[contains(text(),'Datë botimi ↓')]")
    public WebElement sortByDate;

}
