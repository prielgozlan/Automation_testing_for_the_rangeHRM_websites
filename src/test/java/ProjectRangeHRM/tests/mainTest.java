package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.pages.mainPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mainTest extends seleniumBase {

    private ExtentTest test;
    private static mainPage mainPage;

    @BeforeAll
    public static void setUp() throws InterruptedException {
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
        assertTrue(!Objects.equals(logout, "Did not log out successfully"),"Did not log out successfully.");
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
    }
    @Test
    public void linkValidation(){
        driver.navigate().refresh();
        String linkValidation = mainPage.linkValidation();
        test = extent.createTest("Link Validation Test", "Testing if all links are valid on the main page");
        if (Objects.equals(linkValidation, "all links pass ok")){
            test.pass("all links pass ok");
        }else {
            test.fail(linkValidation);
        }
        assertTrue(Objects.equals(linkValidation, "all links pass ok"),linkValidation);
    }

    @Test
    public void checkListOfThePages() throws InterruptedException {
        driver.navigate().refresh();
        String checkListOfThePages = mainPage.checkListOfThePages();
        test = extent.createTest("Page List Check Test", "Testing list of page titles and content matching");
        if(Objects.equals(checkListOfThePages, "The page does match the title in the page.")){
            test.pass(checkListOfThePages);
        }else {
            test.fail(checkListOfThePages);
        }
        assertTrue(!Objects.equals(checkListOfThePages, "The page does match the title in the page."),checkListOfThePages);
    }
    @Test
    public void Responsive() throws InterruptedException {
        driver.navigate().refresh();
        String Responsive = mainPage.Responsive();
        test = extent.createTest("Responsive Test", "Testing responsiveness of the main page");
        if(Objects.equals(Responsive, "everything pass ok")){
            test.pass(Responsive);
        }else {
            test.fail(Responsive);
        }
        assertTrue(Objects.equals(Responsive, "everything pass ok"),Responsive);
    }
    @Test
    public void searchPage() throws InterruptedException {
        driver.navigate().refresh();
        String searchPage = mainPage.searchPage();
        test = extent.createTest("Page Search Test", "Testing search functionality on the main page");
        if(Objects.equals(searchPage, "not found nothing")){
            test.fail("not found nothing pages");
        }else {
            test.pass(searchPage);
        }
        assertTrue(!Objects.equals(searchPage, "not found nothing"),searchPage);
    }
}

