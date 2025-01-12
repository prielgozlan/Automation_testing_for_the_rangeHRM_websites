package ProjectRangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Objects;

public class adminPage {

    // Locating the elements for page navigation using class name
    @FindBy(className = "oxd-main-menu-item-wrapper")
    List<WebElement> listOfNamePage;
    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active']")
    WebElement enterUserName;
    @FindBy(className = "orangehrm-left-space")
    WebElement clickSearch;
    @FindBy(css = "div.orangehrm-horizontal-padding span.oxd-text")
    WebElement findSearch;
    @FindBy(className = "oxd-table-card")
    List<WebElement> listOfName;
    @FindBy(className = "bi-pencil-fill")
    List<WebElement> listOfNameSecond;
    @FindBy(className = "bi-trash")
    List<WebElement> listOfNameUser;
    @FindBy(className = "orangehrm-button-margin")
    WebElement cleanUser;
    @FindBy(className = "oxd-input--active")
    WebElement nameWorker;

    private ChromeDriver driver;

    // Constructor to initialize the page elements
    public adminPage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes the page elements using the driver
    }

    // Method to search for a user by their name in the Admin page
    public String searchNameUser() throws InterruptedException {
        // Step 1: Click on the first item in the menu
        listOfNamePage.get(0).click();

        // Step 2: Get the first name from the user list and use it for search
        String nameUser = listOfName.get(0).getText().split("\n")[0];

        // Step 3: Enter the username in the search field and click search
        enterUserName.sendKeys(nameUser);
        clickSearch.click();

        // Step 4: Check if the search result shows "Record Found"
        if (findSearch.getText().contains("Record Found")) {
            System.out.println("find user");
            return "find user";  // Return success if user is found
        } else {
            System.out.println("not find user");
            return "not find user";  // Return failure if user is not found
        }
    }

    // Method to delete a user from the Admin page
    public String deleteUser() throws InterruptedException {
        // Step 1: Click on the first item in the menu
        listOfNamePage.get(0).click();

        // Step 2: Get the third user's name and store it
        String nameUser = listOfName.get(2).getText().split("\n")[0];

        // Step 3: Click on the delete (trash) icon for that user
        listOfNameUser.get(2).click();

        // Step 4: Click the clean (delete) button
        cleanUser.click();
        Thread.sleep(2000);  // Wait for deletion to complete

        // Step 5: Check if the user still exists in the list
        boolean isUserDeleted = true;
        for (WebElement element : listOfName) {
            if (Objects.equals(element.getText(), nameUser)) {
                isUserDeleted = false;  // User still exists, deletion failed
                break;
            }
        }

        // Step 6: Return success or failure based on user existence
        if (isUserDeleted) {
            System.out.println("User deleted successfully.");
            return "User deleted successfully.";  // Return success if user is deleted
        } else {
            System.out.println("User deletion failed.");
            return "User deletion failed.";  // Return failure if user still exists
        }
    }
}
