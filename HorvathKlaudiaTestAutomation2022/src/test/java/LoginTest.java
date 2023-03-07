import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--ignore-certificate-errors");
        //options.addArguments("user-agent="Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)/%22%22);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    //login tesztek
    @Test
    public void validLogin() throws InterruptedException
    {

        Main main = new Main(driver);
        main.SignInTest();

        Login login = new Login(driver);
        LandingPage landingPage = new LandingPage(driver);

        Thread.sleep(3500);
        login.clickLoginTab();
        login.setUserName("username");
        login.setPassword("Jelszo01");

        login.clickOnLoginButton();
        String result = landingPage.getLogOutText();

        Assertions.assertEquals(result, "Logout");
    }
    @Test
    public void inValidLogin() throws InterruptedException {

        Main main = new Main(driver);
        main.SignInTest();

        Login login = new Login(driver);


        Thread.sleep(3500);
        login.clickLoginTab();
        login.setUserName("username");
        login.setPassword("Jelszo00");
        login.clickOnLoginButton();
        String result = login.getResult();

        Assertions.assertEquals(result, "Username or Password\n" +
                "is not correct!");

    }

    @Test
    public void MissingDetailsLogin() throws InterruptedException {

        Main main = new Main(driver);
        main.SignInTest();

        Login login = new Login(driver);


        Thread.sleep(3500);
        login.clickLoginTab();
        login.setUserName("username");
        login.setPassword("");
        login.clickOnLoginButton();
        String result = login.getResult();

        Assertions.assertEquals(result, "Username or Password\n" +
                "is not correct!");

    }


    //Logout test
    @Test

    public void logout() throws InterruptedException {
        Main main = new Main(driver);
        main.SignInTest();

        Login login = new Login(driver);
        LandingPage landingPage = new LandingPage(driver);
        popUp pop = new popUp(driver);


        Thread.sleep(3500);
        login.clickLoginTab();
        login.setUserName("username");
        login.setPassword("Jelszo01");

        login.clickOnLoginButton();
        Thread.sleep(2500);
        landingPage.clickLogoutButton();
        String result = pop.getOut();

        Assertions.assertEquals(result, "Terms and conditions");
    }


    }
