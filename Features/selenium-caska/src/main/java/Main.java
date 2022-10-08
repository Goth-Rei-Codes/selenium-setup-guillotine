import caska.Caska;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

/**
 * @author Goth-Rei-Codes
 */

public class Main {

    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "YOUR CHROMEDRIVER PATH");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("YOUR CHROME USER DATA DIRECTORY");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void close() {
        driver.quit();
    }

    @Test
    public void test() {
        Caska caska = new Caska(driver);
        caska.goHunting("QASE SUITE URL");
    }
}