package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.PageLoadTimePage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageLoadTimeTest extends seleniumBase {

    // Record the start time in milliseconds
    @Test
    public void testPageLoadTime() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExtentTest test = extent.createTest("Page Load Time Test", "Testing the page load time performance");
        PageLoadTimePage pageLoadTimePage = new PageLoadTimePage(driver);
        long loadTime = pageLoadTimePage.getPerformance(startTime);
        if (loadTime <= 3) {
            test.pass("Page load time is within acceptable range: " + loadTime + "ms");
        } else {
            test.fail("Page load time is too high: " + loadTime + "ms");
        }
        System.out.println(loadTime);
        assertTrue( loadTime < 3,"Page load time is too high");
    }
}
