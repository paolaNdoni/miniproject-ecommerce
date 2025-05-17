package Other.seanca1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Test2 {
 public static void main(String[] args) throws InterruptedException {
  WebDriverManager.chromedriver().setup();
  WebDriver driver = new ChromeDriver();
  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

  String url = "https://demoqa.com/automation-practice-form";
  driver.get(url);
  driver.manage().window().maximize();

  WebElement name = driver.findElement(By.id("firstName"));
  name.sendKeys("Paola");
//  Thread.sleep(1000);

  WebElement lastname = driver.findElement(By.id("lastName"));
  lastname.sendKeys("Ndoni");
//  Thread.sleep(1000);

  WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
  email.sendKeys("ndonipaola@gmail.com");
//  Thread.sleep(1000);

  WebElement gender = driver.findElement(By.xpath("//label[@for='gender-radio-2']"));
  gender.click();
//  Thread.sleep(1000);

  WebElement number = driver.findElement(By.id("userNumber"));
  number.sendKeys("0684441790");
//  Thread.sleep(1000);

  WebElement dateOfBirth = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateOfBirthInput")));
  dateOfBirth.sendKeys("30 Dec 2004");
//  Thread.sleep(1000);

  WebElement subjectsInput = driver.findElement(By.id("subjectsInput")); // Correct field to type subjects
  subjectsInput.sendKeys("Maths");
//  Thread.sleep(1000);

  WebElement hobbies = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-3']"));
  hobbies.click();
//  Thread.sleep(1000);

  WebElement address = driver.findElement(By.id("currentAddress"));
  address.sendKeys("123 seanca1.Main St");
  Thread.sleep(1000);

  WebElement state = driver.findElement(By.id("react-select-3-input"));
  state.sendKeys("NCR");
  Thread.sleep(1000);

  WebElement city = driver.findElement(By.id("react-select-4-input"));
  city.sendKeys("Delhi" );
  Thread.sleep(1000);

  WebElement submitButton = driver.findElement(By.id("submit"));
  submitButton.click();
  Thread.sleep(4000);
  driver.quit();
 }
}