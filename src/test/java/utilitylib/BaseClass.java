package utilitylib;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.EnvVars;
import po.AdminPage;
import po.CreateUserForm;
import po.LandingPage;
import po.LoginPage;
import po.UsersQueryPage;

public class BaseClass {

	public static void main(String[] args) {
		new BaseClass().getDriverInstance();
	}

	public WebDriver getDriverInstance() {
		ChromeOptions co = new ChromeOptions();
		co.setAcceptInsecureCerts(true);
		co.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//		co.setHeadless(true); // this is old method won't work properly in some cases
//		https://developer.chrome.com/articles/new-headless/
//		co.addArguments("--headless=new");
		co.addArguments("--start-maximized");
		co.addArguments("--remote-allow-origins=*");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("download.default_directory",
				"C:\\Users\\vaghe\\eclipse-workspace-github\\eclipse-workspace-github\\InternetHerokuSeleniumTestNG\\test-output\\Downloads");
		map.put("credentials_enable_service", false);
		co.setExperimentalOption("prefs", map);

		return new ChromeDriver(co);

	}

}
