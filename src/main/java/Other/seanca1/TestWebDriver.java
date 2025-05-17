package Other.seanca1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWebDriver {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String baseUrl = "https://www.guru99.com/introduction-to-selenium.html";
        driver.get(baseUrl);//hap driver dhe shkon te url e specifikuar
        String title = driver.getTitle();
        System.out.println("The title of the page is: " + title);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("The current url is" + currentUrl);
        if(currentUrl.equals(baseUrl)){
            System.out.println("The current url is the same as the baseUrl!");
        }
        else {
            System.out.println("The current url is not the same as the baseUrl");
        }

        driver.close();//mbyll tabin ose windowsin ne te cilin jemi ne ato momente
        driver.quit();//mbyll te gjitha tabet e hapura
    }
}
