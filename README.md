# Selenium Setup Guillotine
### What is Selenium?
[Selenium](https://www.selenium.dev/) is a scraping and website testing framework. It is based on many languages:
* [Java](https://www.java.com/it/)
* [JavaScript](https://www.javascript.com/)
* [Python](https://www.python.org/)
* [Ruby](https://www.ruby-lang.org/en/)
* [C#](https://dotnet.microsoft.com/en-us/languages/csharp)

I usually work with Java-based Selenium

### So what can i do to use Selenium?
There are few simple steps to start using Selenium:

1. Download a ChromeDriver aligned with your current Chrome version. You can download your ChromeDriver [here](https://chromedriver.chromium.org/downloads)

2. Integrate the [Selenium dependecies](https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine-/blob/main/Pom_Dependencies) in the pom.xml file inside your project

```
 <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>compile</scope>
        </dependency>
  </dependencies>
```

3. Create your Main class, in which you will write all your tests. You can use this [template](https://github.com/Rei-Codes-In-JavaScript/selenium-setup-guillotine-/blob/main/Main_Template) as a base/reference 
  > **WARNING**: remember to change the ChromeDriver path with your ChromeDriver path inside ___System.setProperty("webdriver.chrome.driver", "CHROMEDRIVER_PATH");___

```
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "CHROMEDRIVER_PATH");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void close() throws InterruptedException{
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void test(){
        driver.get("https://www.reddit.com/");
    }
}
```

### Why Guillotine?
I don't know, it sounds dope. Also i love [Guillotine by Pi'erre Bourne](https://www.youtube.com/watch?v=mgras4X0aKU)

### What if i refuse to use Selenium?
Statistics confirm a 100% decrease in dopamine, serotonin and oxytocin production.
<p>Additionally, scientific studies say 99.9% of people who drop out of using Selenium fail to achieve satisfaction in their job.</p>
<p align="center" style="font-style: italic;">
<img src="https://static.wikia.nocookie.net/60641246-75fe-4ebb-ab7a-e861c95c4895/scale-to-width/755" width="200"/><br>
Footage of a person who refused to use Selenium lol
</p>
