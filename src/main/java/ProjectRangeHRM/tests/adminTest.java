package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.adminPage;
import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.seleniumBase;
import org.openqa.selenium.chrome.ChromeDriver;

public class adminTest {
    public static void main(String[] args) throws InterruptedException {
        seleniumBase base = new seleniumBase();
        ChromeDriver driver = base.seleniumInit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        adminPage adminPage = new adminPage(driver);
//        adminPage.searchNameUser();
//        adminPage.deleteUser();
//        adminPage.updateName();

        base.seleniumClose(driver);
    }
}
