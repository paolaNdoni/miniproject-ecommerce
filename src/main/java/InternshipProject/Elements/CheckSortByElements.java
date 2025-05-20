package InternshipProject.Elements;

import InternshipProject.Utilities.BaseInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckSortByElements {
    public CheckSortByElements() {
        PageFactory.initElements(BaseInformation.getDriver(), this);

    }

    @FindBy(css = "ul.products-grid > li.item")
    public List<WebElement> items;
    public static final By sortByLocator = By.xpath("//select[@title='Sort By']");
}
