package Other.seanca1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {
    public static void main(String[] args) throws Throwable {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://www.google.com";
        driver.get(url);
        driver.manage().window().maximize();
        driver.navigate().to("http://qatechhub.com");
        String title = driver.getTitle();
        if (title.equals("Uptut | Corporate IT Training & Consulting Services")) {
            System.out.println("PASS");
        }else{
            System.out.println("FAIL");
        }
        Thread.sleep(2000);

        driver.navigate().to("http://facebook.com");
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl);
        System.out.println("The current url is " + currentUrl);
        Thread.sleep(2000);

        driver.navigate().back();
        String currentUrl2 = driver.getCurrentUrl();
        driver.get(currentUrl2);
        System.out.println("The current url is/BACK " + currentUrl2);
        Thread.sleep(2000);

        driver.navigate().forward();
        String currentUrl3 = driver.getCurrentUrl();
        driver.get(currentUrl3);
        System.out.println("The current url is/FORWARD " + currentUrl3);

        driver.navigate().refresh();
        driver.quit();
        driver.close();



    }
}
