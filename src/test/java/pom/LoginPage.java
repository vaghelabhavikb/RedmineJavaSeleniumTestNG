package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.EnvVars;
import utilitylib.WebDriverUtilities;

public class LoginPage {

	WebDriverUtilities cmd;

	public LoginPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	By signinLk = By.linkText("Sign in");
	By usernameTB = By.id("username");
	By passwordTB = By.name("password");
	By loginBTN = By.name("login");

	public void navToLoginPage() {
		cmd.driver.get(EnvVars.baseURL);
		cmd.click(signinLk);
	}

	public void login(String un, String pw) {
		cmd.sendText(usernameTB, un);
		cmd.sendText(passwordTB, pw);
		cmd.click(loginBTN);
	}

}
