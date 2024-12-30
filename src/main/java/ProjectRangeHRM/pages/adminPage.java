package ProjectRangeHRM.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class adminPage {

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
    public adminPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver , this);
    }
    public void searchNameUser() throws InterruptedException {
        listOfNamePage.get(0).click();
        String nameUser = listOfName.get(0).getText().split("\n")[0];
        enterUserName.sendKeys(nameUser);
        clickSearch.click();

        if (findSearch.getText().contains("Record Found")){
            System.out.println("find user");
        }
        else {
            System.out.println("not find user");
        }
    }
    public void deleteUser() throws InterruptedException {
        listOfNamePage.get(0).click();
        String nameUser = listOfName.get(2).getText().split("\n")[0];
        listOfNameUser.get(2).click();
        cleanUser.click();
        Thread.sleep(2000);
        boolean isUserDeleted = true;
        for (WebElement element : listOfName) {
            if (Objects.equals(element.getText(), nameUser)) {
                isUserDeleted = false;
                break;
            }
        }
        if (isUserDeleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User deletion failed.");
        }
    }
    public void updateName() throws InterruptedException {
        listOfNamePage.get(0).click();
        listOfNameSecond.get(1).click();
//        nameWorker.click();
        Actions actions = new Actions(driver);
        actions.doubleClick(nameWorker).perform();
        nameWorker.sendKeys(Keys.CONTROL + "a");


        actions.sendKeys(Keys.DELETE).perform();

        sleep(5000);







    }


}
