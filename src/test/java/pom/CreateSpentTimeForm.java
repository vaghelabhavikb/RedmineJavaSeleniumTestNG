package pom;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class CreateSpentTimeForm {
	WebDriverUtilities cmd;

	public CreateSpentTimeForm(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By issueSelector = By.id("time_entry_issue_id");
	By supportEditTimeIssue = By.xpath("//div[contains(text(),'Support Edit Time field for DocID')]");
	By userSelect = By.id("time_entry_user_id");
	By hoursTB = By.id("time_entry_hours");
	By datePicker = By.id("time_entry_spent_on");
	By commentTB = By.id("time_entry_comments");
	By activityDD = By.id("time_entry_activity_id");

	By createBtn = By.name("commit");

	public void postSpentTime(Map<String, String> data) {

		cmd.sendText(issueSelector, data.get("Issue"));
		cmd.click(supportEditTimeIssue);
		cmd.selectByVisibleText(userSelect, data.get("User"));
		cmd.sendText(datePicker, data.get("Date"));
		cmd.sendText(hoursTB, data.get("Hours"));
		if (data.containsKey("Comment")) {
			cmd.sendText(commentTB, data.get("Comment"));
		}
		cmd.selectByVisibleText(activityDD, data.get("Activity"));

		cmd.click(createBtn);
	}
}
