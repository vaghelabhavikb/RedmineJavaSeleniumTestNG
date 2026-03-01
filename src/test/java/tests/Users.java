package tests;

import static config.EnvVars.jsonTDPath;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.AdminPage;
import pom.CreateUserForm;
import pom.LandingPage;
import pom.LoginPage;
import pom.UsersQueryPage;
import utilitylib.BaseClass;
import utilitylib.JsonTestDataFetch;

public class Users {
	WebDriver driver;

	LoginPage loginPage;
	LandingPage landingPage;
	AdminPage adminPage;
	UsersQueryPage usersQueryPage;
	CreateUserForm createUserForm;

	JsonTestDataFetch loginJson;

	public Users() {
		loginJson = new JsonTestDataFetch(jsonTDPath.resolve("LoginCredentials.json"));
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp(ITestContext context) {
		driver = new BaseClass().getDriverInstance();

		context.setAttribute("driver", driver);

		loginPage = new LoginPage(driver);
		landingPage = new LandingPage(driver);
		adminPage = new AdminPage(driver);
		usersQueryPage = new UsersQueryPage(driver);
		createUserForm = new CreateUserForm(driver);

		loginPage.navToLoginPage();
		loginPage.login(loginJson.getTD("$['Main User']['UserName']"), loginJson.getTD("$['Main User']['Password']"));
		landingPage.navToAdminPage();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	@Test(groups = {
			"regression" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "CreateUsers", description = "Post time spent for an issue")
	public void createUsers(LinkedHashMap<String, String> map) {
		adminPage.openUsersPage();
		usersQueryPage.openUserCreationForm();
		createUserForm.createUser(map);
		createUserForm.navToUsersQueryPage();

		SoftAssert sa = new SoftAssert();
		Map<String, String> act = usersQueryPage.getUserEntry(map.get("Login"));
		for (String key : map.keySet()) {
			if (!key.equals("Password")) {
				sa.assertTrue(act.get(key).contains(map.get(key)),
						"Comparison For: " + key + "|Actual: " + act.get(key) + "|Expected: " + map.get(key) + "|");
			}
		}
		sa.assertAll();
	}

}
