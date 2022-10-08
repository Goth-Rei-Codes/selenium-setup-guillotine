package caska.powers;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Goth-Rei-Codes
 */

public class Speed {
    private ChromeDriver driver;

    public Speed(ChromeDriver driver) { this.driver = driver; }

    public void marchTo(String suiteUrl) {
        driver.get(suiteUrl);
    }
}
