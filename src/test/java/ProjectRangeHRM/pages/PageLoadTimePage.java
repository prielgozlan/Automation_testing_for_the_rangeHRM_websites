package ProjectRangeHRM.pages;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class PageLoadTimePage {

    private ChromeDriver driver;

    // Constructor to initialize the ChromeDriver and page elements
    public PageLoadTimePage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize page elements
    }

    // Method to calculate and check the page load time
    public long getPerformance(long startTime){
        // Step 1: Calculate the load time by subtracting the start time from the current time
        long loadTime = System.currentTimeMillis() - startTime;

        // Step 2: Convert the load time from milliseconds to a simplified unit by dividing by 10
        loadTime /= 10;

        // Step 3: Check if the load time is greater than 3 seconds
        if (loadTime > 3){
            // If load time is greater than 3 seconds, print a message indicating the site is slow to load
            System.out.println("The site does not load in a timely manner.");
        }
        else {
            // If load time is within 3 seconds, print a message indicating the site loads quickly
            System.out.println("The site does load in a timely manner.");
        }

        // Step 4: Return the load time (in simplified units)
        return loadTime;
    }
}
