import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends PageBase{

    public LandingPage(WebDriver drv) {
        super(drv);
    }
    private final By logOutText = By.xpath("//*[@id=\"logout-link\"]/a");
    private final By logoutButton = By.xpath("//*[@id=\"logout-link\"]");
    private final By gotoBlogButton = By.xpath("//*[@id=\"navbarCollapse\"]/ul[1]/li[7]");
    private final By seeAllPostButton = By.cssSelector("#blog > div.container > div.row.mb-5 > div:nth-child(2) > div > a");
    private final By profileButton = By.id("profile-btn");


    public String getLogOutText()
    {
        String result = driver.findElement(logOutText).getText();
        return result;
    }

    public void clickLogoutButton()
    {
        driver.findElement(logoutButton).click();
    }
    public void goToBlog()
    {
        driver.findElement(gotoBlogButton).click();
    }
    public void seeAllPosts()
    {
        driver.findElement(seeAllPostButton).click();
    }
    public void clickProfileButton()
    {
        driver.findElement(profileButton).click();
    }

}


