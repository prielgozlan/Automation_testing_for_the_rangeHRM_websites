package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class loginTest extends seleniumBase {

    private ExtentTest test;
    private static loginPage loginPage;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        // Initialize the loginPage object before running tests
        loginPage = new loginPage(driver);
    }

    // Test login functionality with an invalid username wrong
    @Test
    public void checkLoginWithUserNameWrong() throws InterruptedException {
        driver.navigate().refresh();
        String checkLoginWithUserNameWrong = loginPage.checkLoginWithUserNameWrong();
        test = extent.createTest("Invalid Username Test", "Testing login with an invalid username.");
        checkLoginSuccessful(checkLoginWithUserNameWrong , test);
    }

    // Test login functionality with an invalid password wrong
    @Test
    public void checkLoginWithPasswordWrong(){
        driver.navigate().refresh();
        String checkLoginWithPasswordWrong = loginPage.checkLoginWithPasswordWrong();
        test = extent.createTest("Invalid Password Test", "Testing login with an invalid password.");
        checkLoginSuccessful(checkLoginWithPasswordWrong , test);
    }

    // Test login functionality with empty username and password fields
    @Test
    public void checkLoginEmpty(){
        driver.navigate().refresh();
       String checkLoginEmpty = loginPage.checkLoginEmpty();
        test = extent.createTest("Empty Login Test", "Testing login with empty username and password.");
       checkLoginSuccessful(checkLoginEmpty , test);
    }

    // Common validation for login tests
    // Checks if the login result is successful or failed
    public void checkLoginSuccessful(String checkUserOrPassword , ExtentTest test){
        if(Objects.equals(checkUserOrPassword, "Login successful")){
            test.pass(checkUserOrPassword);
        }else {
            test.fail(checkUserOrPassword);
        }
        assertTrue(!Objects.equals(checkUserOrPassword, "Login successful"),checkUserOrPassword);
    }

    // Test the "Forgot Password" functionality
    @Test
    public void forgetYourPassword(){
        driver.navigate().refresh();
        String forgetYourPassword = loginPage.forgetYourPassword();
        test = extent.createTest("Forgot Password Test", "Testing the forgot password functionality.");
        if (Objects.equals(forgetYourPassword, "Not reset Password link sent successfully")){
            test.fail("Not reset Password link sent successfully");
        } else if (Objects.equals(forgetYourPassword, "Reset Password link sent successfully")) {
            test.pass("Reset Password link sent successfully");
        }
        assertTrue(Objects.equals(forgetYourPassword, "Reset Password link sent successfully"),forgetYourPassword);
    }
}