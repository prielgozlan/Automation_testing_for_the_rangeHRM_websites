package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.seleniumBase;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class loginTest {
    public static void main(String[] args) throws InterruptedException {
        seleniumBase base = new seleniumBase();
        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
//        loginPage.checkLoginAdmin();
//        loginPage.checkLoginWithUserNameWrong();
//        loginPage.checkLoginWithPasswordWrong();
//        loginPage.checkLoginEmpty();
//        loginPage.forgetYourPassword();
        base.seleniumClose(driver);
    }
}
