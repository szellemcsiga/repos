import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SignInTest {
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
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void closeTermsAndCondtitons() {
        Signin signin = new Signin(driver);
        signin.navigate();
        signin.closeModal();

    }

    @Test
    public void acceptTermsAndConditions() {
        Signin signin = new Signin(driver);
        signin.navigate();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();

    }

    //valid sign in
    @Test
    public void SignInTest() {
        Signin signin = new Signin(driver);
        signin.navigate();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();
        signin.clickOnTopRegisterButton();
        signin.addUsername("username");
        signin.addPassword("Jelszo01");
        signin.addEmail("dfsf@ggr.hu");
        signin.addDescription("-");
        signin.clickOnRegisterButton();
        String result = signin.getResult();

        Assertions.assertEquals(result, "User registered!");
    }


    //no password added
    @Test
    public void SignInWithoutData() {
        Signin signin = new Signin(driver);
        signin.navigate();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();
        signin.clickOnTopRegisterButton();
        signin.addUsername("bela");
        signin.addPassword("");
        signin.addEmail("dfsf@ggr.hu");
        signin.addDescription("-");
        signin.clickOnRegisterButton();
        String result = signin.getResult();

        Assertions.assertNotSame("User registered!", result);
    }

    //wrong emailaddress added
    @Test
    public void SignInWithWrongData() {

        Signin signin = new Signin(driver);
        signin.navigate();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();
        signin.clickOnTopRegisterButton();
        signin.addUsername("bela");
        signin.addPassword("bela");
        signin.addEmail("dfsf");
        signin.addDescription("-");
        signin.clickOnRegisterButton();
        String result = signin.getResult();

        Assertions.assertNotSame("User registered!", result);
    }


    //sign in with the same datas
    @Test
    public void SignInWithDuplicateData() {

        Signin signin = new Signin(driver);
        signin.navigate();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();
        signin.clickOnTopRegisterButton();
        signin.addUsername("bela");
        signin.addPassword("bela");
        signin.addEmail("dfsfdfsf@rffr.hu");
        signin.addDescription("-");
        signin.clickOnRegisterButton();
        signin.addUsername("bela");
        signin.addPassword("bela");
        signin.addEmail("dfsf@rffr.hu");
        signin.addDescription("-");
        signin.clickOnRegisterButton();
        String result = signin.getResult();

        Assertions.assertEquals(result, "User registered!");
    }

    //ismételt és sorozatos adatbevitel
    @Test
    public void textTest() {

        Signin signin = new Signin(driver);
        signin.navigate();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();
        signin.clickOnTopRegisterButton();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "source.txt"));

            String line = reader.readLine();
            String[] datasArray = line.split(",");


            while (line != null) {

                signin.registratefromtext(datasArray);
                // read next line
                line = reader.readLine();
                signin.clearForm();
                Thread.sleep(2000);
            }
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();


        }
    String result = signin.getResult();

    Assertions.assertEquals(result, "User registered!");
    }
}

