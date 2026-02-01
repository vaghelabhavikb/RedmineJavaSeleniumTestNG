package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class ProjectsQueryPage {

	WebDriverUtilities cmd;

	public ProjectsQueryPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By newProjectLK = By.xpath("//a/span[text()='New project']");

	public void openProjCreationForm() {
		cmd.click(newProjectLK);
	}
	
}
