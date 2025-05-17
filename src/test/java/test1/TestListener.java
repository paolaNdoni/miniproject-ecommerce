package test1;

import InternshipProject.Utilities.BaseInformation;
import InternshipProject.Utilities.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class TestListener implements org.testng.ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseInformation.getDriver();
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
        System.out.println("Screenshot saved to: " + screenshotPath);
    }
}
