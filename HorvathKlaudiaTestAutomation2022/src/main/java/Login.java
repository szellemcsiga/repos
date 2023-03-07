import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class Login extends PageBase {
    private final String url = "https://lennertamas.github.io/portio/";
    private final By username = By.id("email");
    private final By password = By.id("password");
    private final By loginButton = By.xpath("//*[@id=\"login\"]/form/div[4]/button");



    private final By resultMessage = By.id("alert");


    public Login(WebDriver drv) {
        super(drv);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public void setUserName(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void setPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getResult() {
        String result = driver.findElement(resultMessage).getText();

        return result;
    }

    public void clickLoginTab()
    {
        WebElement loginTab  = driver.findElement(By.xpath("(//*[@id=\"login-form-button\"])[2]"));
       loginTab.click();
    }

}