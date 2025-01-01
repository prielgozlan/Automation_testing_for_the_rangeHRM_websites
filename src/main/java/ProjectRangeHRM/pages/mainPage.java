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
        PageFactory.initElements(driver , this);
    }
    public String checkListOfThePages() throws InterruptedException {
        for(int i = 0 ; i < listPage.size() ; i++){
            if(!Objects.equals(listPage.get(i).getText(), "Maintenance")){
                listPage.get(i).click();
                if(Objects.equals(listPage.get(i).getText(), namePage.getText())){
                    System.out.println(listPage.get(i).getText()+": "+ "The page does match the title in the page.");

                }
                else {
                    System.out.println(listPage.get(i).getText()+": " + "The page does not match the title in the page.");
                    return listPage.get(i).getText()+": " + "The page does not match the title in the page.";
                }
            }
        }
        return "The page does match the title in the page.";
    }
    public String searchPage() throws InterruptedException {
        enterNamePage.sendKeys("pim");
        if(!Objects.equals(findPage.get(1).getText(), "")){
            System.out.println("result for search : "+findPage.get(1).getText());
            return "result for search : "+findPage.get(1).getText();
        }
        else{
            System.out.println("not found nothing");
            return "not found nothing";
        }
    }
    public String logout() throws InterruptedException {
        profileMenu.click();
        logoutButton.click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/auth/login")){
            System.out.println("Logged out successfully");
            return "Logged out successfully";
        }
        else {
            System.out.println("Did not log out successfully");
            return "Did not log out successfully";
        }
    }
    public String Responsive() throws InterruptedException {
        PageLoadTimePage PageLoadTimePage = new PageLoadTimePage(driver);
        long startTimeDesktop = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        long loadTimeDesktop = PageLoadTimePage.getPerformance(startTimeDesktop);
        if (loadTimeDesktop > 3000) {
            return "Page load time is too high in Desktop";
        }
        long startTimeTablet = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(768, 1024));
        long loadTimeTablet  = PageLoadTimePage.getPerformance(startTimeTablet);
        if (loadTimeTablet > 3000) {
            return "Page load time is too high in Tablet";
        }
        long startTimeMobile = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(375, 667));
        long loadTimeMobile = PageLoadTimePage.getPerformance(startTimeMobile);
        if (loadTimeMobile > 3000) {
            return "Page load time is too high in Mobile";
        }
        return "everything pass ok";
    }
    public String linkValidation(){
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            int statusCode = checkLinkStatus(url);
            if (statusCode != 200){
                System.out.println("Error number : "+ statusCode +" "+url);
                return "Error number : "+ statusCode +" "+url;
            }
        }
        return "all links pass ok";
    }
     public static int checkLinkStatus(String url) {
            try {
                URL link = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                return connection.getResponseCode();
            } catch (IOException e) {
                return 404;
            }
        }
}
