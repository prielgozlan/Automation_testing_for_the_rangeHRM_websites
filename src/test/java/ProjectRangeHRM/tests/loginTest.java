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
        loginPage = new loginPage(driver);
    }
    @Test
    public void checkLoginWithUserNameWrong() throws InterruptedException {
        driver.navigate().refresh();
        String checkLoginWithUserNameWrong = loginPage.checkLoginWithUserNameWrong();
        test = extent.createTest("Invalid Username Test", "Testing login with an invalid username.");
        checkLoginSuccessful(checkLoginWithUserNameWrong , test);
    }
    @Test
    public void checkLoginWithPasswordWrong(){
        driver.navigate().refresh();
        String checkLoginWithPasswordWrong = loginPage.checkLoginWithPasswordWrong();
        test = extent.createTest("Invalid Password Test", "Testing login with an invalid password.");
        checkLoginSuccessful(checkLoginWithPasswordWrong , test);
    }
    @Test
    public void checkLoginEmpty(){
        driver.navigate().refresh();
       String checkLoginEmpty = loginPage.checkLoginEmpty();
        test = extent.createTest("Empty Login Test", "Testing login with empty username and password.");
       checkLoginSuccessful(checkLoginEmpty , test);
    }
    public void checkLoginSuccessful(String checkUserOrPassword , ExtentTest test){
        if(Objects.equals(checkUserOrPassword, "Login successful")){
            test.pass(checkUserOrPassword);
        }else {
            test.fail(checkUserOrPassword);
        }
        assertTrue(!Objects.equals(checkUserOrPassword, "Login successful"),checkUserOrPassword);
    }
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