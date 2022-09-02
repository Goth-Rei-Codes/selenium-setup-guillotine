package gatsu;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rei-Codes-In-JavaScript
 */

public class Gatsu {
    private final ChromeDriver driver;
    private List<WebElement> b1WebElements = new ArrayList<>();
    private List<WebElement> b2WebElements = new ArrayList<>();
    private List<String> build1Tests = new ArrayList<>();
    private List<String> build2Tests = new ArrayList<>();
    private List<String> build1UniqueTests = new ArrayList<>();
    private List<String> build2UniqueTests = new ArrayList<>();
    private List<String> commonTests = new ArrayList<>();
    private List<String> commonFailed = new ArrayList<>();

    public Gatsu(ChromeDriver driver) {
        this.driver = driver;
    }

    public void generateAnalysisNoFailedOf(String urlBuild1, String urlBuild2) {
        driver.get(urlBuild1);
        collectTestsOfBuilds(urlBuild2);
        collectDeltaOfBuilds();
        collectCommonOfBuilds();
        generateAnalysisNoFailedReports();
    }

    public void generateAnalysisWithFailedOf(String urlBuild1, String urlBuild2) {
        generateAnalysisNoFailedOf(urlBuild1, urlBuild2);
        collectCommonFailedOfBuilds();
        generateAnalysisFailedReports();
    }

    public void collectTestsOfBuilds(String urlBuild2) {
        // Build 1 tests scraping
        b1WebElements = driver.findElements(By.xpath("//a[contains(text(), 'test')]"));

        // Generate Build 1 tests list
        build1Tests = generateBuildTestsList(b1WebElements);

        // Switch to Build 2 tab
        switchToOtherBuild(1, urlBuild2);

        // Build 2 tests scraping
        b2WebElements = driver.findElements(By.xpath("//a[contains(text(), 'test')]"));

        // Generate Build 2 tests list
        build2Tests = generateBuildTestsList(b2WebElements);
    }

    public void collectDeltaOfBuilds() {
        // Find Build 1 new tests
        build1UniqueTests = findNewTestsOfBuild(build2Tests, build1Tests);

        // Find Build 2 new tests
        build2UniqueTests = findNewTestsOfBuild(build1Tests, build2Tests);
    }

    public void collectCommonOfBuilds() {
        // Find common tests between builds
        commonTests = findCommonTestsOfBuilds(build1Tests, build1UniqueTests);
    }

    public void collectCommonFailedOfBuilds() {
        // Read Build 1 real failed tests
        List<String> failedBuild1 = readFile("real_failed.txt");

        // Find common failed tests bewteen builds
        commonFailed = findNewTestsOfBuild(build1UniqueTests, failedBuild1);
    }

    public void generateAnalysisNoFailedReports() {
        // Generate file of Build 1 new tests list
        generateFile(build1UniqueTests, "build1_unique_tests", "These are the unique tests of Build1");

        // Generate file of Build 2 new tests list
        generateFile(build2UniqueTests, "build2_unique_tests", "These are the unique tests of Build2");

        // Generate file of common tests list
        generateFile(commonTests, "common_tests", "These are the common tests between Build1 and Build2, you can skip them in Build2 tests execution");
    }

    public void generateAnalysisFailedReports() {
        generateFile(commonFailed, "common_failed_tests", "These are the common FAILED tests between Build1 and Build2, you can skip them in Build2 tests execution");
    }

    public List<String> generateBuildTestsList(List<WebElement> build) {
        List<String> buildTests = new ArrayList<>();
        for(int x=1; x<build.size(); x++) {
            buildTests.add(build.get(x).getText());
        }
        System.out.println("Number of tests: " + buildTests.size());
        return buildTests;
    }

    public List<String> findNewTestsOfBuild(List<String> origin, List<String> target){
        List<String> targetNewTests = new ArrayList<>(target);
        targetNewTests.removeAll(origin);
        return targetNewTests;
    }

    public List<String> findCommonTestsOfBuilds(List<String> build, List<String> delta){
        List<String> commonTests = new ArrayList<>(build);
        commonTests.removeAll(delta);
        System.out.println("Number of common tests: " + commonTests.size());
        return commonTests;
    }

   public void generateFile(List<String> build, String fileName, String preface){
        try {
            if(build.isEmpty()){
                System.err.println("YAY! There are no new tests for " + fileName);
            } else {
                FileWriter myWriter = new FileWriter(fileName + ".txt");
                myWriter.write("/* " + preface + " */\n\nTotal of tests: " + build.size() + "\n\n");
                build.forEach(test -> {
                    try {
                        String t = test + "\n";
                        if(test.equals(build.get(build.size()-1))){
                            t = test;
                        }
                        myWriter.write(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                myWriter.close();
                System.out.println(fileName + ".txt created");
            }
            if(fileName.equals("common_tests")){
                System.err.println("There are " + build.size() + " common tests");
            } else {
                System.err.println("There are " + build.size() + " new tests");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<String> readFile(String path){
        List<String> fileLines = new ArrayList<>();
        try {
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String test = myReader.nextLine();
                fileLines.add(test);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileLines;
    }

    public void switchToOtherBuild(int indexOfTab, String buildUrl){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(indexOfTab));
        driver.get(buildUrl);
    }
}
