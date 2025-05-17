package Other.seanca2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exc3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qbek.github.io/selenium-exercises/en/radio_buttons.html");
        WebElement radioButton = driver.findElement(By.xpath("//input[@value='rmffm']"));
        WebElement radioButton2 = driver.findElement(By.xpath("//input[@value='radiozet']"));
        WebElement radioButton3 = driver.findElement(By.xpath("//input[@value='polskieradio']"));

        radioButton.click();
        if (radioButton.isSelected()) {
            WebElement redirect = driver.findElement(By.xpath("//a[contains(text(),'Web page')]"));
            redirect.click();
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("radio_buttons")) {
                System.out.println("Successfully redirected to: " + currentUrl);
            } else {
                System.out.println("Redirection failed.");
            }
        }
        driver.navigate().back();
        Thread.sleep(2000);

        radioButton2.click();
        if(radioButton2.isSelected()){
            WebElement redirect = driver.findElement(By.xpath("//a[@href='https://www.radiozet.pl']"));
            redirect.click();
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("radio_buttons")) {
                System.out.println("Successfully redirected to: " + currentUrl);
            }
            else {
                System.out.println("Redirection failed.");
            }
        }
        driver.navigate().back();
        Thread.sleep(2000);
        radioButton3.click();
        if(radioButton3.isSelected()){
            WebElement redirect = driver.findElement(By.xpath("//a[@href='https://www.polskieradio.pl']"));
            redirect.click();
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("radio_buttons")) {
                System.out.println("Successfully redirected to: " + currentUrl);
            }
            else {
                System.out.println("Redirection failed.");
            }
        }
        driver.quit();
        driver.close();


    }
}
