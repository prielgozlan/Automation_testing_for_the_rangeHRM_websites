package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.pages.pimPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import static org.junit.Assert.assertTrue;

public class pimTest{

    private seleniumBase base;
    private ChromeDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private pimPage pimPage;

    @Before
    public void setUp() throws InterruptedException {
        String timesNow = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String myPath = System.getProperty("user.dir") + "/src/main/resources/pimTest_" + timesNow + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(myPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("PIM Page Test", "Testing user addition and search functionalities in PIM module");
        base = new seleniumBase();
        driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        pimPage = new pimPage(driver);
    }
    @Test
    public void addUser() throws InterruptedException {
        String addUser = pimPage.addUser();
       tampToSearch(addUser);
    }
    @Test
    public void searchEmployee() throws InterruptedException {
        String searchEmployee = pimPage.searchEmployee("user");
        tampToSearch(searchEmployee);
    }
    public void tampToSearch(String name){
        if (Objects.equals(name, "No Records Found")) {
            test.fail("Adding a new user failed.");
        } else if (Objects.equals(name, "Records Found")) {
            test.pass("Adding a new user was successful.");
        }
        assertTrue("Adding a new user failed.", Objects.equals(name, "Records Found"));
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
//import ProjectRangeHRM.pages.loginPage;
//import ProjectRangeHRM.pages.pimPage;
//import ProjectRangeHRM.seleniumBase;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class pimTest {
//    public static void main(String[] args) throws InterruptedException {
//        seleniumBase base = new seleniumBase();
//        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        loginPage loginPage = new loginPage(driver);
//        loginPage.checkLoginAdmin();
//        pimPage pimPage = new pimPage(driver);
//        pimPage.addUser();
//        pimPage.searchEmployee("user");
//
//        base.seleniumClose(driver);
//    }
//}




