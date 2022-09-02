import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import gatsu.Gatsu;

/**
 * @author Rei-Codes-In-JavaScript
 */

public class Main {

    ChromeDriver driver;

    String build1 = "BUILD1 PATH";
    String build2 = "BUILD2 PATH";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "YOUR CHROMEDRIVER PATH");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void close() {
        driver.quit();
    }

    @Test
    public void generateAnalysisNoFailed() {

        /*
         * This script generates 3 files:
         * 1. Build1 unique tests list (build1_unique_tests.txt)
         * 2. Build2 unique tests list (build2_unique_tests.txt)
         * 3. Build1 and Build2 common tests list (common_tests.txt)
         *
         * REQUIREMENTS: none
         *
         */

        Gatsu gatsu = new Gatsu(driver);
        gatsu.generateAnalysisNoFailedOf(build1, build2);
    }

    @Test
    public void generateAnalysisWithFailed() {

        /*
         * This script generates 4 files:
         * 1. Build1 unique tests list (build1_unique_tests.txt)
         * 2. Build2 unique tests list (build2_unique_tests.txt)
         * 3. Build1 and Build2 common tests list (common_tests.txt)
         * 4. Build1 and Build2 common FAILED tests (common_failed_tests.txt)
         *
         * REQUIREMENTS:
         * 1. Build1 previous execution
         * 2. Build1 list of failed tests
         * 3. The failed list must match the same format of the Jenkins' list]
         * 4. You can use the real_failed.txt file to paste the list of failed in your Build1 execution, but if you choose to use another file,
         *    remember to change the file name/path when the gatsu.readfile() method is called
         *
         */

        Gatsu gatsu = new Gatsu(driver);
        gatsu.generateAnalysisWithFailedOf(build1, build2);
    }
}