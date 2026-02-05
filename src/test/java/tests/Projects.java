package tests;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
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

	public Projects() {
		Path loginJsonPath = EnvVars.tdFolderPath.resolve(Paths.get("Jsondata", "LoginCredentials.json"));
		loginJson = new JsonTestDataFetch(loginJsonPath);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp(ITestContext context) {
		driver = new BaseClass().getDriverInstance();

		context.setAttribute("driver", driver);

		loginPage = new LoginPage(driver);
		landingPage = new LandingPage(driver);
		projectQueryPage = new ProjectsQueryPage(driver);
		createProjectForm = new CreateProjectForm(driver);

		loginPage.navToLoginPage();
		loginPage.login(loginJson.getTD("$['Main User']['UserName']"), loginJson.getTD("$['Main User']['Password']"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
//		driver.quit();
	}

	@Test(groups = {
			"smoke" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectNames", description = "Create a project with only name field")
	public void createProjectWithName(String name) {
		landingPage.navToProjectsPage();
		projectQueryPage.openProjCreationForm();
		createProjectForm.createProject(name);

		Assert.assertTrue(createProjectForm.isSuccessfulCreationAlertDisplayed());
		Assert.assertTrue(createProjectForm.getCreatedProjName().equals(name));
	}

	@Test(groups = {
			"regression" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectDetailsWithOptionalFields", description = "Create a project with optional fields")
	public void createProjWithOptionalFields(Map<String, String> projData) {
		landingPage.navToProjectsPage();
		projectQueryPage.openProjCreationForm();
		createProjectForm.createProject(projData);
		
		Assert.assertTrue(createProjectForm.isSuccessfulCreationAlertDisplayed());
		Assert.assertTrue(createProjectForm.getCreatedProjName().equals(projData.get("Project Name")));
	}
}
