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
        PageFactory.initElements(driver , this);
    }
    public void login(String user , String password){
        enterUserName.click();
        enterUserName.sendKeys(user);
        enterPassword.click();
        enterPassword.sendKeys(password);
        Login.click();
    }
    public void checkLoginAdmin() throws InterruptedException {
        String userName =  userNameAndPassword.get(0).getText().split(" : ")[1];
        String password =  userNameAndPassword.get(1).getText().split(" : ")[1];
        login(userName , password);
        System.out.println("Log in successfully as administrator");
    }
    public String checkLoginWithUserNameWrong(){
        String password =  userNameAndPassword.get(1).getText().split(" : ")[1];
        login("Admin111" , password);
        try {
            if(Objects.equals(InvalidCredentials.getText(), "Invalid credentials")){
                System.out.println(InvalidCredentials.getText());
                return "Invalid credentials";
            }
        }catch (Exception e){
            System.out.println("Login successful");
        }
        return "Login successful";

    }
    public String checkLoginWithPasswordWrong(){
        driver.navigate().refresh();
        String userName =  userNameAndPassword.get(0).getText().split(" : ")[1];
        login(userName ,"admin000");
        try {
            if(Objects.equals(InvalidCredentials.getText(), "Invalid credentials")){
                System.out.println(InvalidCredentials.getText());
                return "Invalid credentials";
            }
        }catch (Exception e){
            System.out.println("Login successful");
        }
        return "Login successful";
    }
    public String checkLoginEmpty(){
        login("","");
        try {
            if(Objects.equals(Required.getText(), "Required")){
                System.out.println("user add name and password");
                return "user add name and password";
            }
        }catch (Exception e){
            System.out.println("Login successful");
        }

        return "Login successful";
    }
    public String forgetYourPassword(){
        forgetPassword.click();
        enterUserName.sendKeys("Admin");
        resetPassword.click();
        try {
            if(Objects.equals(linkSentSuccessfully.getText(), "Reset Password link sent successfully")){
                System.out.println(linkSentSuccessfully.getText());
                return "Reset Password link sent successfully";
            }

        }catch (Exception e){
            System.out.println("Not reset Password link sent successfully");
        }
        return "Not reset Password link sent successfully";
    }
}
