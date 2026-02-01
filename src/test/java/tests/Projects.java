package tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.EnvVars;
import pom.CreateProjectForm;
import pom.LandingPage;
import pom.LoginPage;
import pom.ProjectsQueryPage;
import utilitylib.BaseClass;
import utilitylib.JsonTestDataFetch;

public class Projects {

	WebDriver driver;

	LoginPage loginPage;
	LandingPage landingPage;
	ProjectsQueryPage projectQueryPage;
	CreateProjectForm createProjectForm;

	JsonTestDataFetch loginJson;

	@BeforeMethod
	public void setUp() {
		driver = new BaseClass().getDriverInstance();

		loginPage = new LoginPage(driver);
		landingPage = new LandingPage(driver);
		projectQueryPage = new ProjectsQueryPage(driver);
		createProjectForm = new CreateProjectForm(driver);
		
		Path loginJsonPath = EnvVars.tdFolderPath.resolve(Paths.get("Jsondata", "LoginCredentials.json"));
		loginJson = new JsonTestDataFetch(loginJsonPath);

		loginPage.navToLoginPage();

		loginPage.login(loginJson.getTD("$['Main User']['UserName']"), loginJson.getTD("$['Main User']['Password']"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectNames")
	public void createProjectWithName(String name) {
		landingPage.navToProjectsPage();
		projectQueryPage.openProjCreationForm();
		createProjectForm.createProject(name);
	}

}
