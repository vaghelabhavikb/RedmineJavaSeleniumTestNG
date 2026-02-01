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
	
	
	public void navToProjectsPage() {
		cmd.click(projectsLK);
	}
	
}
