package ProjectRangeHRM.tests;

import ProjectRangeHRM.pages.adminPage;
import ProjectRangeHRM.pages.loginPage;
import ProjectRangeHRM.seleniumBase;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class adminTest extends seleniumBase {

    private ExtentTest test;
    private static adminPage adminPage;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        loginPage loginPage = new loginPage(driver);
        loginPage.checkLoginAdmin();
        adminPage = new adminPage(driver);
    }
    @Test
    public void searchNameUser() throws InterruptedException {
        driver.navigate().refresh();
        String searchNameUser = adminPage.searchNameUser();
        test = extent.createTest("Search User Test", "Testing search for a user by name in the Admin module");
        if(Objects.equals(searchNameUser, "not find user")){
            test.fail("not find user");
        } else if (Objects.equals(searchNameUser, "find user")) {
            test.pass("find user");
        }
        assertTrue(Objects.equals(searchNameUser, "find user"), searchNameUser);

    }
    @Test
    public void deleteUser() throws InterruptedException {
        driver.navigate().refresh();
        String deleteUser = adminPage.deleteUser();
        test = extent.createTest("Delete User Test", "Testing user deletion functionality in the Admin module");
        if(Objects.equals(deleteUser, "User deletion failed.")){
            test.fail("User deletion failed.");
        } else if (Objects.equals(deleteUser, "User deleted successfully.")) {
            test.pass("User deleted successfully.");
        }
        assertTrue(Objects.equals(deleteUser, "User deleted successfully."), deleteUser);
    }
}