package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class AdminPage {

	WebDriverUtilities cmd;
	
	public AdminPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}
	
	public By usersLK = By.xpath("//a/*[text()='Users']");
	
	public void openUsersPage() {
		cmd.click(usersLK);
	}
	
}
