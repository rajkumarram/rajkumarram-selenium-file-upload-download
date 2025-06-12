//Code Example: - 
//Java + TestNG (File Upload)
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class FileUploadTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
      Log.info("Starting WebDriver...");
		  String chromedriverPath = "C:\\Users\\rajkumar.ram\\chromedriver-win64\\chromedriver.exe";
		  System.setProperty("webdriver.chrome.driver", chromedriverPath);
		
        driver = new ChromeDriver();
        driver.get("https://rajkumarram.com/upload");
    }

    @Test
    public void uploadFile() {
        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys("C:\\Users\\User\\Documents\\testfile.txt");

        driver.findElement(By.id("file-submit")).click();

        WebElement successMsg = driver.findElement(By.tagName("h3"));
        assert successMsg.getText().equals("File Uploaded!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
