package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.pages.pimPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class pimTest extends seleniumBase {

    private ExtentTest test;
    private static pimPage pimPage;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        pimPage = new pimPage(driver);
    }
    @Test
    public void addUser() throws InterruptedException {
        driver.navigate().refresh();
        String addUser = pimPage.addUser();
        test = extent.createTest("Add User Test", "Testing the addition of a new user to the PIM module");
        tampToSearch(addUser , test);
    }
    @Test
    public void searchEmployee() throws InterruptedException {
        driver.navigate().refresh();
        String searchEmployee = pimPage.searchEmployee("user");
        test = extent.createTest("Search Employee Test", "Testing employee search functionality in the PIM module");
        tampToSearch(searchEmployee , test);
    }
    public void tampToSearch(String name , ExtentTest test){
        if (Objects.equals(name, "No Records Found")) {
            test.fail("Adding a new user failed.");
        } else if (Objects.equals(name, "Records Found")) {
            test.pass("Adding a new user was successful.");
        }
        assertTrue(Objects.equals(name, "Records Found"),"Adding a new user failed.");
    }
}
