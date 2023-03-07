import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BlogTest {

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

    //több oldalas lista bejárása
    @Test
    public void goToBlog() throws InterruptedException {

        Main main = new Main(driver);
        main.SignInTest();
        main.loginTest();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goToBlog();
        Thread.sleep(3000);
        landingPage.seeAllPosts();
        Thread.sleep(3000);
        Blog blog = new Blog(driver);
       // blog.clickNext();
        int actual = 0;

        while (true) {
            actual += blog.numberOfRows();

            if (blog.isLastPage()) {
                break;
            }
            blog.clickNext();
        }
        Assertions.assertEquals(9, actual);
    }
}
