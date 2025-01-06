package ProjectRangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class pimPage {

    @FindBy(className = "oxd-main-menu-item-wrapper")
    List<WebElement> listOfNamePage;
    @FindBy(xpath = "//div[@class='orangehrm-header-container']//button[@class='oxd-button oxd-button--medium oxd-button--secondary' and contains(., 'Add')]")
    WebElement enterAdd;
    @FindBy(name = "firstName")
    WebElement firstName;
    @FindBy(name = "lastName")
    WebElement lastName;
    @FindBy(className = "orangehrm-left-space")
    WebElement enterSave;
    @FindBy(className = "orangehrm-edit-employee-name")
    WebElement nameSave;
    @FindBy(css = ".oxd-autocomplete-text-input input")
    WebElement nameEmp;
    @FindBy(className = "orangehrm-left-space")
    WebElement enterSearch;
    @FindBy(css = "div.orangehrm-horizontal-padding span.oxd-text")
    WebElement findEmployee;

    private ChromeDriver driver;

    public pimPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver , this);
    }
    public String searchEmployee(String nameEmployee) throws InterruptedException {
        listOfNamePage.get(1).click();
        nameEmp.sendKeys(nameEmployee);
        enterSearch.click();
        sleep(5000);
        if(Objects.equals(findEmployee.getText(), "No Records Found")){
            System.out.println("No Records Found");
            return "No Records Found";
        }
        else {
            System.out.println("Records Found");
            return "Records Found";
        }
    }
    public String addUser() throws InterruptedException {
        listOfNamePage.get(1).click();
        enterAdd.click();
        String firstNameOfWorker = "priel";
        String lastNameOfWorker = "gozlan";
        firstName.sendKeys(firstNameOfWorker);
        lastName.sendKeys(lastNameOfWorker);
        enterSave.click();
        sleep(5000);
        listOfNamePage.get(1).click();
        String searchEmployee = searchEmployee(lastNameOfWorker);
        sleep(5000);
        return searchEmployee;
    }
}
