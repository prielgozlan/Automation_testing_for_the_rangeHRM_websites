package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
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

public class loginTest {

    private seleniumBase base;
    private ChromeDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private loginPage loginPage;

    @Before
    public void setUp() throws InterruptedException {
        String timesNow = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String myPath = System.getProperty("user.dir") + "/src/main/resources/loginTest_" + timesNow + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(myPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        test = extent.createTest("Login Page Test", "Testing login functionalities including invalid credentials, empty login, and password reset.");
        base = new seleniumBase();
        driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new loginPage(driver);
    }
    @Test
    public void checkLoginWithUserNameWrong() throws InterruptedException {
        String checkLoginWithUserNameWrong = loginPage.checkLoginWithUserNameWrong();
        checkLoginSuccessful(checkLoginWithUserNameWrong);
        test = extent.createTest("Invalid Username Test", "Testing login with an invalid username.");
    }
    @Test
    public void checkLoginWithPasswordWrong(){
        String checkLoginWithPasswordWrong = loginPage.checkLoginWithPasswordWrong();
        checkLoginSuccessful(checkLoginWithPasswordWrong);
        test = extent.createTest("Invalid Password Test", "Testing login with an invalid password.");
    }
    @Test
    public void checkLoginEmpty(){
       String checkLoginEmpty = loginPage.checkLoginEmpty();
       checkLoginSuccessful(checkLoginEmpty);
        test = extent.createTest("Empty Login Test", "Testing login with empty username and password.");
    }
    public void checkLoginSuccessful(String checkUserOrPassword){
        if(Objects.equals(checkUserOrPassword, "Login successful")){
            test.pass(checkUserOrPassword);
        }else {
            test.fail(checkUserOrPassword);
        }
        assertTrue(checkUserOrPassword, !Objects.equals(checkUserOrPassword, "Login successful"));
    }
    @Test
    public void forgetYourPassword(){
        String forgetYourPassword = loginPage.forgetYourPassword();
        test = extent.createTest("Forgot Password Test", "Testing the forgot password functionality.");
        if (Objects.equals(forgetYourPassword, "Not reset Password link sent successfully")){
            test.fail("Not reset Password link sent successfully");
        } else if (Objects.equals(forgetYourPassword, "Reset Password link sent successfully")) {
            test.pass("Reset Password link sent successfully");
        }
        assertTrue(forgetYourPassword, Objects.equals(forgetYourPassword, "Reset Password link sent successfully"));
    }
    @After
    public void tearDown(){
        base.seleniumClose(driver);
        extent.flush();
    }
}