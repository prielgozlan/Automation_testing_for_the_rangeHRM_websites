package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.PageLoadTimePage;
import ProjectRangeHRM.seleniumBase;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageLoadTimeTest {
    public static void main(String[] args) throws InterruptedException {
        seleniumBase base = new seleniumBase();
        long startTime = System.currentTimeMillis();
        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        PageLoadTimePage PageLoadTimePage = new PageLoadTimePage(driver);
        PageLoadTimePage.getPerformance(startTime);
        base.seleniumClose(driver);
    }
}
