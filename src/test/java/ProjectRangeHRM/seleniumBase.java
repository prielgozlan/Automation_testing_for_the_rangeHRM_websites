package ProjectRangeHRM;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class seleniumBase {

    public static ChromeDriver driver;
    protected static ExtentReports extent;

    @BeforeAll
    public static void seleniumInit() throws InterruptedException {
        System.out.println("Starting...");

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/src/test/resources/TestReport_" + timestamp + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // אתחול של ה-ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");    //fix for chrome version 111
        options.addArguments("--start-maximized");
        options.setAcceptInsecureCerts(true);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterAll
    public static void seleniumClose(){
        System.out.println("close test ...");
        // שמירה של הדוח (לא מוחק אותו, אלא מוסיף אליו)
        extent.flush();
        driver.close();
    }
}
