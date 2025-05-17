package Other.PROJECT.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WebDriverUtils {
    BaseInformation baseInformation;

    public WebDriverUtils() {
    }

    public static List<String> getBrowserLogs(BaseInformation baseInformation) {
        LogEntries logEntries = null;

        try {
            logEntries = BaseInformation.getDriver().manage().logs().get("browser");
        } catch (NoSuchSessionException var3) {
            return new LinkedList();
        }

        return (List)logEntries.getAll().stream().map((l) -> l.getMessage()).collect(Collectors.toList());
    }

    public static void maximizeWindowOfDriver(BaseInformation baseInformation) {
        BaseInformation.getDriver().manage().window().maximize();
    }

    public static void closeDriver(BaseInformation baseInformation) {
        if (BaseInformation.getDriver() != null) {
            try {
                BaseInformation.getDriver().quit();
            } catch (WebDriverException exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

    public static void setImplicityTimeout(BaseInformation baseInformation, long valueInSecond) {
        BaseInformation.getDriver().manage().timeouts().implicitlyWait(valueInSecond, TimeUnit.SECONDS);
    }

    public static void switchToTab(BaseInformation baseInformation, int numberOfTab) {
        ArrayList<String> newTb = new ArrayList(BaseInformation.getDriver().getWindowHandles());
        BaseInformation.getDriver().switchTo().window((String)newTb.get(numberOfTab));
        WaitUtils.waitFor(5000L);
    }

    public static void clearAllCookies(BaseInformation baseInformation) {
        BaseInformation.getDriver().manage().deleteAllCookies();
    }

    public static void setResolution(BaseInformation baseInformation, String resolution) {
        int widht = Integer.parseInt(resolution.substring(0, resolution.indexOf("x")));
        int height = Integer.parseInt(resolution.substring(resolution.indexOf("x") + 1));
        BaseInformation.getDriver().manage().window().setSize(new Dimension(widht, height));
    }
}
