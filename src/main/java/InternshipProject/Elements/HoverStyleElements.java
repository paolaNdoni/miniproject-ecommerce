package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HoverStyleElements {
    public HoverStyleElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//li[@class='level0 nav-1 first parent']")
    public WebElement women;
    @FindBy(xpath = "//a[@class='level1']")
    public WebElement viewAll;
    @FindBy(xpath = "//img[@id='product-collection-image-428']")
    public WebElement dress;
    @FindBy(xpath = "//li[@class='level0 nav-5 parent']")
    public WebElement sale;
    @FindBy(xpath = "//a[contains(text(), 'View All Sale')]")
    public WebElement viewAllSale;
    @FindBy(xpath = "//li[@class='level0 nav-2 parent']")
    public WebElement man;
    @FindBy(xpath = "(//a[@class='level1'])[2]")
    public WebElement viewAllMen;
}
