package caska.powers;

import caska.Mock;
import caska.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Goth-Rei-Codes
 */

public class Vision {
    private final ChromeDriver driver;

    public Vision(ChromeDriver driver) { this.driver = driver; }

    public List<Test> investigate() {
        List<Test> suite = new ArrayList<>();
        List<WebElement> testContainers = driver.findElements(By.className("eS8b4a"));
        testContainers.forEach(container -> {
            container.click();
            suite.add(huntTestAndMocks());
            closeCase();
        });
        return suite;
    }

    public Test huntTestAndMocks() {
        List<WebElement> mocksContainers = driver.findElements(By.xpath("//p[contains(text(), 'to return') or contains(text(), 'to respond')]"));
        List<Mock> mocks = new ArrayList<>();
        mocksContainers.forEach(container -> {
            mocks.add(clarify(container));
        });
        Test test = new Test();
        test.setName(fetchTestName());
        test.setMocks(mocks);
        return test;
    }

    public String fetchTestName() {
        return driver.findElement(By.className("gnkJFY")).getText().replace(" ", "").split("\\|")[0];
    }

    public Mock clarify(WebElement container) {
        Mock mock = new Mock();
        mock.setRemotePath(container.findElement(By.tagName("code")).getText().replace(" ", ""));
        String noWhiteSpaceMock = container.getText().replace(" ", "").replace("\n", "");
        String splittingWord = "";
        if(noWhiteSpaceMock.contains("return")){
            splittingWord = "return";
        } else if(noWhiteSpaceMock.contains("respondwith")){
            splittingWord = "respondwith";
        }
        String returnMock = noWhiteSpaceMock.split(splittingWord)[1];
        if(returnMock.contains(".json")){
            if(!returnMock.contains("andstatus")){
                mock.setLocalPath(returnMock.split("with")[0]);
            } else {
                mock.setLocalPath(returnMock.split("andstatus")[0]);
            }
            mock.setStatusCode(noWhiteSpaceMock.split("code")[1].replaceAll("[^0-9]", ""));
            if(returnMock.contains("query")){
                mock.setIgnoreQueryParams(true);
            } else{
                mock.setIgnoreQueryParams(false);
            }
        } else {
            mock.setLocalPath("no_content.json");
            mock.setStatusCode(noWhiteSpaceMock.split("return")[1].replaceAll("[^0-9]", ""));
        }
        return mock;
    }

    public void closeCase() {
        driver.findElement(By.className("hhg1oh")).click();
    }
}