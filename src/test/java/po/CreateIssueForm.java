package po;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import utilitylib.WebDriverUtilities;

public class CreateIssueForm{
	WebDriverUtilities cmd;
	String browserName;

	public CreateIssueForm(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By trackerSelect = By.id("issue_tracker_id");
	By subjectTB = By.id("issue_subject");
	By descriptionTB = By.id("issue_description");
	By statusSelect = By.id("issue_status_id");
	By prioritySelect = By.id("issue_priority_id");
	By startDatePicker = By.id("issue_start_date");
	By estimatedTime = By.id("issue_estimated_hours");
	By saveIssue = By.name("commit");

	public void createIssue(Map<String, String> map) {

		for (String key : map.keySet()) {
			switch (key) {
			case "Tracker":
				cmd.selectByValue(trackerSelect, map.get(key));
				break;
			case "Subject":
				cmd.sendText(subjectTB, map.get(key));
				break;
			case "Description":
				cmd.sendText(descriptionTB, map.get(key));
				break;
			case "Status":
				cmd.selectByValue(statusSelect, map.get(key));
				break;
			case "Priority":
				cmd.selectByValue(prioritySelect, map.get(key));
				break;
			case "StartDate":
				switch (cmd.getBrowserName()) {
				case CHROME:
					cmd.sendText(startDatePicker, map.get(key));
					break;
				case FIREFOX:
					cmd.actionsSendText(startDatePicker, map.get(key));
					break;
				default:
					Reporter.log("Unknown browser detected: " + cmd.getBrowserName());
					break;
				}
				break;
			case "EstimatedTime":
				cmd.sendText(estimatedTime, map.get(key));
				break;
			default:
				Reporter.log("Incorrect Issue field in test data json: " + key);
				break;
			}
		}
		cmd.click(saveIssue);
	}
}
