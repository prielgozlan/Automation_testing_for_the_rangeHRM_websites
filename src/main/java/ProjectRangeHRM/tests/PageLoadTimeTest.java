package ProjectRangeHRM.tests;
import ProjectRangeHRM.pages.PageLoadTimePage;
import ProjectRangeHRM.seleniumBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertTrue;
public class PageLoadTimeTest {

    private seleniumBase base;
    private ChromeDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @Before
    public void setUp() throws InterruptedException {
        String timesNow = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String myPath = System.getProperty("user.dir") + "/src/main/resources/extentReport_" + timesNow + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(myPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Page Load Time Test", "Testing the page load time of HRM login page");
    }

    @Test
    public void testPageLoadTime() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        base = new seleniumBase();
        driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        PageLoadTimePage pageLoadTimePage = new PageLoadTimePage(driver);
        long loadTime = pageLoadTimePage.getPerformance(startTime);
        System.out.println(loadTime);
        if (loadTime > 3000) {
            test.pass("Page load time is within acceptable range: " + loadTime + "ms");
        } else {
            test.fail("Page load time is too high: " + loadTime + "ms");
        }
        assertTrue("Page load time is too high", loadTime >= 3000);
    }

    @After
    public void tearDown() {
        base.seleniumClose(driver);
        extent.flush();
    }
}


//גירסה ראשונה בלי junit and Extent Report
//package ProjectRangeHRM.tests;
//
//import ProjectRangeHRM.pages.PageLoadTimePage;
//import ProjectRangeHRM.seleniumBase;
//import org.openqa.selenium.chrome.ChromeDriver;
//public class PageLoadTimeTest {
//    public static void main(String[] args) throws InterruptedException {
//        seleniumBase base = new seleniumBase();
//        long startTime = System.currentTimeMillis();
//        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        PageLoadTimePage PageLoadTimePage = new PageLoadTimePage(driver);
//        PageLoadTimePage.getPerformance(startTime);
//        base.seleniumClose(driver);
//    }
//}