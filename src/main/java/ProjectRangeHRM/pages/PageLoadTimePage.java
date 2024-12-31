package ProjectRangeHRM.pages;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
public class PageLoadTimePage {

    private ChromeDriver driver;
    public PageLoadTimePage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public long getPerformance (long startTime){
        long loadTime = System.currentTimeMillis() - startTime;
        loadTime /= 1000;
        if (loadTime > 3){
            System.out.println("The site does not load in a timely manner.");
        }
        else {
            System.out.println("The site does load in a timely manner.");
        }

        return loadTime;
    }
}
