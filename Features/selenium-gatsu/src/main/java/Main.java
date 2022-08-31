/**
 * @author Rei-Codes-In-JavaScript
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "YOUR CHROMEDRIVER PATH");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void close() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void generate() {

        // Reach Build 1 page
        driver.get("BUILD1 HTML FILE");

        // Build 1 tests scraping
        List<WebElement> b1 = driver.findElements(By.xpath("//a[contains(text(), 'test')]"));

        // Generate Build 1 tests list
        List<String> build1 = generateBuildTestsList(b1);

        // Switch to Build 2 tab
        switchToOtherBuild(1, "BUILD2 HTML FILE");

        // Build 2 tests scraping
        List<WebElement> b2 = driver.findElements(By.xpath("//a[contains(text(), 'test')]"));

        // Generate Build 2 tests list
        List<String> build2 = generateBuildTestsList(b2);

        // Find Build 1 new tests
        List<String> build1NewTests = findNewTestsOfBuild(build2, build1);

        // Find Build 2 new tests
        List<String> build2NewTests = findNewTestsOfBuild(build1, build2);

        // Generate file of Build 1 new tests list
        generateDeltaFile(build1NewTests, "build1");

        // Generate file of Build 2 new tests list
        generateDeltaFile(build2NewTests, "build2");
    }

    public void switchToOtherBuild(int indexOfTab, String buildUrl){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(indexOfTab));
        driver.get(buildUrl);
    }

    List<String> generateBuildTestsList(List<WebElement> build){
        List<String> buildTests = new ArrayList<>();

        for(int x=1; x<build.size(); x++) {
            buildTests.add(build.get(x).getText());
        }

        System.out.println("Number of tests: " + buildTests.size());
        return buildTests;
    }

    List<String> findNewTestsOfBuild(List<String> origin, List<String> target) {
        List<String> targetNewTests = new ArrayList<>(target);
        targetNewTests.removeAll(origin);
        return targetNewTests;
    }

    void generateDeltaFile(List<String> build, String fileName){
        try {
            if(build.isEmpty()) {
                System.err.println("YAY! There are no new tests for " + fileName);
            } else {
                FileWriter myWriter = new FileWriter(fileName + ".txt");
                myWriter.write("Total of new tests: " + build.size() + "\n\n");
                build.forEach(test -> {
                    try {
                        myWriter.write(test + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                myWriter.close();
                System.out.println(fileName + ".txt created");
            }
            System.err.println("There are " + build.size() + " new tests");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
