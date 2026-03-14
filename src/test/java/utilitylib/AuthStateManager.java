package utilitylib;

import static config.EnvVars.configDataFolderPath;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.TypeToken;

import com.google.gson.Gson;


public class AuthStateManager {
	public Path chromeCookies = configDataFolderPath.resolve("ChromeCookies.json");
	public Path ffCookies = configDataFolderPath.resolve("FFCookies.json");

	public void createAndSaveAuthState(WebDriver driver) throws Exception {

		try {
			// wait until logged in (dashboard visible)
			Set<Cookie> cookies = driver.manage().getCookies();
			// Serialize to JSON (use Gson, Jackson, etc.)
			switch (new WebDriverUtilities(driver).getBrowserName()) {
			case CHROME:
				Files.writeString(chromeCookies, new Gson().toJson(cookies));
				break;
			case FIREFOX:
				Files.writeString(ffCookies, new Gson().toJson(cookies));
				break;
			}
		} finally {
			driver.quit();
		}
	}

	public void applyAuthCookies(WebDriver driver) throws Exception {
//		driver.get("https://app.com"); // must be same domain first!

		String cookiesJson = "";
		
		switch (new WebDriverUtilities(driver).getBrowserName()) {
		case CHROME:
			cookiesJson = Files.readString(chromeCookies);
			break;
		case FIREFOX:
			cookiesJson = Files.readString(ffCookies);
			break;
		}
		
		Type type = new TypeToken<Set<Cookie>>() {
		}.getType();
		Set<Cookie> cookies = new Gson().fromJson(cookiesJson, type);

		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		driver.navigate().refresh(); // or go to desired page
	}
}
