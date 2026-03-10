package tests;

import static config.EnvVars.jsonTDPath;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilitylib.BaseClass;
import utilitylib.JsonTestDataFetch;
import utilitylib.POManager;

public class Common {

	public WebDriver driver;

	POManager po;

	JsonTestDataFetch loginJson;

	public Common() {
		loginJson = new JsonTestDataFetch(jsonTDPath.resolve("LoginCredentials.json"));
	}

	@BeforeMethod(alwaysRun = true)
	public void commonSetup(ITestContext context) {
		driver = new BaseClass().getDriverInstance();

		context.setAttribute("driver", driver);

		po = new POManager(driver);

		po.loginPage().navToLoginPage();
		po.loginPage().login(loginJson.getTD("$['Main User']['UserName']"),
				loginJson.getTD("$['Main User']['Password']"));
	}

	@AfterMethod(alwaysRun = true)
	public void commonTearDown() {
		driver.quit();
	}

}
