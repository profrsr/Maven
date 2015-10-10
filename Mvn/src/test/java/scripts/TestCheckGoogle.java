package scripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestCheckGoogle {
	
	@Test
	public void run() throws IOException {
		
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://google.co.in");
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("C:\\SelSep\\take.jpg"));
	}

}
