package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.pages.pimPage;
import ProjectRangeHRM.seleniumBase;
import org.openqa.selenium.chrome.ChromeDriver;

public class pimTest {
    public static void main(String[] args) throws InterruptedException {
        seleniumBase base = new seleniumBase();
        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        pimPage pimPage = new pimPage(driver);
//        pimPage.addUser();
//        pimPage.searchEmployee("user");

        base.seleniumClose(driver);
    }
}
