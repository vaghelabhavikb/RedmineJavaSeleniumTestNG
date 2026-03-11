package tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Projects extends Common {

	@BeforeMethod(alwaysRun = true)
	public void projectsTCSetUp() {
		po.landingPage().navToProjectsPage();
	}

	@Test(groups = {
			"smoke" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectNames", description = "Create a project with only name field")
	public void createProjectWithName(String name) {
		po.projectQueryPage().openProjCreationForm();
		po.createProjectForm().createProject(name);

		Assert.assertTrue(po.createProjectForm().isSuccessfulCreationAlertDisplayed());
		Assert.assertTrue(po.createProjectForm().getCreatedProjName().equals(name));
	}

	@Test(groups = {
			"regression" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "ProjectDetailsWithOptionalFields", description = "Create a project with optional fields")
	public void createProjWithOptionalFields(Map<String, String> projData) {
		po.projectQueryPage().openProjCreationForm();
		po.createProjectForm().createProject(projData);

		Assert.assertTrue(po.createProjectForm().isSuccessfulCreationAlertDisplayed());
		Assert.assertTrue(po.createProjectForm().getCreatedProjName().equals(projData.get("Project Name")));
	}

	@Test(groups = {
			"smoke" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "CreateIssues", description = "Create issues for a project")
	public void createIssues(LinkedHashMap<String, String> map) {
		// Test
		po.projectQueryPage().openProject("DocID");
		po.projectPage().navToIssuesTab();
		po.issuesTab().openIssueCreationForm();
		po.createIssueForm().createIssue(map);

		// Validation
		HashMap<String, String> actualIssueInfo = po.projectIssueInfoPage().getIssueInfo();
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

	@Test(groups = {
			"regression" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "PostSpentTime", description = "Post time spent for an issue")
	public void postTimeSpent(LinkedHashMap<String, String> map) {
		po.projectQueryPage().openProject("DocID");
		po.projectPage().navToSpentTimeTab();
		po.spentTimeTab().launchSpentTimeCreationForm();
		po.createSpentTimeForm().postSpentTime(map);

		SoftAssert sa = new SoftAssert();
		Map<String, String> act = po.spentTimeTab().getTimeEntry(map.get("Hours"));
		for (String key : map.keySet()) {
			switch (key) {
			case "Date":
				DateTimeFormatter expDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter actDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate expDate = LocalDate.parse(map.get(key), expDateFormatter);
				LocalDate actDate = LocalDate.parse(act.get(key), actDateFormatter);
				sa.assertTrue(actDate.isEqual(expDate),
						"Comparison For: " + key + "|Actual: " + act.get(key) + "|Expected: " + map.get(key) + "|");
				break;
			default:
				sa.assertTrue(act.get(key).contains(map.get(key)),
						"Comparison For: " + key + "|Actual: " + act.get(key) + "|Expected: " + map.get(key) + "|");
				break;
			}
		}
		sa.assertAll();
	}
}
