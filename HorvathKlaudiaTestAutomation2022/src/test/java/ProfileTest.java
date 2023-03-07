import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ProfileTest {
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
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    //adatok módosítása
    @Test
    public void editProfile()
    {
        Main main = new Main(driver);
        main.SignInTest();
        main.loginTest();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickProfileButton();
        popUp popUp = new popUp(driver);
        popUp.clickOnX();
        Profile profile = new Profile(driver);
        profile.fillName("Kiss Elemér");
        profile.fillBio("This is my bio");
        profile.fillPhoneNumber("063099988775");
        profile.saveTheProfil();
        String result = profile.getResult();
        Assert.assertEquals(result, "Profile Edited!");
    }
    //adatok törlése
    @Test
    public void DeleteProfile()
    {
        popUp popUp = new popUp(driver);
        editProfile();
        Profile profile = new Profile(driver);
        profile.deleteProfile();
        profile.sureDelete();
        String result = popUp.getOut();

        Assertions.assertEquals(result, "Terms and conditions");
    }

    
    
}
