package InternshipProject.Pages;

import InternshipProject.Elements.HoverStyleElements;
import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.BasePageObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HoverStylePage {
    BasePageObject basePageObject = new BasePageObject();
    HoverStyleElements hoverStyleElements = new HoverStyleElements();
    public HoverStylePage(){
        PageFactory.initElements(BaseInformation.getDriver(), this);
    }
    public void hoverWomen(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, hoverStyleElements.women);
        basePageObject.getWebElementUtils()
                .moveMouseToElement(hoverStyleElements.women);
    }
    public void clickViewAll(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, hoverStyleElements.viewAll)
        .click();
    }
    public void hoverDress(){
        String beforeHover = basePageObject
                .getWebElementUtils().getCssValue(hoverStyleElements.dress, "border-color");

        basePageObject.getWebElementUtils()
                .scrollToElement(hoverStyleElements.dress);
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, hoverStyleElements.dress);
        basePageObject.getWebElementUtils()
                .moveMouseToElement(hoverStyleElements.dress);
        String afterHover = basePageObject.getWebElementUtils()
                .getCssValue(hoverStyleElements.dress, "border-color");
        Assert.assertNotEquals(beforeHover, "Border bottom color did not change on hover", afterHover);

    }
    public void hoverSale(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, hoverStyleElements.sale);
        basePageObject.getWebElementUtils()
                .moveMouseToElement(hoverStyleElements.sale);
    }
    public void clickViewAllSale(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, hoverStyleElements.viewAllSale)
                .click();
    }
    public void hoverMan(){
        basePageObject.getWebElementUtils()
                .moveMouseToElement(hoverStyleElements.man);
    }
    public void clickViewAllMen(){
        basePageObject.getWaitUtils()
                .waitForElementVisibleWithCustomTime(5000, hoverStyleElements.viewAllMen)
                .click();
    }

}
