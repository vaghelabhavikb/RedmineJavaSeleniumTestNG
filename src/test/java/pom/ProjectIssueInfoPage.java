package pom;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class ProjectIssueInfoPage {

	WebDriverUtilities cmd;

	public ProjectIssueInfoPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By trackerLbl = By.cssSelector("#content .inline-block");
	By subjectLbl = By.xpath("//div[@class='subject']//child::h3");
	By descriptionLbl = By.cssSelector("#issue_description_wiki p");
	By statusLbl = By.xpath("//div[text()='Status:']//following-sibling::div");
	By priorityLbl = By.xpath("//div[text()='Priority:']//following-sibling::div");
	By startDateLbl = By.xpath("//div[text()='Start date:']//following-sibling::div");
	By estimatedTimeLbl = By.xpath("//div[text()='Estimated time:']//following-sibling::div");

	public HashMap<String, String> getIssueInfo() {
		HashMap<String, String> info = new HashMap<String, String>();
		info.put("Tracker", cmd.getText(trackerLbl));
		info.put("Subject", cmd.getText(subjectLbl));
		if (cmd.isElementFoundWithoutWait(descriptionLbl)) {
			info.put("Description", cmd.getText(descriptionLbl));
		}
		info.put("Status", cmd.getText(statusLbl));
		info.put("Priority", cmd.getText(priorityLbl));
		info.put("StartDate", cmd.getText(startDateLbl));
		info.put("EstimatedTime", cmd.getText(estimatedTimeLbl));
		return info;
	}

}
