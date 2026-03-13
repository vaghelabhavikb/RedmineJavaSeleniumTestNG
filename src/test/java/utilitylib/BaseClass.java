package utilitylib;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseClass {

	public WebDriver wd;

//	public static void main(String[] args) {
//		new BaseClass().getDriverInstance();
//	}

	public WebDriver getDriverInstance(String browser, boolean headless) {
		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions co = new ChromeOptions();
			co.setAcceptInsecureCerts(true);
			co.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//		co.setHeadless(true); // this is old method won't work properly in some cases
//		https://developer.chrome.com/articles/new-headless/
			if (headless) {
				co.addArguments("--headless=new");
				co.addArguments("--window-size=1366,768");
			}
			co.addArguments("--start-maximized");
			co.addArguments("--remote-allow-origins=*");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("download.default_directory", "C:\\Temp");
			map.put("credentials_enable_service", false);
			co.setExperimentalOption("prefs", map);

			wd = new ChromeDriver(co);
			return wd;
		} else {
			FirefoxOptions fo = new FirefoxOptions();
			if (headless) {
				fo.addArguments("-headless");
			}
			fo.setAcceptInsecureCerts(true);
			fo.addPreference("dom.webdriver.enabled", false); // Similar to excluding enable-automation
			fo.addPreference("useAutomationExtension", false);
			fo.addPreference("browser.download.dir", "C:\\Temp");
			fo.addPreference("browser.download.folderList", 2); // 2 means use custom location
			fo.addPreference("browser.download.manager.showWhenStarting", false);
			fo.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,application/pdf");
			fo.addPreference("signon.rememberSignons", false);
			wd = new FirefoxDriver(fo);
			wd.manage().window().maximize();
			return wd;
		}
	}

}
