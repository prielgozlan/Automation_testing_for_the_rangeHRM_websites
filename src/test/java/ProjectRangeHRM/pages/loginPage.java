package ProjectRangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class loginPage {

    private static final Logger log = LoggerFactory.getLogger(loginPage.class);

    // Locate necessary elements for login and error messages
    @FindBy(className = "oxd-text--p")
    List<WebElement> userNameAndPassword;
    @FindBy(name = "username")
    WebElement enterUserName;
    @FindBy(name = "password")
    WebElement enterPassword;
    @FindBy(className = "oxd-button--medium")
    WebElement Login;
    @FindBy(className = "oxd-alert-content-text")
    WebElement InvalidCredentials;
    @FindBy(className = "oxd-input-group__message")
    WebElement Required;
    @FindBy(className = "orangehrm-login-forgot-header")
    WebElement forgetPassword;
    @FindBy(className = "orangehrm-forgot-password-button--reset")
    WebElement resetPassword;
    @FindBy(className = "orangehrm-forgot-password-title")
    WebElement linkSentSuccessfully;
    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> logOut;
    @FindBy(className = "oxd-userdropdown-icon")
    WebElement drop;

    private ChromeDriver driver;

    public loginPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver , this); // Initialize the elements
    }

    // Method for logging in using provided credentials
    public void login(String user , String password){
        enterUserName.click(); // Click on the username field
        enterUserName.sendKeys(user); // Enter the username
        enterPassword.click(); // Click on the password field
        enterPassword.sendKeys(password); // Enter the password
        Login.click(); // Click the login button
    }

    // Test for successful login as Admin
    public void checkLoginAdmin() throws InterruptedException {
        // Step 1: Get the credentials from the page (user and password)
        String userName =  userNameAndPassword.get(0).getText().split(" : ")[1];
        String password =  userNameAndPassword.get(1).getText().split(" : ")[1];

        // Step 2: Call the login method with Admin credentials
        login(userName , password);
        System.out.println("Log in successfully as administrator");
    }

    // Test for login with incorrect username
    public String checkLoginWithUserNameWrong(){
        // Step 1: Retrieve password from the page
        String password =  userNameAndPassword.get(1).getText().split(" : ")[1];

        // Step 2: Attempt login with an incorrect username ("Admin111")
        login("Admin111" , password);

        try {
            // Step 3: Check if the error message for invalid credentials is displayed
            if(Objects.equals(InvalidCredentials.getText(), "Invalid credentials")){
                System.out.println(InvalidCredentials.getText());
                return "Invalid credentials"; // Return message if invalid credentials are shown
            }
        } catch (Exception e) {
            // If no error, login is successful
            System.out.println("Login successful");
        }
        return "Login successful"; // Return message if login succeeded despite wrong username
    }

    // Test for login with incorrect password
    public String checkLoginWithPasswordWrong(){
        driver.navigate().refresh(); // Refresh the page to ensure clean state
        // Step 1: Retrieve username from the page
        String userName =  userNameAndPassword.get(0).getText().split(" : ")[1];

        // Step 2: Attempt login with an incorrect password ("admin000")
        login(userName ,"admin000");

        try {
            // Step 3: Check if the error message for invalid credentials is displayed
            if(Objects.equals(InvalidCredentials.getText(), "Invalid credentials")){
                System.out.println(InvalidCredentials.getText());
                return "Invalid credentials"; // Return message if invalid credentials are shown
            }
        } catch (Exception e) {
            // If no error, login is successful
            System.out.println("Login successful");
        }
        return "Login successful"; // Return message if login succeeded despite wrong password
    }

    // Test for login with empty fields
    public String checkLoginEmpty(){
        // Step 1: Attempt login with both username and password left blank
        login("","");

        try {
            // Step 2: Check if the error message for required fields is shown
            if(Objects.equals(Required.getText(), "Required")){
                System.out.println("user add name and password");
                return "user add name and password"; // Return message if fields are required
            }
        } catch (Exception e) {
            // If no error, login is successful (though unlikely)
            System.out.println("Login successful");
        }

        return "Login successful"; // Return message if login succeeded despite empty fields
    }

    // Test for password reset functionality
    public String forgetYourPassword(){
        // Step 1: Click on the 'Forgot Password' link
        forgetPassword.click();

        // Step 2: Enter the username for password recovery
        enterUserName.sendKeys("Admin");

        // Step 3: Click the reset password button
        resetPassword.click();

        try {
            // Step 4: Check if the success message for password reset link is displayed
            if(Objects.equals(linkSentSuccessfully.getText(), "Reset Password link sent successfully")){
                System.out.println(linkSentSuccessfully.getText());
                return "Reset Password link sent successfully"; // Return success message
            }
        } catch (Exception e) {
            // If error occurs, return failure message
            System.out.println("Not reset Password link sent successfully");
        }
        return "Not reset Password link sent successfully"; // Return failure message if no success
    }
}
