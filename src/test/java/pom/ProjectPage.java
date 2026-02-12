package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class ProjectPage {

	WebDriverUtilities cmd;
	
	public ProjectPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}
	
	By issuesLK = By.linkText("Issues");
	
	public void navToIssuesTab() {
		cmd.click(issuesLK);
	}
	
}
