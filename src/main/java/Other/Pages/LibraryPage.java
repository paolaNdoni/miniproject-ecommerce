package Other.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LibraryPage extends LibraryPageElements {
    private WebDriver driver;
    private WebDriverWait wait;

    public LibraryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickKatalog() {
        katalog = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='icon mb-4']")));
        katalog.click();
    }

    public void selectTitulliAndFill(String title) {
        titulliOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'indicatorContainer')])[2]")));
        scrollIntoView(titulliOption);
        titulliOption.click();

        secondDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'indicatorContainer')])[3]")));
        new Actions(driver).moveToElement(secondDropdown).click().perform();

        permban = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'fillon me')]")));
        scrollIntoView(permban);
        permban.click();

        titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Vendos elementin e kërkimit']")));
        titleInput.sendKeys(title);
    }

    public void setGjuha(String language) {
        gjuhaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Gjuha']/..//input")));
        gjuhaInput.sendKeys(language);

        gjuhaShqip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'option') and text()='" + language + "']")));
        gjuhaShqip.click();
    }

    public void selectCheckbox() {
        checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @class='form-check-input']")));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void search() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Kërko')]")));
        button.click();
    }

    public boolean verifyResultVisible(String title) {
        WebElement liber = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'katalogu-materiale') and .//h6[contains(text(), '" + title + "')]]")
        ));
        scrollIntoView(liber);
        return liber.isDisplayed();
    }

    public void openAuthor() {
        autori = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Dante Alighieri')]")));
        autori.click();
    }

    public void sortBooks() {
        sortButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Rendit')]")));
        sortButton.click();

        sortByDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Datë botimi ↓')]")));
        sortByDate.click();
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
//730$
