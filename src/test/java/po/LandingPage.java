package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.EnvVars;
import utilitylib.WebDriverUtilities;

public class LandingPage {

	WebDriverUtilities cmd;

	public LandingPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}
	
	public LandingPage() {
	}
	
	public By projectsLK = By.linkText("Projects");
	public By adminLK = By.linkText("Administration");


	public void navToLandingPage() {
		switch (cmd.getBrowserName()) {
		case CHROME:
			cmd.driver.get(EnvVars.baseURL1);
			break;
		case FIREFOX:
			cmd.driver.get(EnvVars.baseURL2);
			break;
		}
	}
	
	public void navToProjectsPage() {
		cmd.click(projectsLK);
	}
	
	public void navToAdminPage() {
		cmd.click(adminLK);
	}
	
}
