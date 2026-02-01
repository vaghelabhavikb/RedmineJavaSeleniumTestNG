package utilitylib;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {

	public WebDriver wd;

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


		wd = new ChromeDriver(co);
		return wd;
	}

}
