package tests;

import static config.EnvVars.jsonTDPath;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.CreateIssueForm;
import pom.CreateProjectForm;
import pom.IssuesTab;
import pom.LandingPage;
import pom.LoginPage;
import pom.ProjectIssueInfoPage;
import pom.ProjectPage;
import pom.ProjectsQueryPage;
import utilitylib.BaseClass;
import utilitylib.JsonTestDataFetch;

public class Projects {

	WebDriver driver;

	LoginPage loginPage;
	LandingPage landingPage;
	ProjectsQueryPage projectQueryPage;
	CreateProjectForm createProjectForm;
	ProjectPage projectPage;
	IssuesTab issuesTab;
	CreateIssueForm createIssueForm;
	ProjectIssueInfoPage projectIssueInfoPage;

	JsonTestDataFetch loginJson;

	public Projects() {
		loginJson = new JsonTestDataFetch(jsonTDPath.resolve("LoginCredentials.json"));
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp(ITestContext context) {
		driver = new BaseClass().getDriverInstance();

		context.setAttribute("driver", driver);

		loginPage = new LoginPage(driver);
		landingPage = new LandingPage(driver);
		projectQueryPage = new ProjectsQueryPage(driver);
		createProjectForm = new CreateProjectForm(driver);
		projectPage = new ProjectPage(driver);
		issuesTab = new IssuesTab(driver);
		createIssueForm = new CreateIssueForm(driver);
		projectIssueInfoPage = new ProjectIssueInfoPage(driver);

		loginPage.navToLoginPage();
		loginPage.login(loginJson.getTD("$['Main User']['UserName']"), loginJson.getTD("$['Main User']['Password']"));
		landingPage.navToProjectsPage();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
//		driver.quit();
	}

	@Test(groups = {
			"smoke" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectNames", description = "Create a project with only name field")
	public void createProjectWithName(String name) {
		projectQueryPage.openProjCreationForm();
		createProjectForm.createProject(name);

		Assert.assertTrue(createProjectForm.isSuccessfulCreationAlertDisplayed());
		Assert.assertTrue(createProjectForm.getCreatedProjName().equals(name));
	}

	@Test(groups = {
			"regression" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectDetailsWithOptionalFields", description = "Create a project with optional fields")
	public void createProjWithOptionalFields(Map<String, String> projData) {
		projectQueryPage.openProjCreationForm();
		createProjectForm.createProject(projData);

		Assert.assertTrue(createProjectForm.isSuccessfulCreationAlertDisplayed());
		Assert.assertTrue(createProjectForm.getCreatedProjName().equals(projData.get("Project Name")));
	}

	@Test(groups = {
			"smoke" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "CreateIssues", description = "Create issues for a project")
	public void createIssues(LinkedHashMap<String, String> map) {
		// Test
		projectQueryPage.openProject("DocID");
		projectPage.navToIssuesTab();
		issuesTab.openIssueCreationForm();
		createIssueForm.createIssue(map);

		// Validation
		HashMap<String, String> actualIssueInfo = projectIssueInfoPage.getIssueInfo();
		SoftAssert sa = new SoftAssert();
		for (String fieldName : map.keySet()) {
			switch (fieldName) {
			case "StartDate":
				DateTimeFormatter expDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter actDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate expDate = LocalDate.parse(map.get(fieldName), expDateFormatter);
				LocalDate actDate = LocalDate.parse(actualIssueInfo.get(fieldName), actDateFormatter);
				sa.assertTrue(expDate.isEqual(actDate), fieldName + " comparison");
				break;
			default:
				sa.assertTrue(actualIssueInfo.get(fieldName).contains(map.get(fieldName)), fieldName + " comparison");
				break;
			}
		}
		sa.assertAll();
	}
}
