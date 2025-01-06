package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.adminPage;
import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class adminTest {

    private seleniumBase base;
    private ChromeDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private adminPage adminPage;

    @BeforeEach
    public void setUp() throws InterruptedException {
        String timesNow = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String myPath = System.getProperty("user.dir") + "/src/test/resources/adminTest_" + timesNow + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(myPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Admin Page Functionalities Test", "Testing user search and deletion functionalities in the Admin module");
        base = new seleniumBase();
        driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        adminPage = new adminPage(driver);
    }
    @Test
    public void searchNameUser() throws InterruptedException {
        String searchNameUser = adminPage.searchNameUser();
        test = extent.createTest("Search User Test", "Testing search for a user by name in the Admin module");
        if(Objects.equals(searchNameUser, "not find user")){
            test.fail("not find user");
        } else if (Objects.equals(searchNameUser, "find user")) {
            test.pass("find user");
        }
        assertTrue(searchNameUser, Objects.equals(searchNameUser, "find user"));
    }
    @Test
    public void deleteUser() throws InterruptedException {
        String deleteUser = adminPage.deleteUser();
        test = extent.createTest("Delete User Test", "Testing user deletion functionality in the Admin module");
        if(Objects.equals(deleteUser, "User deletion failed.")){
            test.fail("User deletion failed.");
        } else if (Objects.equals(deleteUser, "User deleted successfully.")) {
            test.pass("User deleted successfully.");
        }
        assertTrue(deleteUser, Objects.equals(deleteUser, "User deleted successfully."));
    }
    @AfterEach
    public void tearDown(){
        base.seleniumClose(driver);
        extent.flush();
    }
}