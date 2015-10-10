package scripts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import wrapper.GenericWrapperMethods;
import wrapper.OpenTapsWrappers;

public class TestCreateLead {		
	
  OpenTapsWrappers ow;
  RemoteWebDriver driver;
  GenericWrapperMethods wm;
  
  
	@BeforeClass(groups = "common")
	public void launchLogin() throws IOException {

		// create instance
		wm = new GenericWrapperMethods();

		// launch chrome and site
		driver = wm.launchApp("firefox", "http://demo1.opentaps.org");

		// create instance
		ow = new OpenTapsWrappers(driver);

		// login to app and verify the title
		ow.login("DemoSalesManager", "crmsfa");

		// click on CRMSFA
		wm.clickLink("CRM/SFA");
	}
  
  /*
   * Kill all opened browsers
   * 
   */
  @BeforeTest(groups="common")
  public void beforeTest() throws IOException {
	  
	  Runtime rt = Runtime.getRuntime();
      rt.exec("taskkill /IM chrome.exe");
      rt.exec("taskkill /IM firefox.exe");
      rt.exec("taskkill /IM iexplore.exe");	 
      
      // open the database connection
  }

  @AfterTest(groups="common")
  public void afterTest() {
	  
	  // close the database 
  }

  /*
   * create a snapshot report folder
   */
  @BeforeSuite(groups="common")
  public void beforeSuite() {
	  
	  Date now = new Date();

	  
  }

  /*
   * 
   */
  @AfterSuite(groups="common")
  public void afterSuite() {
	  
	  // code to publish the report
  }
  
  
  @AfterClass(groups="common",alwaysRun=true)
  public void logoutClose() throws IOException{
	  ow.logout();
  }
  
  @Test(groups="lead", invocationCount=1)
  public void createLead() throws IOException, InterruptedException {
	 
	 // click on create lead
	 wm.clickLink("Create Lead");
	 
	 // enter the company name
	 wm.enterById("createLeadForm_companyName", "Tester");
	 
	 // enter the first name
	 wm.enterById("createLeadForm_firstName", "First Name");
	 
	 // enter the last name
	 wm.enterById("createLeadForm_lastName", "Last Name");
	 
	 // click create lead
	 wm.clickByXpath("//*[@name='submitButton']");		
	 
	 // confirm the creation is successful
	 wm.verifyTitle("View Lead | opentaps CRM");
		
  }
  
  @Test(groups="lead",dependsOnMethods="createLead")
  public void deleteLead() throws IOException{
	  
	  // delete the lead
	  wm.clickLink("Delete");
	  
	  // verify the delete confirmation
	  wm.verifyTitle("My Leads | opentaps CRM");
	  
  }
 
  
}
