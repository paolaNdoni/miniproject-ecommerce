package Other.seanca1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test3 {
    public static void main(String[] args) throws Throwable {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://demo.nopcommerce.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("setTimeout(() => { debugger; }, 5000);");
        driver.manage().window().maximize();
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        WebElement registerForm = driver.findElement(By.xpath("//button[@class='button-1 register-button']"));
        registerForm.click();

        String title = driver.getTitle();
        System.out.println(title);

        WebElement gender = driver.findElement(By.id("gender-female"));
        gender.click();

        WebElement name = driver.findElement(By.id("FirstName"));
        name.sendKeys("Paola");

        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys("Ndoni");

        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys("test@gmail.com");

        WebElement company = driver.findElement(By.id("Company"));
        company.sendKeys("Test");

        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("123456");

        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys("123456");

        WebElement register = driver.findElement(By.id("register-button"));
        register.click();

        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='ico-logout']"));
        logoutButton.click();

        WebElement loginButton = driver.findElement(By.xpath("//a[@class='ico-login']"));
        loginButton.click();

        WebElement submit = driver.findElement(By.id("register-button"));
        submit.click();

        WebElement emailTry = driver.findElement(By.xpath("//input[@id='Email']"));
        emailTry.sendKeys("test@gmail.com");

        WebElement passwordTry = driver.findElement(By.xpath("//input[@id='Password']"));
        passwordTry.sendKeys("123456");

        WebElement loginTry= driver.findElement(By.xpath("//button[@class='button-1 login-button']"));
        loginTry.click();

        WebElement success = driver.findElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        if (success.isDisplayed()) {
            System.out.println("Login successful");
        }
        driver.quit();

    }
}
