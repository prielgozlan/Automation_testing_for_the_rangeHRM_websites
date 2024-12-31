package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.pages.mainPage;
import ProjectRangeHRM.seleniumBase;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class mainTest {
    public static void main(String[] args) throws InterruptedException {
        seleniumBase base = new seleniumBase();
        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        mainPage mainPage = new  mainPage(driver);
        mainPage.logout();
//        mainPage.Responsive();
//        mainPage.linkValidation();
//        mainPage.checkListOfThePages();
//        mainPage.searchPage();
        base.seleniumClose(driver);
    }
}
