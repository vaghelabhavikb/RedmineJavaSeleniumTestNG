package pom;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class CreateUserForm {

	WebDriverUtilities cmd;

	public CreateUserForm(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	public By loginTB = By.id("user_login");
	public By firstNameTB = By.id("user_firstname");
	public By lastNameTB = By.id("user_lastname");
	public By emailTB = By.id("user_mail");
	public By isAdminCB = By.id("user_admin");
	public By passwordTB = By.id("user_password");
	public By confirmPasswordTB = By.id("user_password_confirmation");

	public By submitBTN = By.name("commit");
	
	public By usersLK = By.xpath("//a/*[text()='Users']");

	public void createUser(Map<String, String> map) {
		cmd.sendText(loginTB, map.get("Login"));
		cmd.sendText(firstNameTB, map.get("First name"));
		cmd.sendText(lastNameTB, map.get("Last name"));
		cmd.sendText(emailTB, map.get("Email"));
		if (map.containsKey("Administrator") && map.get("Administrator").equals("Yes")) {
			cmd.click(isAdminCB);
		}
		cmd.sendText(passwordTB, map.get("Password"));
		cmd.sendText(confirmPasswordTB, map.get("Password"));
		
		cmd.click(submitBTN);
	}
	
	public void navToUsersQueryPage() {
		cmd.click(usersLK);
	}
}
