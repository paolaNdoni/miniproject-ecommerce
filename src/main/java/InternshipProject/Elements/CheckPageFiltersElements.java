package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckPageFiltersElements {
    public CheckPageFiltersElements(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    @FindBy(xpath = "//img[@title='Black']")
    public WebElement black;
    @FindBy(xpath = "//a[span[contains(text(),'$0.00')] and span[contains(text(),'$99.99')]]")
    public WebElement priceRangeLink;


}
