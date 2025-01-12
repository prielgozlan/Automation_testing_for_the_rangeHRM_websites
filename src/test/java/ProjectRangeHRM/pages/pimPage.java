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
        // Step 1: Click on the second item in the list of pages
        listOfNamePage.get(1).click();
        // Step 2: Enter the employee's name into the search input field
        nameEmp.sendKeys(nameEmployee);
        // Step 3: Click the search button
        enterSearch.click();
        // Step 4: Pause for 5 seconds to ensure the search results are loaded
        sleep(5000);
        // Step 5: Check if the "No Records Found" message appears
        if(Objects.equals(findEmployee.getText(), "No Records Found")){
            System.out.println("No Records Found");
            return "No Records Found";
        }
        else {
            // Step 6: If records are found, return a success message
            System.out.println("Records Found");
            return "Records Found";
        }
    }

    public String addUser() throws InterruptedException {
        // Step 1: Click on the second item in the list of pages to go to the PIM section
        listOfNamePage.get(1).click();
        // Step 2: Click the "Add" button to start adding a new user
        enterAdd.click();
        // Step 3: Enter the first name of the new user
        String firstNameOfWorker = "priel";
        firstName.sendKeys(firstNameOfWorker);
        // Step 4: Enter the last name of the new user
        String lastNameOfWorker = "gozlan";
        lastName.sendKeys(lastNameOfWorker);
        // Step 5: Click the "Save" button to save the new user
        enterSave.click();
        // Step 6: Wait for the page to process the save operation
        sleep(5000);
        // Step 7: Go back to the PIM section to search for the newly added user
        listOfNamePage.get(1).click();
        // Step 8: Search for the newly added employee by their last name
        String searchEmployee = searchEmployee(lastNameOfWorker);
        // Step 9: Wait for the search to complete and return the result of the search
        sleep(5000);
        return searchEmployee;
    }

}
