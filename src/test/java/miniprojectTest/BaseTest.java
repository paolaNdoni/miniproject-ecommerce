package miniprojectTest;

import InternshipProject.Utilities.BaseInformation;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void setUp() {
        BaseInformation.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        BaseInformation.quit();
    }
}
