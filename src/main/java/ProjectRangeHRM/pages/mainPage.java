package ProjectRangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.sleep;

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

    public void checkListOfThePages() throws InterruptedException {

        for(int i = 0 ; i < listPage.size() ; i++){
            if(!Objects.equals(listPage.get(i).getText(), "Maintenance")){
                listPage.get(i).click();
                if(Objects.equals(listPage.get(i).getText(), namePage.getText())){
                    System.out.println(listPage.get(i).getText()+": "+ "The page does match the title in the page.");
                }
                else {
                    System.out.println(listPage.get(i).getText()+": " + "The page does not match the title in the page.");
                }
            }
        }

    }
    public void searchPage() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for the page that you want:");
        String namePage = scanner.nextLine();
        enterNamePage.sendKeys(namePage);
        if(!Objects.equals(findPage.get(1).getText(), "")){
            System.out.println("result for search : "+findPage.get(1).getText());

        }
        else{
            System.out.println("not found nothing");

        }
    }
    public void logout() throws InterruptedException {
        profileMenu.click();
        logoutButton.click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/auth/login")){
            System.out.println("Logged out successfully");
        }
        else {
            System.out.println("Did not log out successfully");
        }
    }
    public void Responsive() throws InterruptedException {
        PageLoadTimePage PageLoadTimePage = new PageLoadTimePage(driver);
        long startTimeDesktop = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        PageLoadTimePage.getPerformance(startTimeDesktop);

        long startTimeTablet = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(768, 1024));
        PageLoadTimePage.getPerformance(startTimeTablet);

        long startTimeMobile = System.currentTimeMillis();
        driver.manage().window().setSize(new Dimension(375, 667));
        PageLoadTimePage.getPerformance(startTimeMobile);


    }






}
