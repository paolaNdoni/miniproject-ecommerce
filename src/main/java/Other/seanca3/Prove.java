package Other.seanca3;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Prove {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(" https://ecommerce.tealiumdemo.com/");
        WebElement radioButton = driver.findElement(By.id("privacy_pref_optin"));
        radioButton.click();
        WebElement submit = driver.findElement(By.id("consent_prompt_submit"));
        submit.click();
        Thread.sleep(2000);
        WebElement account = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
        account.click();
        Thread.sleep(2000);
        WebElement register = driver.findElement(By.xpath("//a[@title='Register']"));
        register.click();
        Thread.sleep(2000);
        String title = driver.getTitle();
        String expectedTitle = "Create New Customer Account";
        Assert.assertEquals(expectedTitle, title);
        WebElement name = driver.findElement(By.xpath("//input[@id='firstname']"));
        name.sendKeys("John");
        WebElement middle = driver.findElement(By.cssSelector("input[id='middlename']"));
        middle.sendKeys("Doe");
        WebElement lastname = driver.findElement(By.cssSelector("input[id='lastname']"));
        lastname.sendKeys("Smith");
        WebElement email = driver.findElement(By.cssSelector("input[id='email_address']"));
        email.sendKeys("john.smith099866@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
        password.sendKeys("1234567");
        WebElement confirm = driver.findElement(By.cssSelector("input[name='confirmation']"));
        String confimedPass= password.getAttribute("value");
        confirm.sendKeys(confimedPass);
        WebElement submit2 = driver.findElement(By.xpath("//button[@title='Register']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submit2);
        Thread.sleep(500);  // short pause to let browser catch up
        submit2.click();
        try{
        List<WebElement> itemContainers = driver.findElements(By.cssSelector("li.item.last"));

        for (WebElement item : itemContainers) {
            // Find all <a> with class 'swatch-link has-image' inside this item
            List<WebElement> swatches = item.findElements(By.cssSelector("a.swatch-link.has-image"));

            for (WebElement swatch : swatches) {
                try {
                    // Try to find the closest parent li with class 'option-black'
                    WebElement parentLi = swatch.findElement(By.xpath("./ancestor::li[contains(@class,'option-black')]"));
                    String borderColor = swatch.getCssValue("border-bottom-color").trim();

                    if (parentLi != null && parentLi.getAttribute("class").contains("option-black")) {
                        // This swatch is inside black option li — should have blue border
                        Assert.assertEquals("Swatch inside black option missing blue border",
                                "rgb(51, 153, 204)", borderColor);
                        System.out.println("PASS: Swatch inside black option has correct blue border");
                    } else {
                        // This else will practically never happen since parentLi found,
                        // but we keep for logic clarity
                        Assert.assertNotEquals("Swatch outside black option has blue border",
                                "rgb(51, 153, 204)", borderColor);
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    // No parent with 'option-black' class found
                    String borderColor = swatch.getCssValue("border-bottom-color").trim();
                    Assert.assertNotEquals("Swatch outside black option has blue border",
                            "rgb(51, 153, 204)", borderColor);
                    System.out.println("PASS: Swatch outside black option has no blue border");
                }
            }
        }

    } catch (AssertionError e) {
        System.err.println("ASSERTION FAILED: " + e.getMessage());
    } finally {
        // Close browser
        driver.quit();
    }
}


    }

