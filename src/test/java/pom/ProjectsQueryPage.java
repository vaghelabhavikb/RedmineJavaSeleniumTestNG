package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import utilitylib.WebDriverUtilities;

public class ProjectsQueryPage {

	WebDriverUtilities cmd;

	public ProjectsQueryPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By newProjectLK = By.xpath("//a/span[text()='New project']");
	By docIDDProjLK = By.linkText("DocID");

	public void openProjCreationForm() {
		cmd.click(newProjectLK);
	}
	
	public void openProject(String projName) {
		switch(projName) {
		case "DocID":
			cmd.click(docIDDProjLK);
			break;
		default:
			Reporter.log("Project Name is invalid: " + projName);
		}
	}
	
}
