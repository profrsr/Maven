package wrapper;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;

public class OpenTapsWrappers {
	
	RemoteWebDriver driver;

	/* login to opentaps application
	 * 
	 * 
	 */
	GenericWrapperMethods wm;
	
	public OpenTapsWrappers(RemoteWebDriver driver) {
		this.driver = driver;
	}


	public void login() throws IOException{
		
		login("DemoCSR", "crmsfa");
		
	}
	
    public void login(String username, String password) throws IOException{
		
		wm = new GenericWrapperMethods(driver);
		
		// enter the user name
		wm.enterById("username", username);
		
		// enter the password
		wm.enterById("password", password);
		
		// click login
		wm.clickByClassName("decorativeSubmit");
		
		// verify the title
		wm.verifyTitle("Opentaps Open Source ERP + CRM");
	}
	
	
	
	public void logout() throws IOException{		
		wm.clickLink("Logout");
		wm.quitBrowser();
	}
}
