package caska;

import caska.powers.Speech;
import caska.powers.Speed;
import caska.powers.Vision;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Goth-Rei-Codes
 */

public class Caska {
    private final ChromeDriver driver;
    public Caska(ChromeDriver driver) { this.driver = driver; }

    public void goHunting(String suiteUrl) {
        Speed speed = new Speed(driver);
        Vision vision = new Vision(driver);
        Speech speech = new Speech(driver);

        speed.marchTo(suiteUrl);
        speech.translate(vision.investigate());
    }
}
