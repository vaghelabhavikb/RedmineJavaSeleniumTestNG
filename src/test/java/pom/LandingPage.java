package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class LandingPage {

	WebDriverUtilities cmd;

	public LandingPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}
	
	By projectsLK = By.linkText("Projects");
	By adminLK = By.linkText("Administration");
	
	public void navToProjectsPage() {
		cmd.click(projectsLK);
	}
	
	public void navToAdminPage() {
		cmd.click(adminLK);
	}
	
}
