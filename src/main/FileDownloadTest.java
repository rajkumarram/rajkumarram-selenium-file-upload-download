import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.*;

public class FileDownloadTest {
    WebDriver driver;
    String downloadFilepath = System.getProperty("user.dir") + "/downloads";

    @BeforeClass
    public void setup() {
        // Set Chrome preferences for automatic download
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.get("https://rajkumarram.com/downloadpage"); // Replace with actual download page URL
    }

    @Test
    public void downloadFile() throws InterruptedException {
        // Click on the download link/button
        WebElement downloadLink = driver.findElement(By.id("downloadButton")); // Replace with actual locator
        downloadLink.click();

        // Wait for download to complete (simple wait; better to use explicit wait or polling)
        Thread.sleep(5000);

        // Verify file is downloaded
        File dir = new File(downloadFilepath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".pdf")); // Example: expecting a PDF file
        assert files != null && files.length > 0 : "File not downloaded!";
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Optionally clean up downloaded files after test
        try {
            Files.walk(Paths.get(downloadFilepath))
                .map(Path::toFile)
                .forEach(File::delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
