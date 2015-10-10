package wrapper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GenericWrapperMethods {
	RemoteWebDriver driver;
	int i = 1;

	public GenericWrapperMethods() {

	}

	public GenericWrapperMethods(RemoteWebDriver driver) {
		this.driver = driver;
	}

	/*
	 * This method will launch only firefox and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * 
	 * @param url - The url with http or https
	 * 
	 */
	public void launchApp(String url) throws IOException {

		try {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			
			} catch (Exception e) {
			// ATUReports.add("The browser could not loaded", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		   
		takeSnapShot();
	}

	/**
	 * This method will launch any browser and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * 
	 * @param browser - Browser of type firefox or chrome or ie
	 * 
	 * @param url - The url with http or https
	 * @author Babu
	 *  
	 */
	public RemoteWebDriver launchApp(String browser, String url) throws IOException {

		/*
		 * DesiredCapabilities dc = new DesiredCapabilities();
		 * dc.setPlatform(Platform.WINDOWS);
		 * 
		 */

		// if the browser is firefox
		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();

			/*
			 * dc.setBrowserName("firefox"); driver = new RemoteWebDriver(new
			 * URL("http://192.168.1.9:5555/wd/hub"),dc);
			 */

		} else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");

			driver = new ChromeDriver();
			/*
			 * // Set Browser Name dc.setBrowserName("chrome");
			 * dc.setVersion("44.0"); driver = new RemoteWebDriver(new
			 * URL("http://192.168.1.9:5555/wd/hub"),dc);
			 */

		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");

			driver = new InternetExplorerDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		takeSnapShot();

		return driver;

	}

	// take snapshot
	public void takeSnapShot() throws IOException {
		File src = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\SelSep\\snap_" + i + ".png"));
		i++;
	}

	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
 	 *  
	 */
	public void enterById(String idValue, String data) throws IOException {
		try {
			driver.findElement(By.id(idValue)).clear();
			driver.findElement(By.id(idValue)).sendKeys(data);
		} catch (NoSuchElementException e) {
			System.out.println("The element with the id: " + idValue + "is not present");
		} catch (WebDriverException e1) {
			System.out.println("The browser is unavailable");
		} finally {
			takeSnapShot();
		}
	}

	// enter value by name
	public void enterByName(String nameValue, String data) throws IOException {
		driver.findElement(By.name(nameValue)).clear();
		driver.findElement(By.name(nameValue)).sendKeys(data);
		takeSnapShot();
	}

	// Click by class name
	public void clickByClassName(String classValue) throws IOException {
		driver.findElement(By.className(classValue)).click();
		takeSnapShot();
	}

	// Verify title
	public void verifyTitle(String title) throws IOException {
		if (driver.getTitle().equalsIgnoreCase(title)) {
			System.out.println("Title is matched");
		} else {
			System.out.println("Title do not match");
		}
		takeSnapShot();
	}

	// Close browser
	public void quitBrowser() {
		driver.quit();
	}

	public void clickLink(String linkName) throws IOException {
		driver.findElement(By.linkText(linkName)).click();
		takeSnapShot();
	}

	// Click by xpath
	public void clickByXpath(String xpath) throws IOException {
		driver.findElement(By.xpath(xpath)).click();
		takeSnapShot();
	}

	// accept alert
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

}
