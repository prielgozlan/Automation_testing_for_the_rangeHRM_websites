package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.pages.mainPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class mainTest {

    private seleniumBase base;
    private ChromeDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private mainPage mainPage;

    @BeforeEach
    public void setUp() throws InterruptedException {
        String timesNow = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String myPath = System.getProperty("user.dir") + "/src/main/resources/mainTest_" + timesNow + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(myPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Main Page Functionalities Test", "Testing user management, logout, link validation, page search, and responsiveness on the Main Page");
        base = new seleniumBase();
        driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        mainPage = new mainPage(driver);
    }
    @Test
    public void logout() throws InterruptedException {
        String logout = mainPage.logout();
        test = extent.createTest("Logout Test", "Testing logout functionality");
        if(Objects.equals(logout, "Logged out successfully")){
            test.pass("Logged out successfully");
        } else if (Objects.equals(logout, "Did not log out successfully")) {
            test.fail("Did not log out successfully");
        }
        assertTrue("Did not log out successfully.", !Objects.equals(logout, "Did not log out successfully"));
    }
    @Test
    public void linkValidation(){
        String linkValidation = mainPage.linkValidation();
        test = extent.createTest("Link Validation Test", "Testing if all links are valid on the main page");
        if (Objects.equals(linkValidation, "all links pass ok")){
            test.pass("all links pass ok");
        }else {
            test.fail(linkValidation);
        }
        assertTrue(linkValidation, Objects.equals(linkValidation, "all links pass ok"));
    }
    @Test
    public void searchPage() throws InterruptedException {
        String searchPage = mainPage.searchPage();
        test = extent.createTest("Page Search Test", "Testing search functionality on the main page");
        if(Objects.equals(searchPage, "not found nothing")){
            test.fail("not found nothing pages");
        }else {
            test.pass(searchPage);
        }
        assertTrue(searchPage, !Objects.equals(searchPage, "not found nothing"));
    }
    @Test
    public void checkListOfThePages() throws InterruptedException {
        String checkListOfThePages = mainPage.checkListOfThePages();
        extent.createTest("Page List Check Test", "Testing list of page titles and content matching");
        if(Objects.equals(checkListOfThePages, "The page does match the title in the page.")){
            test.pass(checkListOfThePages);
        }else {
            test.fail(checkListOfThePages);
        }
        assertTrue(checkListOfThePages, !Objects.equals(checkListOfThePages, "The page does match the title in the page."));
    }
    @Test
    public void Responsive() throws InterruptedException {
        String Responsive = mainPage.Responsive();
        test = extent.createTest("Responsive Test", "Testing responsiveness of the main page");
        if(Objects.equals(Responsive, "everything pass ok")){
            test.pass(Responsive);
        }else {
            test.fail(Responsive);
        }
        assertTrue(Responsive, Objects.equals(Responsive, "everything pass ok"));
    }
    @AfterEach
    public void tearDown(){
        base.seleniumClose(driver);
        extent.flush();
    }
}

