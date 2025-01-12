package ProjectRangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class mainPage {

    @FindBy(className = "oxd-main-menu-item-wrapper")
    List<WebElement> listPage;
    @FindBy(className = "oxd-topbar-header-breadcrumb-module")
    WebElement namePage;
    @FindBy(className = "oxd-input--active")
    WebElement enterNamePage;
    @FindBy(className = "oxd-main-menu")
    List<WebElement> findPage;
    @FindBy(xpath = "//i[contains(@class, 'userdropdown')]")
    WebElement profileMenu;
    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logoutButton;

    private ChromeDriver driver;

    public mainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver , this); // Initialize the page elements
    }

    // Test to check if the page title matches the page's name
    public String checkListOfThePages() throws InterruptedException {
        // Step 1: Loop through all the menu items in the list
        for(int i = 0; i < listPage.size(); i++){
            if(!Objects.equals(listPage.get(i).getText(), "Maintenance")){ // Skip "Maintenance"
                listPage.get(i).click(); // Click on the menu item

                // Step 2: Check if the page name matches the title displayed on the page
                if(Objects.equals(listPage.get(i).getText(), namePage.getText())){
                    System.out.println(listPage.get(i).getText() + ": " + "The page does match the title in the page.");
                } else {
                    System.out.println(listPage.get(i).getText() + ": " + "The page does not match the title in the page.");
                    return listPage.get(i).getText() + ": " + "The page does not match the title in the page.";
                }
            }
        }
        return "The page does match the title in the page."; // All pages match title
    }

    // Test for searching a page from the list
    public String searchPage() throws InterruptedException {
        // Step 1: Enter "pim" in the search field
        enterNamePage.sendKeys("pim");

        // Step 2: Check if any search results are found
        if(!Objects.equals(findPage.get(1).getText(), "")){
            System.out.println("result for search: " + findPage.get(1).getText());
            return "result for search: " + findPage.get(1).getText();
        } else {
            System.out.println("not found nothing");
            return "not found nothing";
        }
    }

    // Test for logging out of the system
    public String logout() throws InterruptedException {
        // Step 1: Click on the profile menu to open the dropdown
        profileMenu.click();

        // Step 2: Click on the logout button
        logoutButton.click();

        // Step 3: Check the current URL to confirm logout was successful
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/auth/login")){
            System.out.println("Logged out successfully");
            return "Logged out successfully";
        } else {
            System.out.println("Did not log out successfully");
            return "Did not log out successfully";
        }
    }

    // Test for responsive design (check page load time on different devices)
    public String Responsive() throws InterruptedException {
        PageLoadTimePage PageLoadTimePage = new PageLoadTimePage(driver);

        // Step 1: Test for Desktop
        long startTimeDesktop = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(1920, 1080)); // Set desktop resolution
        long loadTimeDesktop = PageLoadTimePage.getPerformance(startTimeDesktop);
        if (loadTimeDesktop > 3000) {
            return "Page load time is too high in Desktop";
        }

        // Step 2: Test for Tablet
        long startTimeTablet = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(768, 1024)); // Set tablet resolution
        long loadTimeTablet = PageLoadTimePage.getPerformance(startTimeTablet);
        if (loadTimeTablet > 3000) {
            return "Page load time is too high in Tablet";
        }

        // Step 3: Test for Mobile
        long startTimeMobile = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(375, 667)); // Set mobile resolution
        long loadTimeMobile = PageLoadTimePage.getPerformance(startTimeMobile);
        if (loadTimeMobile > 3000) {
            return "Page load time is too high in Mobile";
        }

        return "everything pass ok"; // All tests pass if load time is within acceptable limits
    }

    // Test for checking the status of all links on the page
    public String linkValidation(){
        // Step 1: Find all links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Step 2: Loop through each link and check its status code
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            int statusCode = checkLinkStatus(url); // Get HTTP status code for each link
            if (statusCode != 200) {
                System.out.println("Error number: " + statusCode + " " + url);
                return "Error number: " + statusCode + " " + url; // Return if any link has error status
            }
        }
        return "all links pass ok"; // All links are valid if no errors are found
    }

    // Helper method to check the status code of a URL
    public static int checkLinkStatus(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("HEAD"); // Use HEAD method to check the status
            connection.connect();
            return connection.getResponseCode(); // Return the response code
        } catch (IOException e) {
            return 404; // If there is an exception, assume 404 error
        }
    }
}
