package pom;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import utilitylib.WebDriverUtilities;

public class CreateProjectForm {

	WebDriverUtilities cmd;

	public CreateProjectForm(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By name = By.id("project_name");
	By desc = By.id("project_description");
	By identifier = By.id("project_identifier");
	By markPublic = By.id("project_is_public");
	By subOfProj = By.id("project_parent_id");
	By projCreate = By.name("commit");
	
	By successAlert = By.id("flash_notice");
	By createdProjName = By.className("current-project");
	
	By subProjectOfDD = By.id("project_parent_id");
	
	
	public void createProject(String projName) {
		cmd.sendText(name, projName);
		cmd.click(projCreate);
		cmd.findElement(successAlert);
	}

	public void createProject(Map<String, String> map) {
		cmd.sendText(name, map.get("Project Name"));
		if (map.containsKey("Description"))
			cmd.sendText(desc, map.get("Description"));
		if (map.containsKey("Identifier")) {
			cmd.clearText(identifier);
			cmd.sendText(identifier, map.get("Identifier"));
		}
		if (map.containsKey("Mark Public")) {
			if(map.get("Mark Public").equals("false"))
				cmd.click(markPublic);
		}
		if (map.containsKey("Sub Project Of")) {
			switch (map.get("Sub Project Of")) {
			case "DocID":
				cmd.selectByValue(subProjectOfDD, "DocID");
				break;
			default:
				Reporter.log("Project Name is invalid: " + map.get("Sub Project Of"));
				Reporter.log(
						"As this is an optional field, continuing with the Project creation assuming this as bad data");
				break;
			}
		}

		cmd.click(projCreate);
	}
	
	public boolean isSuccessfulCreationAlertDisplayed() {
		return cmd.isElementVisible(successAlert);
	}
	
	public String getCreatedProjName() {
		return cmd.getText(createdProjName);
	}
	
}
