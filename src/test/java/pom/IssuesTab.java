package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class IssuesTab {

	WebDriverUtilities cmd;

	public IssuesTab(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}
	
	By newIssueLK = By.xpath("//span[text()='New issue']");
	
	public void openIssueCreationForm() {
		cmd.click(newIssueLK);
	}
}
